package com.cybertek.day4;

import com.cybertek.utiliteis.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWithJsonPath extends HRTestBase {
    @DisplayName("Get request to Countries")
    @Test
    public void test1() {

        Response response = get("/countries");

        //get the second country name with JsonPath

        //to use jsonpath we assign response to JsonPath
        JsonPath jsonPath = response.jsonPath();

        String secondCountryName = jsonPath.getString("items[1].country_name");
        System.out.println("secondCountryName = " + secondCountryName);

        //get all country ids
        //items.country_id
        List<String> allCountryIds = jsonPath.getList("items.country_id");
        System.out.println(allCountryIds);
        List<String> employees = jsonPath.getList("employees.findAll{it.salary>1000");
    }

    @DisplayName("GET requesto /employees with query param")
    @Test
    public void test2() {
        //we added limit query param to get 107 employees
        Response response = given().queryParam("limit", 107)
                .when().get("/employees");

        JsonPath jsonPath = response.jsonPath();

        //get me all email of employees who is working as IT_PROG
        List<String> employeeITProgs = jsonPath.getList("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(employeeITProgs);

        //get me first name of employees who is making more than 10000
        List<String> employees = jsonPath.getList("items.findAll{it.salary>10000}.first_name");
        System.out.println(employees);

    }
}
