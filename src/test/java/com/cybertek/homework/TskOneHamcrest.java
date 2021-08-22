package com.cybertek.homework;

import com.cybertek.utilities.HRTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TskOneHamcrest extends HRTestBase {
    /**
     *  Given accept type is Json
     * - Path param value- US
     * - When users sends request to /countries
     * - Then status code is 200
     * - And Content - Type is Json
     * - And country_id is US
     * - And Country_name is United States of America
     * - And Region_id is
     */
    @DisplayName("Get info from US")
    @Test
    public void test1(){
                        given()
                                .accept(ContentType.JSON)
                                .and().pathParam("country_id", "US")
                                .when()
                                .get("/countries/{country_id}")
                                .then()
                                .statusCode(200)
                                .and()
                                .contentType("application/json")
                                .and()
                                .body("country_id", is("US"))
                                .body("country_name", is("United States of America"))
                                .body("region_id", is(2));
    }

    /**
     * - Given accept type is Json
     * - Query param value - q={"department_id":80}
     * - When users sends request to /employees
     * - Then status code is 200
     * - And Content - Type is Json
     * - And all job_ids start with 'SA'
     * - And all department_ids are 80
     * - Count is 25
     */


}
