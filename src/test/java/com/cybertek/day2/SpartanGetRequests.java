package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {
    String baseUrl = "http://100.26.214.41:8000";

    @Test
    public void test1(){

//    Given Accept type application/json
//    When user send GET request to api/spartans end point
//    Then status code must 200
//    And response Content Type must be application/json
//    And response body should include spartan result
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                       .get(baseUrl + "/api/spartans");

//printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());


        System.out.println("response.contentType() = " + response.contentType());

        response.prettyPrint();

        Assertions.assertEquals(response.statusCode(), 200);

        Assertions.assertEquals(response.contentType(), "application/json");


    }
   /*
        Given accept header is application/json
        When users sends a get request to /api/spartans/3
        Then status code should be 200
        And content type should be application/json
        and json body should contain Fidole
     */

    @DisplayName("GET one spartan /api/spartans/3 and verify")
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get(baseUrl + "/api/spartans/3");


        //verify status code 200
        Assertions.assertEquals(200,response.statusCode());

        //verify content type
        Assertions.assertEquals("application/json",response.contentType());

        //verify json body contains Fidole
        Assertions.assertTrue(response.body().asString().contains("Fidole"));

    }
    @DisplayName("Get request to /api/hello")
    @Test
    public void test3(){
        Response response = RestAssured.when().get(baseUrl + "/api/hello");


        //verify status code 200
        Assertions.assertEquals(200,response.statusCode());

        //verify content type
        Assertions.assertEquals("text/plain;charset=UTF-8",response.contentType());

        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        System.out.println("response.header(\"Content-Length\") = " + response.header("Content-Length"));
        System.out.println(response.headers().hasHeaderWithName("Date"));

        Assertions.assertEquals("17", response.header("Content-Length"));

        Assertions.assertEquals("Hello from Sparta", response.body().asString());



    }
}
