package org.IrvinCampos;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.IrvinCampos.pojo.AddPlace;
import org.IrvinCampos.pojo.Location;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.responseSpecification;

public class SpecBuilderTest {
    @Test public void SBuildTest() {
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

        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("key","qaclick123")
                .setContentType(ContentType.JSON).build();


        RequestSpecification response = given().spec(requestSpecification).body(addPlace);
//        RequestSpecification requestSpecification1 = new RequestSpecBuilder();

        ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
        RequestSpecification res=given().spec(requestSpecification)
                .body(addPlace);
        Response response1 =res.when().post("/maps/api/place/add/json").
                then().spec(resspec).extract().response();

        String responseString=response1.asString();
        System.out.println(responseString);


    }
}
