package org.IrvinCampos.Utils;

import io.restassured.path.json.JsonPath;

public class ReUsableMethods {
    public static JsonPath rawToJson(String response) {
        JsonPath jsonPath = new JsonPath(response);
        return jsonPath;// For parsing JSON

    }
}
