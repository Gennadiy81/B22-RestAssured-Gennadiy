package com.cybertek.day5;

import com.cybertek.utiliteis.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class JSONtoJAVATest extends SpartanTestBase {


        @DisplayName("GET one Spartan and deserialize to Map")
        @Test
        public void oneSpartanToMap(){

            Response response = given().pathParam("id", 15)
                    .when().get("/api/spartans/{id}")
                    .then().statusCode(200).extract().response();

            //get the json and convert it to the map

            Map<String,Object> jsonMap = response.as(Map.class);
            System.out.println(jsonMap.toString());

            String actualName = (String) jsonMap.get("name");
            assertThat(actualName, is("Meta"));


        }

        @DisplayName("Get all spartans to Java data xtructure")
        @Test
        public void getAllSpartan(){
           Response response =  get("/api/spartans").then().statusCode(200).extract().response();

            List<Map<String, Object>> jsonList = response.as(List.class);

            System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));

            Map<String, Object> spartan3 = jsonList.get(2);
            System.out.println(spartan3);


        }
    }
