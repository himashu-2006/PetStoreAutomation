package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
	Faker faker;
     User	userPayload;
     
     public Logger logger;
     
     
     
	@BeforeClass
	public void setupData() {
		 
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		logger = LogManager.getLogger(this.getClass());
		
	} 
	
	@Test(priority =1)
     public void testPostUser() {
	   
		logger.info("************* Creating user *************");
      	Response responce = UserEndPoints2.createUser(userPayload);
    	responce.then().log().all();
      	Assert.assertEquals(responce.getStatusCode(), 200);
      	
		logger.info("************* User is Created *************");
      }
	
//	@Test(priority=2)
//	public void testGetUserByName() {
//		
//	  Response response	=   UserEndPoints.readUser(this.userPayload.getUsername());
//		response.then().log().all();
//		Assert.assertEquals(response.getStatusCode(),200);
//			
//	}
	
	@Test(priority = 2)
	public void testGetUserByName() {
		
		logger.info("************* Reading user Info *************");
		
	    Response response = UserEndPoints2.readUser(this.userPayload.getUsername());
	    response.then().log().all();
	    Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 404);
	    
	    logger.info("*************  user Info is displayed *************");
	}	
	
	
	@Test(priority =3)
    public void testUpdateUserByName() {
	   
		logger.info("************* Updating  user *************");
		
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
     	Response responce = UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);
   	    responce.then().log().body().statusCode(200);
     	Assert.assertEquals(responce.getStatusCode(), 200);
     	logger.info("************* User is Updated *************");
     	Response responceAfterupdate= UserEndPoints.createUser(userPayload);
    	responceAfterupdate.then().log().all();
      	Assert.assertEquals(responceAfterupdate.getStatusCode(), 200);
     	
      
      	
     }
	 
	@Test(priority =4)
	public void testDeleteUserByName() {
		
	logger.info("************* Deleting user *************");
		 
	Response response =	UserEndPoints2.deleteUser(this.userPayload.getUsername());
	Assert.assertEquals(response.getStatusCode(), 200);
	
	logger.info("************* User Deleted *************");
	
   }
	
}
