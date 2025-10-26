package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

public class UserEndPoints2 {

    // Method created for getting URLs from properties file 
    static ResourceBundle getURL() {
        ResourceBundle routes = ResourceBundle.getBundle("routes");
        return routes;
    }

    public static Response createUser(User payload) {
        String post_url = getURL().getString("post_url");

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
        .when()
                .post(post_url);
    }

    public static Response readUser(String userName) {
        String get_url = getURL().getString("get_url");

        return given()
                .pathParam("username", userName)
        .when()
                .get(get_url);
    }

    public static Response updateUser(String userName, User payload) {
        String update_url = getURL().getString("update_url");

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
        .when()
                .put(update_url);
    }

    public static Response deleteUser(String userName) {
        // ✅ FIX: use lowercase key name
        String delete_url = getURL().getString("delete_url");

        return given()
                .pathParam("username", userName)
        .when()
                .delete(delete_url);
    }
}


