/**
 * 
 */
package com.newgen.unit.example.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newgen.example.controller.SessionController;
import com.newgen.example.model.LoginForm;
import com.newgen.example.service.SessionService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nikhil.adlakha
 *
 */
@Slf4j
public class SessionControllerTest {
	
	private static final String ORG = "ECM";
	private static final String USERNAME = "niks8adlakha@gmaill.com";
	private static final String PASSWORD = "XYZ";
	
	private SessionController sessionController;
	
	@Mock
	private SessionService sessionService;
	
	@BeforeAll
	public static void logTesting(){
		log.info("==========================================================");
		log.info("Testing Session Controller Unit Tests:");
		log.info("==========================================================");
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);		
		sessionController=new SessionController(sessionService);		
	}
	
	@Test
	public void testUserLogin() throws Exception{		
		log.info("==========================================================");
		log.info("Testing userLogin method:");
		
		//given
		LoginForm loginForm=new LoginForm(USERNAME,PASSWORD);
		String entity=loginForm.toString();
		
		ResponseEntity<String> response=new ResponseEntity<>(entity,HttpStatus.OK);
		//when
		Mockito.when(sessionService.login(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(response);
		
		ResponseEntity<String> e = sessionController.userLogin(ORG, loginForm);
		
		//then
		assertEquals(response, e);		
		assertEquals(e.getBody(), loginForm.toString());
		verify(sessionService,times(1)).login(ArgumentMatchers.anyString(), ArgumentMatchers.any());
		
		log.info("Entity returned validated");
		log.info("Entity String value Validated");
		log.info("Service Layer Calls Validated. Number of calls made: 1");
	}
	
	@AfterEach
	public void separate() {
		log.info("==========================================================");
	}
}
