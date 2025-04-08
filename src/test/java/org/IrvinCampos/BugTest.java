package org.IrvinCampos;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class BugTest {
    @Test
    public void createABugTest() {
        RestAssured.baseURI = "https://irvincampos76.atlassian.net/";
        String createIssueResponse =given().header("Content-Type","application/json ")
                .header("Authorization","Basic aXJ2aW5jYW1wb3M3NkBnbWFpbC5jb206QVRBVFQzeEZmR0YwRGhiNGdaOHVsZF9BR1FRcGVxT2dWbzd6LVl5T3pYMWcxVlluYXVRODlfcWFYLTB5WjhoSm45bjVWS0NNd2VpNlNiWXJqdEdiVGJ6QUlGZHZiVEs5c0Jkd1BxVFNmc3U2V3NZSWJXMGozaklMS2tSbk8ybWN6WXFfalN6THc5SkNsSUl4dzNwRU1CZjJKSmNsTkRaSXU3eGRNYktFVXlsSC1uMmQtU3ZWSFQ0PTgzRjRBNEU5")
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"SCRUM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"Website items are not working - automation.\",\n" +
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
                .header("Authorization","Basic aXJ2aW5jYW1wb3M3NkBnbWFpbC5jb206QVRBVFQzeEZmR0YwRGhiNGdaOHVsZF9BR1FRcGVxT2dWbzd6LVl5T3pYMWcxVlluYXVRODlfcWFYLTB5WjhoSm45bjVWS0NNd2VpNlNiWXJqdEdiVGJ6QUlGZHZiVEs5c0Jkd1BxVFNmc3U2V3NZSWJXMGozaklMS2tSbk8ybWN6WXFfalN6THc5SkNsSUl4dzNwRU1CZjJKSmNsTkRaSXU3eGRNYktFVXlsSC1uMmQtU3ZWSFQ0PTgzRjRBNEU5")
                .multiPart("file", new File("C://Users//Irvin//Downloads//Metallic_shield_bug444.jpg")).log().all()
                .post("rest/api/3/issue/{key}/attachments").then().log().all().assertThat().statusCode(200);

    }
}
