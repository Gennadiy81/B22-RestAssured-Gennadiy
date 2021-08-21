package com.cybertek.homework;


import com.cybertek.utiliteis.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HomeWorkOne extends HRTestBase {

    /**
     * Q1:
     * - Given accept type is Json
     * - Path param value- US
     * - When users sends request to /countries
     * - Then status code is 200
     * - And Content - Type is Json
     * - And country_id is US
     * - And Country_name is United States of America
     * - And Region_id is
     */

    @DisplayName("Get info of US")
    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("country_id", "US")
                .when().get("/countries/{country_id}");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertEquals("US", response.path("country_id").toString());
        assertEquals("United States of America", response.path("country_name"));
        int region_id = response.path("region_id");
        assertEquals(2, region_id);

    }

    /**
     * Q2:
     * - Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users sends request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25
     */
    @DisplayName("Get info from department 80")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"department_id\":80}")
                .when().get("/employees");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("SA"));

        String job_id = response.path("items[0].job_id");
        assertTrue(job_id.contains("SA"));
        int department_id = response.path("items[0].department_id");
        assertEquals(80, department_id);
        List<String> count = response.path("items");
        assertEquals(25, count.size());

    }
    /**
     * Q3:
     * - Given accept type is Json
     * -Query param value q= region_id 3
     * - When users sends request to /countries
     * - Then status code is 200
     * - And all regions_id is 3
     * - And count is 6
     * - And hasMore is false
     * - And Country_name are;
     * Australia,China,India,Japan,Malaysia,Singapore
     */

    @DisplayName("Get country info from region 3")
    @Test
    public void test3(){
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":3}")
                .when().get("/countries");

        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        List<String> count = response.path("items");
        assertEquals(6, count.size());
//        boolean hasMore = response.path("hasMore");
        assertEquals(false, response.path("hasMore"));
        List<String> actualCountries = Arrays.asList("Australia", "China", "India", "Japan", "Malaysia", "Singapore");
        List<String> expectedCountries = response.path("items.country_name");
        assertTrue(expectedCountries.stream().allMatch(t -> actualCountries.
                stream().allMatch(expectedCountries::contains)));




    }

}























