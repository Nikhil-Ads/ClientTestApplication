package com.newgen.integration.example.controller;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.newgen.GeneralTest;
import com.newgen.example.ClientTestApplication;
import com.newgen.example.controller.SessionController;
import com.newgen.example.model.LoginForm;

import lombok.extern.slf4j.Slf4j;

@ContextConfiguration(classes = ClientTestApplication.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
public class SessionControllerTest extends GeneralTest {
	
	@Autowired
	private SessionController sessionController;
	
	@Value("${emailId}")
	private String EMAIL;
	
	@Value("${password}")
	private String PASSWORD;
	
	@Value("${accessToken}")
	private String TOKEN;
	
	@Value("/session/login")
	private String login_url; 
	
	@Value("/session/logout")
	private String logout_url;

	@Value("")
	private String getTokens_url;

	@Value("")
	private String validateToken_url;

	private MockMvc mockMvc;
	
	private MvcResult results;
	
	@BeforeTestClass
 	public void logTesting(){
		log.info("==========================================================");
		log.info("Testing Session Controller Integration Tests:");
		log.info("==========================================================");
	}
	
	@BeforeEach
	public void setUp(){
		mockMvc=MockMvcBuilders.standaloneSetup(sessionController).build();
		results=null;
	}
	
	@Test
	@Order(1)
	public void testNullORG_UserLogin() throws Exception{
		log.info("Test: Testing userLogin method with Null Org");
		log.info("==========================================================");
		log.info(entering("testNullORG_UserLogin"));
		log.info("==========================================================");
		
		//given
		LoginForm loginForm=new LoginForm(EMAIL,PASSWORD);

		HttpHeaders headers=new HttpHeaders();
		headers.set("org", null);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", null));
		log.info(inputParameter("Body", "loginDTO", loginForm.toString()));
		log.info("==========================================================");
		//when
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
			results= mockMvc.perform(MockMvcRequestBuilders.post(login_url)										
					.headers(headers)
					.content(new ObjectMapper().writeValueAsString(loginForm))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andReturn();	
		});
		log.info("==========================================================");
		log.info("Response:");
		log.info(null);		
	}
	
	@Test
	@Order(2)
	public void testEmptyORG_UserLogin() throws Exception{
		log.info("Test: Testing userLogin method with Empty Org");
		log.info("==========================================================");
		log.info(entering("testEmptyORG_UserLogin"));
		log.info("==========================================================");
		
		//given
		final String ORG=new String();
		LoginForm loginForm=new LoginForm(EMAIL,PASSWORD);

		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Body", "loginDTO", loginForm.toString()));
		log.info("==========================================================");
		//when
		results =mockMvc.perform(MockMvcRequestBuilders.post(login_url)										
				.headers(headers)
				.content(new ObjectMapper().writeValueAsString(loginForm))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Please enter a valid organization value"))
				.andReturn();	
		
		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());		
	}
	
	@Test
	@Order(3)
	public void testInvalidOrg_UserLogin() throws Exception{
		log.info("Test: Testing userLogin method with Invalid Org");
		log.info("==========================================================");
		log.info(entering("testInvalidOrg_UserLogin"));
		log.info("==========================================================");	
		//given
		String orgTest = new String("EJM");
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", orgTest);		
		
		LoginForm loginForm=new LoginForm(EMAIL,PASSWORD);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", orgTest));
		log.info(inputParameter("Body", "loginDTO", loginForm.toString()));
		log.info("==========================================================");
		//when
		results =mockMvc.perform(MockMvcRequestBuilders.post(login_url)										
				.headers(headers)
				.content(new ObjectMapper().writeValueAsString(loginForm))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Please enter a valid organization value"))
				.andReturn();		
		
		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());		
		
	}
	
	@Test
	@Order(4)
	public void testNullLoginDTO_UserLogin() throws Exception{
		log.info("Test: Testing userLogin method with Null LoginDTO");
		log.info("==========================================================");
		log.info(entering("testNullLoginDTO_UserLogin"));
		log.info("==========================================================");
		
		//given
		final String ORG=new String("ECM");
		LoginForm loginForm=null;

		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Body", "loginDTO", null));
		log.info("==========================================================");
		//when
		results= mockMvc.perform(MockMvcRequestBuilders.post(login_url)										
				.headers(headers)
				.content(new ObjectMapper().writeValueAsString(loginForm))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		log.info("==========================================================");
		log.info("Response:");
		log.info(null);		
	}
	
	@Test
	@Order(5)
	public void testEmptyLoginDTO_UserLogin() throws Exception{
		log.info("Test: Testing userLogin method with Empty Email and Password");
		log.info("==========================================================");
		log.info(entering("testEmptyLoginDTO_UserLogin"));
		log.info("==========================================================");	
		//given
		final String ORG="ECM";
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		
		final String EMAIL=new String();
		final String PASSWORD=new String();
		LoginForm loginForm=new LoginForm(EMAIL,PASSWORD);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Body", "loginDTO", loginForm.toString()));
		log.info("==========================================================");
		//when
		results=mockMvc.perform(MockMvcRequestBuilders.post(login_url)										
						.headers(headers)
						.content(new ObjectMapper().writeValueAsString(loginForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Email must not be blank!"))
				.andReturn();
		
		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());		
	}
	
	@Test
	@Order(6)
	public void testInValidEmail_UserLogin() throws Exception{
		log.info("Test: Testing userLogin method with Invalid Email");
		log.info("==========================================================");
		log.info(entering("testInValidEmail_UserLogin"));
		log.info("==========================================================");			
		//given
		final String ORG="ECM";
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		
		final String EMAIL="niksdhsdh";		
		LoginForm loginForm=new LoginForm(EMAIL,PASSWORD);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Body", "loginDTO", loginForm.toString()));
		log.info("==========================================================");
		//when
		results=mockMvc.perform(MockMvcRequestBuilders.post(login_url)										
						.headers(headers)
						.content(new ObjectMapper().writeValueAsString(loginForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Email is not Valid."))
				.andReturn();
		
		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());				
	}
	
	@Test
	@Order(7)
	public void testInValidPassword_UserLogin() throws Exception{
		log.info("Test: Testing userLogin method with Invalid Password");
		log.info("==========================================================");
		log.info(entering("testInValidPassword_UserLogin"));
		log.info("==========================================================");	
		//given
		final String ORG="ECM";
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		
		final String PASSWORD="niksdhsdh";
		LoginForm loginForm=new LoginForm(EMAIL,PASSWORD);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Body", "loginDTO", loginForm.toString()));
		log.info("==========================================================");
		//when
		results=mockMvc.perform(MockMvcRequestBuilders.post(login_url)										
						.headers(headers)
						.content(new ObjectMapper().writeValueAsString(loginForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Authentication Failed"))
				.andReturn();
		
		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());		
	}
	
	@Test
	@Order(8)
	public void testInValidEmailAndPassword_UserLogin() throws Exception{
		log.info("Test: Testing userLogin method with Invalid Email and Password");
		log.info("==========================================================");
		log.info(entering("testInValidEmailAndPassword_UserLogin"));
		log.info("==========================================================");	
		//given
		final String ORG="ECM";
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		
		final String EMAIL="niksdhsdh@gmail.com";
		final String PASSWORD="niksdhsdh";
		LoginForm loginForm=new LoginForm(EMAIL,PASSWORD);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Body", "loginDTO", loginForm.toString()));
		log.info("==========================================================");
		//when
		results=mockMvc.perform(MockMvcRequestBuilders.post(login_url)										
						.headers(headers)
						.content(new ObjectMapper().writeValueAsString(loginForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("EmailId does not exist"))
				.andReturn();
		
		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());		
	}

	@Test
	@Order(9)
	public void testValid_UserLogin() throws Exception{
		log.info("Test: Testing userLogin method with Valid Data");
		log.info("==========================================================");
		log.info(entering("testValid_UserLogin"));
		log.info("==========================================================");	
		//given
		final String ORG="ECM";
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		
		LoginForm loginForm=new LoginForm(EMAIL,PASSWORD);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Body", "loginDTO", loginForm.toString()));
		log.info("==========================================================");
		//when
		results=mockMvc.perform(MockMvcRequestBuilders.post(login_url)										
						.headers(headers)
						.content(new ObjectMapper().writeValueAsString(loginForm))
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.accessToken").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.refreshToken").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.userId").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.userEmail").value(EMAIL))
				.andReturn();
		
		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());		
	}

	@Test
	@Order(10)
	public void testNullORG_UserLogout() throws Exception{
		log.info("Test: Testing userLogout method with Null Org");
		log.info("==========================================================");
		log.info(entering("testNullORG_UserLogout"));
		log.info("==========================================================");
		
		//given
		final String ORG=null;
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		headers.set("accessToken", TOKEN);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Header", "accessToken", TOKEN));
		log.info("==========================================================");
		//when
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
			results= mockMvc.perform(MockMvcRequestBuilders.get(logout_url)										
					.headers(headers)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andReturn();	
		});
		log.info("==========================================================");
		log.info("Response:");
		log.info(null);		
	}
	
	@Test
	@Order(11)
	public void testEmptyORG_UserLogout() throws Exception{
		log.info("Test: Testing userLogout method with Empty Org");
		log.info("==========================================================");
		log.info(entering("testEmptyORG_UserLogout"));
		log.info("==========================================================");
		
		//given
		final String ORG=new String();

		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		headers.set("accessToken", TOKEN);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Header", "accessToken", TOKEN));
		log.info("==========================================================");
		//when
		results =mockMvc.perform(MockMvcRequestBuilders.get(logout_url)										
				.headers(headers)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();	
		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());		
	}
	
	@Test
	@Order(12)
	public void testInvalidORG_UserLogout() throws Exception{
		log.info("Test: Testing userLogout method with Invalid Org");
		log.info("==========================================================");
		log.info(entering("testEmptyORG_UserLogout"));
		log.info("==========================================================");
		
		//given
		final String ORG=new String("EGM");

		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		headers.set("accessToken", TOKEN);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Header", "accessToken", TOKEN));
		log.info("==========================================================");
		//when
		results =mockMvc.perform(MockMvcRequestBuilders.get(logout_url)										
				.headers(headers)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Please enter a valid organization value"))
				.andReturn();	
		
		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());		
	}

	@Test
	@Order(13)
	public void testNullToken_UserLogout() throws Exception{
		log.info("Test: Testing userLogout method with Null Access Token");
		log.info("==========================================================");
		log.info(entering("testNullToken_UserLogout"));
		log.info("==========================================================");
		
		//given		
		final String ORG=new String("ECM");
		TOKEN=null;
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		headers.set("accessToken", TOKEN);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Header", "accessToken", TOKEN));
		log.info("==========================================================");
		//when
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
			results= mockMvc.perform(MockMvcRequestBuilders.get(logout_url)										
					.headers(headers)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andReturn();	
		});
		log.info("==========================================================");
		log.info("Response:");
		log.info(null);		
	}
	
	@Test
	@Order(14)
	public void testEmptyToken_UserLogout() throws Exception{
		log.info("Test: Testing userLogout method with Empty Access Token");
		log.info("==========================================================");
		log.info(entering("testEmptyToken_UserLogout"));
		log.info("==========================================================");
		
		//given
		final String ORG=new String("ECM");
		TOKEN=new String();
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		headers.set("accessToken", TOKEN);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Header", "accessToken", TOKEN));
		log.info("==========================================================");
		//when
		results =mockMvc.perform(MockMvcRequestBuilders.get(logout_url)										
				.headers(headers)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();	
		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());		
	}
	
	@Test
	@Order(15)
	public void testInvalidToken_UserLogout() throws Exception{
		log.info("Test: Testing userLogout method with Invalid Access Token");
		log.info("==========================================================");
		log.info(entering("testInvalidToken_UserLogout"));
		log.info("==========================================================");
		
		//given
		final String ORG=new String("ECM");
		TOKEN=new String("sahdsjdhjghrgurhuiofhsriofhslfkjsfhlkdhf");

		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		headers.set("accessToken", TOKEN);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Header", "accessToken", TOKEN));
		log.info("==========================================================");
		//when
		results =mockMvc.perform(MockMvcRequestBuilders.get(logout_url)										
				.headers(headers)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();	

		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());		
	}
	
	@Test
	@Order(16)
	public void testNullORGandToken_UserLogout() throws Exception{
		log.info("Test: Testing userLogout method with Null Org and Access Token");
		log.info("==========================================================");
		log.info(entering("testNullORGandToken_UserLogout"));
		log.info("==========================================================");
		
		//given		
		final String ORG=null;
		TOKEN=null;
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		headers.set("accessToken", TOKEN);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Header", "accessToken", TOKEN));
		log.info("==========================================================");
		//when
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
			results= mockMvc.perform(MockMvcRequestBuilders.get(logout_url)										
					.headers(headers)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON))
					.andReturn();	
		});
		log.info("==========================================================");
		log.info("Response:");
		log.info(null);		
	}

	@Test
	@Order(17)
	public void testInvalidORGAndToken_UserLogout() throws Exception{
		log.info("Test: Testing userLogout method with Invalid Org and Access Token");
		log.info("==========================================================");
		log.info(entering("testInvalidORGAndToken_UserLogout"));
		log.info("==========================================================");
		
		//given
		final String ORG=new String("EGM");
		TOKEN=new String("shduodhfiruofgerhgoeffgyefo");

		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		headers.set("accessToken", TOKEN);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Header", "accessToken", TOKEN));
		log.info("==========================================================");
		//when
		results =mockMvc.perform(MockMvcRequestBuilders.get(logout_url)										
				.headers(headers)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Please enter a valid organization value"))
				.andReturn();	
		
		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());		
	}

	@Test
	@Order(18)
	public void testValid_UserLogout() throws Exception{
		log.info("Test: Testing userLogout method with Valid Data");
		log.info("==========================================================");
		log.info(entering("testValid_UserLogout"));
		log.info("==========================================================");
		
		//given
		final String ORG=new String("ECM");
		
		HttpHeaders headers=new HttpHeaders();
		headers.set("org", ORG);
		headers.set("accessToken", TOKEN);
		
		log.info("Parameters Passed:");
		log.info(parameters());
		log.info(inputParameter("Header", "org", ORG));
		log.info(inputParameter("Header", "accessToken", TOKEN));
		log.info("==========================================================");
		//when
		results =mockMvc.perform(MockMvcRequestBuilders.get(logout_url)										
				.headers(headers)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("1"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.statusMessage").value("success"))
				.andReturn();	
		
		log.info("==========================================================");
		log.info("Response:");
		log.info(results.getResponse().getContentAsString());		
	}
	
	@AfterEach
	public void separate(){
		log.info("==========================================================");				
	}
	

}
