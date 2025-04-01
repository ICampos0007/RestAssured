package org.IrvinCampos.Utils;

import io.restassured.path.json.JsonPath;
import org.IrvinCampos.files.Payload;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidationTest {
    @Test
    public void SumOfCourses() {
        JsonPath jsonPath = new JsonPath(Payload.CoursePrice());
        int count = jsonPath.get("courses.size()");

        int sum = 0;

        for (int i = 0; i<count; i++) {
            int price = jsonPath.get("courses["+i+"].price");
            int copies = jsonPath.get("courses["+i+"].copies");
            int amount = price * copies;
            System.out.println(amount);
            sum = sum + amount;
        }
        System.out.println(sum);
        int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum,purchaseAmount);

    }
}
