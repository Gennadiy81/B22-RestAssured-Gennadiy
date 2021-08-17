package com.cybertek.day2;


import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;



import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.baseURI;
public class SpartanNegativeGeTest {
    @BeforeAll
    public static void init(){
        baseURI = "http://100.26.214.41:8000";
    }
    @Test
    public  void test1() {

        Response response = given()
                                    .accept(ContentType.XML)
                            .when()
                                    .get("/api/spartans/10");;

        assertEquals(406, response.statusCode());
        assertEquals(response.contentType(), "application/xml;charset=UTF-8");


    }
}
