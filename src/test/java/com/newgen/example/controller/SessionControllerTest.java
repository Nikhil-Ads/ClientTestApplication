/**
 * 
 */
package com.newgen.example.controller;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.newgen.example.exception.CustomException;
import com.newgen.example.model.LoginForm;
import com.newgen.example.service.SessionService;
import com.newgen.example.validation.InputValidator;

import lombok.extern.slf4j.Slf4j;

/**
 * @author nikhil.adlakha
 *
 */
@Slf4j
public class SessionControllerTest {
	
	private static final String ORG = "ECM";
	private static final String USERNAME = "niks8adlakha@gmaill.com";
	private static final String PASSWORD = "System@123";
	
	private SessionController sessionController;
	
	@Mock
	private SessionService sessionService;
	
	private static InputValidator inputValidator; 
	
	@BeforeAll
	public static void logTesting() throws InterruptedException{
		inputValidator=new InputValidator();
		Thread.sleep(2000);
		log.info("==========================================================");
		log.info("Testing sessionControllerTest:");
		log.info("==========================================================");
	}

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);		
		sessionController=new SessionController(inputValidator, sessionService);
	}
	
	@Test
	public void testNullORG_UserLogin(){		
		log.info("==========================================================");
		log.info("Testing userLogin method with Null Org :");
	
		//given
		LoginForm loginForm=new LoginForm(USERNAME,PASSWORD);
		String entity=loginForm.toString();
		
		//when
		Mockito.when(sessionService.login(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(entity);
		assertThatNullPointerException().isThrownBy(() -> {
			sessionController.userLogin(null, loginForm);	
		});	
		log.info("NullPointerException is thrown");
		log.info("==========================================================");
	}
	
	@Test
	public void testInvalidOrg_UserLogin() {
		log.info("==========================================================");
		log.info("Testing userLogin method with Invalid Org :");
	
		//given
		String orgTest = new String("EJM");
		
		LoginForm loginForm=new LoginForm(USERNAME,PASSWORD);
		String entity=loginForm.toString();
		
		//when
		Mockito.when(sessionService.login(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(entity);
		log.info(assertThatExceptionOfType(CustomException.class).isThrownBy(() -> {
						sessionController.userLogin(orgTest, loginForm);	
					}).getWritableAssertionInfo().descriptionText());	
		log.info("CustomException is thrown");
		log.info("==========================================================");		
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
		Mockito.when(sessionService.login(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(entity);
		
		ResponseEntity<String> e = sessionController.userLogin(ORG, loginForm);
		
		//then
		assertEquals(response, e);		
		assertEquals(e.getBody(), loginForm.toString());
		verify(sessionService,times(1)).login(ArgumentMatchers.anyString(), ArgumentMatchers.any());
		
		log.info("Entity returned validated");
		log.info("Entity String value Validated");
		log.info("Service Layer Calls Validated. Number of calls made: 1");
		log.info("==========================================================");
	}
	
}
