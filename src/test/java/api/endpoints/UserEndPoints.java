/*package api.endpoints;

import org.testng.annotations.Test;

import api.payload.User;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
//UserEndPoints.Java
//Created for perform Create , Read , Update , Delete request to the user API.

public class UserEndPoints {

	public static Response createUser(User payload) {

		Response responce = given().
				contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when(). 
				post(Routes.post_url);

		return responce;
		
	}
	
	public static Response readUser(String userName) {
		
		Response responce = given()
				.pathParam("username", userName)
				
				.when() 
				.get(Routes.post_url);
		
		return responce;
		
	}
	
	public static Response updateUser(String userName , User payload) {

		Response responce = given().
				contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)

				.when(). 
				post(Routes.post_url);

		return responce;
		
	}
	
	

public static Response deleteUser(String userName) {
		
		Response responce = given()
				.pathParam("username", userName)
				
				.when() 
				.delete(Routes.post_url);
		
		return responce;
		
	}
	
	
	
}
*/

package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserEndPoints {

    public static Response createUser(User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(Routes.post_url);
    }

    public static Response readUser(String userName) {
        return given()
                .pathParam("username", userName)
        .when()
                .get(Routes.get_url);   // ✅ Fixed here
    }

    public static Response updateUser(String userName, User payload) {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
        .when()
                .put(Routes.update_url); // ✅ Should be PUT
    }

    public static Response deleteUser(String userName) {
        return given()
                .pathParam("username", userName)
        .when()
                .delete(Routes.delete_url); // ✅ Should be delete_url
    }
}


