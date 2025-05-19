package org.IrvinCampos;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.IrvinCampos.pojo.LoginRequest;
import org.IrvinCampos.pojo.LoginResponse;
import org.IrvinCampos.pojo.OrderDetails;
import org.IrvinCampos.pojo.Orders;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
       String userID = loginResponse.getUserId();

//       Create Product

       String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2ODE5MzNiMWZkMmFmMWM5OWUxMDdmNzIiLCJ1c2VyRW1haWwiOiJpcnZpbmNhbXBvczc2QGdtYWlsLmNvbSIsInVzZXJNb2JpbGUiOjMyMTQzMTI0MTQsInVzZXJSb2xlIjoiY3VzdG9tZXIiLCJpYXQiOjE3NDY0ODIzNDAsImV4cCI6MTc3ODAzOTk0MH0.YIZfJUOAY5hQ5dpcqlpZK8GvtDfnQIkIq5bF6Fke3BI";

        RequestSpecification CreateProductRequest =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token).build();

        RequestSpecification addingProductRequest = given().log().all().spec(CreateProductRequest).param("productName","Laptop")
                .param("productAddedBy",userID)
                .param("productCategory","fashion")
                .param("productSubCategory","shirts")
                .param("productPrice","11500")
                .param("productDescription","Addias Originals")
                .param("productFor","women")
                .multiPart("productImage", new File("C://Users//Irvin//Desktop//miscellaneous//jett cat.jpg"));

        String addProductResponse = addingProductRequest.when().post("/api/ecom/product/add-product")
                .then().log().all().extract().response().asString();
        JsonPath jsonPath = new JsonPath(addProductResponse);
        String productId = jsonPath.get("productId");

//        Create Order
        RequestSpecification CreateOrderRequest =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token).setContentType(ContentType.JSON).build();

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setCountry("India");
        orderDetails.setProductOrderId(productId);

        List<OrderDetails> orderDetailList = new ArrayList<OrderDetails>();
        orderDetailList.add(orderDetails);
        Orders orders = new Orders();
        orders.setOrders(orderDetailList);
        RequestSpecification createOrderRequest = given().log().all().spec(CreateOrderRequest).body(orders);
        String responseAddOrder = createOrderRequest.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
        System.out.println(responseAddOrder);

//        Delete Order
        RequestSpecification DeleteOrderRequest =  new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",token).setContentType(ContentType.JSON).build();

        RequestSpecification DeleteProductRequest =  given().log().all().spec(DeleteOrderRequest).pathParam("productId", productId);
        String deleteProductResponse = DeleteProductRequest.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();

        JsonPath jsonPath1 = new JsonPath(deleteProductResponse);
        Assert.assertEquals("Product Deleted Successfully", jsonPath1.get("message"));



    }


}
