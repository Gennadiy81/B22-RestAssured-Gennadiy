package com.cybertek.day3;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SpartanTestWithPath {
    @BeforeAll
    public static void init() {
        baseURI = "http://100.26.214.41:8000";

    }

    @DisplayName("GET one spartan with Path Param")
    @Test()
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                                   .and().pathParam("id", 10)
                            .when().get("/api/spartans/{id}");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());

        System.out.println(response.path("id").toString());
        System.out.println(response.path("name").toString());
        System.out.println(response.path("gender").toString());
        System.out.println(response.path("phone").toString());

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);
    }
    @DisplayName("Get all spartan and navigate with Path")
    @Test()
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");


        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String name = response.path("name[0]");
        System.out.println("name = " + name);

        String lastFirstName = response.path("name[-1]");
        System.out.println("lastFirstName = " + lastFirstName);
        //save names inside the list of string
        List<String> names = response.path("name");
        System.out.println(names);
        //print each name one by one
        for (String n : names) {
            System.out.println(n);
        }
    }
}
