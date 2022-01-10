package com.example.hibernate5mysqlcrudrestapi;

import com.example.hibernate5mysqlcrudrestapi.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootCrudRestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootCrudRestApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRoolUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null,headers);

        ResponseEntity<String> response = restTemplate.exchange(getRoolUrl()
                +"/users", HttpMethod.GET,entity,String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetUserById() {
        User user = restTemplate.getForObject(getRoolUrl() + "/users/1",User.class);
        System.out.println(user.getFirstName());
        assertNotNull(user);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmailId("admin@gmail.com");
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setCreatedBy("admin");
        user.setUpdatedBy("admin");

        ResponseEntity<User> postResponse = restTemplate.postForEntity(getRoolUrl() +
                "/users", user, User.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
        int id = 1;
        User user = restTemplate.getForObject(getRoolUrl() + "/users/" + id,
                User.class);
        user.setFirstName("admin1");
        user.setLastName("admin2");

        restTemplate.put(getRoolUrl() + "/users/" + id,user);

        User updatedUser = restTemplate.getForObject(getRoolUrl() + "/users/" + id,
                User.class);
        assertNotNull(updatedUser);
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        User user = restTemplate.getForObject(getRoolUrl() + "/users/" + id,
                User.class);
        assertNotNull(user);

        restTemplate.delete(getRoolUrl() + "/users/" + id);

        try {
            user = restTemplate.getForObject(getRoolUrl() + "/users/" + id,
                    User.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}
