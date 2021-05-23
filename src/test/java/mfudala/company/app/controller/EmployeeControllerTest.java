package mfudala.company.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.TextNode;
import mfudala.company.app.AppApplication;
import mfudala.company.app.model.ActiveEmployee;
import mfudala.company.app.model.Employee;
import mfudala.company.app.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void shouldAddEmployee() {
        Employee testEmp = getTestEmployee();
        assertNull(testEmp.getId());

        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(
                getRootUrl() + "/employee", testEmp, Employee.class);

        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        assertNotNull(postResponse.getBody().getId());
    }

    @Test
    void shouldAddActiveEmployee() {
        ActiveEmployee testActEmp = getTestActiveEmployee();
        assertNull(testActEmp.getId());

        ResponseEntity<ActiveEmployee> postResponse = restTemplate.postForEntity(
                getRootUrl() + "/employee", testActEmp, ActiveEmployee.class);

        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        assertEquals(HttpStatus.CREATED, postResponse.getStatusCode());
        assertNotNull(postResponse.getBody().getId());
    }

    @Test
    void shouldReturnAllEmployees() {
        ResponseEntity<String> response = getAllEmployees();

        assertNotNull(response.getBody());
        assertTrue(response.getStatusCode()
                           .is2xxSuccessful());
    }

    @Test
    void shouldDeleteEmployee() throws JsonProcessingException {
        int id = 4;

        JsonNode initEmployees = getJsonNodeFromResponse(getAllEmployees().getBody());
        assertTrue(initEmployees.findValues("id").contains(IntNode.valueOf(id)));

        restTemplate.delete(getRootUrl() + "/employee/" + id);

        JsonNode outputEmployees = getJsonNodeFromResponse(getAllEmployees().getBody());
        assertFalse(outputEmployees.findValues("id").contains(IntNode.valueOf(id)));

    }

    @Test
    void shouldDeleteActiveEmployee() throws JsonProcessingException {
        int id = 3;

        JsonNode initEmployees = getJsonNodeFromResponse(getAllEmployees().getBody());
        assertTrue(initEmployees.findValues("id").contains(IntNode.valueOf(id)));

        restTemplate.delete(getRootUrl() + "/employee/active/" + id);

        JsonNode outputEmployees = getJsonNodeFromResponse(getAllEmployees().getBody());
        assertFalse(outputEmployees.findValues("id").contains(IntNode.valueOf(id)));
    }

    @Test
    void shouldUpdateEmployee() throws JsonProcessingException {
        Employee testEmp = getTestEmployee();
        ResponseEntity<Employee> postResponse = restTemplate.postForEntity(
                getRootUrl() + "/employee", testEmp, Employee.class);

        Long responseEmpId = postResponse.getBody().getId();

        assertNotNull(responseEmpId);
        testEmp.setId(responseEmpId);
        testEmp.setName("UpdatedName");

        restTemplate.put("/employee", testEmp);

        JsonNode responseEmployees = getJsonNodeFromResponse(getAllEmployees().getBody());

        assertTrue(responseEmployees.findValues("id")
                           .contains(IntNode.valueOf(Math.toIntExact(responseEmpId))));

        assertTrue(responseEmployees.findValues("name")
                   .contains(TextNode.valueOf("UpdatedName")));
    }

    @Test
    void shouldUpdateActiveEmployee() throws JsonProcessingException {
        ActiveEmployee actvEmp = getTestActiveEmployee();
        ResponseEntity<ActiveEmployee> postResponse = restTemplate.postForEntity(
                getRootUrl() + "/employee", actvEmp, ActiveEmployee.class);

        Long responseEmpId = postResponse.getBody().getId();

        assertNotNull(responseEmpId);
        actvEmp.setId(responseEmpId);
        actvEmp.setName("ActiveName");

        restTemplate.put("/employee", actvEmp);

        JsonNode responseEmployees = getJsonNodeFromResponse(getAllEmployees().getBody());

        assertTrue(responseEmployees.findValues("id")
                                    .contains(IntNode.valueOf(Math.toIntExact(responseEmpId))));

        assertTrue(responseEmployees.findValues("name")
                                    .contains(TextNode.valueOf("ActiveName")));
    }

    private ResponseEntity<String> getAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(getRootUrl() + "/employees", HttpMethod.GET, entity, String.class);
    }

    private JsonNode getJsonNodeFromResponse(String responseBody) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        return objMapper.readTree(responseBody);
    }

    public Employee getTestEmployee() {
        Employee emp = new Employee();
        emp.setName("Test");
        emp.setTeams(new ArrayList<>());
        return emp;
    }

    public ActiveEmployee getTestActiveEmployee() {
        ActiveEmployee actEmp = new ActiveEmployee();
        actEmp.setName("Test2");
        actEmp.setTeams(new ArrayList<>());
        actEmp.setSalary(1200d);
        actEmp.setDateOfEmployment(LocalDate.now());
        return actEmp;
    }
}