package org.IrvinCampos.Utils;

import io.restassured.path.json.JsonPath;
import org.IrvinCampos.files.Payload;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath jsonPath = new JsonPath(Payload.CoursePrice());
//        Print number of courses returned by API
        int count = jsonPath.get("courses.size()");
        System.out.println(count);
//        Print purchase amount
        int totalAmount = jsonPath.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);
        String firstTitle = jsonPath.getString("courses[0].title");
        System.out.println(firstTitle);

//        Print all course titles and their respective prices
        for (int i =0; i<count; i++) {
            String courseTitles = jsonPath.get("courses["+i+"].title");
            System.out.println(courseTitles);
            System.out.println(jsonPath.get("courses["+i+"].price").toString());
        }
    }

}
