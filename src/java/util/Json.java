/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import org.json.JSONObject;

public class Json {
    public static String getValueOfParamInJsonString(String jsonString, String param) {
        JSONObject jsonObject = new JSONObject(jsonString);
        String value = jsonObject.getString(param);
        return value;
    }
}