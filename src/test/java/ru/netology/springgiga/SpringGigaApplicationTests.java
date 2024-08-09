package ru.netology.springgiga;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringGigaApplicationTests {
    private int startDevPort = 8080;
    private int startProdPort = 8081;

    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private GenericContainer<?> devapp = new GenericContainer<>("devapp:1.0")
            .withExposedPorts(startDevPort)
            .waitingFor(Wait.forListeningPort());

    @Container
    private GenericContainer<?> prodapp = new GenericContainer<>("prodapp:1.0")
            .withExposedPorts(startProdPort)
            .waitingFor(Wait.forListeningPort());

    @Test
    void contextLoads() {
        Integer devport = devapp.getMappedPort(startDevPort);
        Integer prodport = prodapp.getMappedPort(startProdPort);

        ResponseEntity<String> dev = restTemplate.getForEntity("http://localhost:" + devport + "/profile", String.class);
        ResponseEntity<String> prod = restTemplate.getForEntity("http://localhost:" + prodport + "/profile", String.class);

        System.out.println("DEV: " + dev.getBody());
        System.out.println("PROD: " + prod.getBody());
    }
}