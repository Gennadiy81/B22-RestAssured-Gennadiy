package com.cybertek.day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSApiTestWithPath {
    @BeforeAll
    public static void init() {
        baseURI = "http://100.26.214.41:1000/ords/hr";
    }

    @DisplayName("GET request to countries")
    @Test()
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":2}")
                .when()
                .get("/countries");

        assertEquals(200, response.statusCode());

        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //print first CountryId
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountryName = response.path("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        String thirdHref = response.path("items[2].links[0].href");
        System.out.println("thirdHref = " + thirdHref);

        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all regions ids are equal to 2
        List<Integer> allRegionsIDs = response.path("items.region_id");

        for (Integer regionsID : allRegionsIDs) {
            System.out.println("regionsID = " + regionsID);
            assertEquals(2,regionsID);
        }


    }

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2(){
        Response response= given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        //make sure we have only IT_PROG as a job_id
        List<String> allJobIDs = response.path("items.job_id");

        for (String jobID : allJobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG",jobID);
        }
    }
}
