package dto;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.oneOf;

public class UserApi {

    public static void deleteUser(String accessToken){
        given()
                .header("Content-type", "application/json")
                .header("Authorization", accessToken)
                .when().log().all()
                .delete("/api/auth/user")
                .then().log().all()
                .statusCode((oneOf(200, 202)));
    }

    public static Response loginUser(LoginUser loginUser){
        Response response2 = given()
                .header("Content-type", "application/json")
                .body(loginUser)
                .when().log().all()
                .post("/api/auth/login");
        return response2;
    }

}
