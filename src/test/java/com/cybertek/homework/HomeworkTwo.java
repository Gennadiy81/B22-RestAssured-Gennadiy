package com.cybertek.homework;



import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HomeworkTwo {
    /**
     * Given Accept application/json
     * And path zipcode is 22031
     * When I send a GET request to /us endpoint
     * Then status code must be 200
     * And content type must be application/json
     * And Server header is cloudflare
     * And Report-To header exists
     * And body should contains following information
     *     post code is 22031
     *     country  is United States
     *     country abbreviation is US
     *     place name is Fairfax
     *     state is Virginia
     *     latitude is 38.8604
     */
    @BeforeAll
    public static void init(){
       baseURI = "https://www.zippopotam.us/us";
    }

    @DisplayName("Get info from 22031")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                            .and().pathParam("zipcode", 22031)
                            .when().get("/{zipcode}");


        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals("cloudflare", response.header("Server"));
        assertFalse(response.header("Report-To").isEmpty());

        JsonPath jsonData = response.jsonPath();
        assertEquals(22031, jsonData.getLong("'post code'"));
        assertEquals("United States", jsonData.getString("country"));
        assertEquals("US", jsonData.getString("'country abbreviation'"));
        assertEquals("Fairfax", jsonData.getString("places[0].'place name'"));
        assertEquals("Virginia", jsonData.getString("places[0].state"));
        assertEquals(38.8604, jsonData.getDouble("places[0].latitude"));

    }

    @DisplayName("Get 404 for zipcode 50000")
    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 50000)
                .when().get("/{zipcode}");
        assertEquals(404, response.statusCode());
        assertEquals("application/json", response.contentType());
    }























}
