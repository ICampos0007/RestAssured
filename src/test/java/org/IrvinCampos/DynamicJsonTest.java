package org.IrvinCampos;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.IrvinCampos.Utils.ReUsableMethods;
import org.IrvinCampos.files.Payload;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJsonTest {
    @Test(dataProvider = "BooksData")
    public void addBook() {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type","application/json")
                .body(Payload.addBook("adsfs","6464")).when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath jsonPath = ReUsableMethods.rawToJson(response);
        String ID = jsonPath.get("ID");
        System.out.println(ID);
    }
    @DataProvider(name = "BooksData")
    public Object[][] getData() {
//        array = collection of elements
//        multidimensional array = collection of arrays
        return new Object[][] {{"ojfwty","9363"}, {"qfsd","1523"}, {"ghfds","86453"}};
    }
}
