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
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type","application/json")
                .body(Payload.addBook(aisle,isbn)).when().post("/Library/Addbook.php")
                .then().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath jsonPath = ReUsableMethods.rawToJson(response);
        String ID = jsonPath.get("ID");
        System.out.println(ID);
    }

    @Test(dataProvider = "BooksData")
    public void deleteBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type","application/json")
                .body(Payload.deleteBook(isbn,aisle)).when().post("/Library/DeleteBook.php")
                .then().assertThat().statusCode(200).extract().response().asString();

        JsonPath jsonPath = ReUsableMethods.rawToJson(response);
        String ID = isbn + aisle;
        System.out.println(ID);
        System.out.println(response);
    }
    @DataProvider(name = "BooksData")
    public Object[][] getData() {
//        array = collection of elements
//        multidimensional array = collection of arrays
        return new Object[][] {{"ojfwtyz","9363"}, {"qfsdz","1523"}, {"ghfdsz","86453"}};
    }
}
