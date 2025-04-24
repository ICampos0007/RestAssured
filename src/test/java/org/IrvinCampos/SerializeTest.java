package org.IrvinCampos;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.IrvinCampos.pojo.AddPlace;
import org.IrvinCampos.pojo.Location;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTest {
    @Test
    public void SerializingTest() {
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress("29, side layout, cohen 09");
        addPlace.setLanguage("French-IN");
        addPlace.setPhoneNumber("(+1) 123 456 7890");
        addPlace.setWebsite("https://rahulshettyacademy.com/");
        addPlace.setName("Front line house");
        List<String> myList = new ArrayList<>();
        myList.add("shoe park");
        myList.add("shop");
        addPlace.setTypes(myList);
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        addPlace.setLocation(location);
        Response response = given().log().all().queryParam("key","qaclick123").body(addPlace)
                .when().post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();

        String responseString = response.asString();
        System.out.println(responseString);
    }

}
