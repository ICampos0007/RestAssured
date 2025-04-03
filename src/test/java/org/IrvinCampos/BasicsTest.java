package org.IrvinCampos;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.IrvinCampos.Utils.ReUsableMethods;
import org.IrvinCampos.files.Payload;
import org.testng.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicsTest {
    public static void main(String[] args) throws IOException {
        //        validate if add place api is working
//        given - all input details
//        when- submit the API resource,http method
//        then- validate the response
//        content of the file to String -> content of file can convert into Byt -> Byte data to string
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String response = given().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Files.readAllBytes(Paths.get("C://Users//Irvin//Downloads//addPlace.json"))).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response); // For parsing JSON
        String placeID = js.getString("place_id");

        System.out.println(placeID);

//        Update place

        // Update place with corrected JSON and endpoint
        String newAddress= "Summer Walk, Africa";
        String updateResponse = given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\": \"" + placeID + "\",\n" +
                        "\"address\": \""+newAddress+"\",\n" +  // Corrected double quotes
                        "\"key\": \"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")  // Corrected endpoint
                .then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated".trim()))
                .extract().response().asString();

        System.out.println(updateResponse);

//        Get Place
      String getPlaceResponse = given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",placeID)
                .when().get("maps/api/place/get/json")  // <-- Corrected endpoint
                .then().assertThat().statusCode(200).log().all().extract().asString();


        JsonPath jsonPath1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = jsonPath1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress,newAddress);

    }
}
