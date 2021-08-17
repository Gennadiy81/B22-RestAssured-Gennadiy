package com.cybertek.day2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests {

    @BeforeAll
    public static void init(){
        baseURI = "http://100.26.214.41:1000/ords/hr";
    }
   @DisplayName("Get request to /regions")
    @Test
   public void test1(){
       Response response = get("/regions");
       System.out.println(response.statusCode());


   }

   @DisplayName("Get request to /regions")
    @Test
    public void test2(){
        Response response = get( "/regions/2");

       assertEquals(200, response.statusCode());
       assertEquals( "application/json",response.contentType());

       response.prettyPrint();
       assertEquals(response.body().asString().contains("Americas"), true);


   }


}
