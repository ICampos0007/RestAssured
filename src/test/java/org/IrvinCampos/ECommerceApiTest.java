package org.IrvinCampos;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.IrvinCampos.pojo.LoginRequest;
import org.IrvinCampos.pojo.LoginResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ECommerceApiTest {
    @Test
    public void LoginTest() {
       RequestSpecification requestSpecification =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("irvincampos76@gmail.com");
        loginRequest.setUserPassword("Atgatgnite1!");
       RequestSpecification requestSpecificationLogin = given().log().all().spec(requestSpecification).body(loginRequest);
       LoginResponse loginResponse = requestSpecificationLogin.when().post("/api/ecom/auth/login").then().extract().response().as(LoginResponse.class);

       System.out.println("token: " + loginResponse.getToken());
       System.out.println("userId: " +loginResponse.getUserId());
       System.out.println("message: " +loginResponse.getMessage());
    }
}
