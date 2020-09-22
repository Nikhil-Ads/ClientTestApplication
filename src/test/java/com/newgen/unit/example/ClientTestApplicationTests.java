package com.newgen.unit.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.newgen.example.ClientTestApplication;

@ContextConfiguration(classes = ClientTestApplication.class)
@SpringBootTest
class ClientTestApplicationTests {

	@Test
	void contextLoads() {
	}

}
