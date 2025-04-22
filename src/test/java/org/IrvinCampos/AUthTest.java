package org.IrvinCampos;

import io.restassured.path.json.JsonPath;
import org.IrvinCampos.pojo.Api;
import org.IrvinCampos.pojo.Courses;
import org.IrvinCampos.pojo.GetCourse;
import org.IrvinCampos.pojo.WebAutomation;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class AUthTest {
    @Test public void AuthorizationTokenTest() {
        String response = given()
                .formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .formParam("scope","trust")
                .when().log().all()
                .post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
        System.out.println(response);
        JsonPath jsonPath = new JsonPath(response);
        String accessToken = jsonPath.getString("access_token");
        System.out.println(accessToken);

        GetCourse getCourse = given()
                .queryParam("access_token",accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);

        System.out.println(getCourse.getLinkedIn());
        System.out.println(getCourse.getInstructor());
        System.out.println(getCourse.getCourses().getApi().get(1).getCourseTitle());
        List<Api> apiCourses = getCourse.getCourses().getApi();
        for (int i =0; i < apiCourses.size(); i++) {
            if (apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

        List<WebAutomation> getAllCourses = getCourse.getCourses().getWebAutomation();
        for (int i= 0; i < getAllCourses.size(); i++) {
            System.out.println(getAllCourses.get(i).getCourseTitle());
        }

    }
}
