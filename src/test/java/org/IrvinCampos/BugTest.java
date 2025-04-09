package org.IrvinCampos;

import io.restassured.RestAssured;
import io.restassured.config.Config;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class BugTest {
    @Test
    public void createABugTest() throws IOException {
        Properties properties = new Properties();
//        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "src//main//resources//config.properties");
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\config.properties");

        properties.load(fileInputStream);
        RestAssured.baseURI = "https://irvincampos76.atlassian.net/";
        String createIssueResponse =given().header("Content-Type","application/json")

                .header("Authorization", properties.getProperty("apiKey"))
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"SCRUM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"Website items are not working - automation2.\",\n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}\n")
                .log().all()
                .post("rest/api/3/issue").then().log().all().assertThat().statusCode(201).extract().response().asString();
        JsonPath jsonPath = new JsonPath(createIssueResponse);
        String issueID = jsonPath.getString("id");
        System.out.println(issueID);

//        Add Attachement
        given().pathParam("key", issueID)
                .header("X-Atlassian-Token","no-check")
                .header("Authorization", properties.getProperty("apiKey"))
                .multiPart("file", new File("C://Users//Irvin//Downloads//Metallic_shield_bug444.jpg")).log().all()
                .post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);

    }
}
