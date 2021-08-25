package com.cybertek.utilities;

import com.cybertek.pojo.CreateSpartan;
import com.cybertek.pojo.Spartan;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Random;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public abstract class SpartanUtils extends SpartanTestBase{

    public static void createSpartan(){
        Faker faker = new Faker();

        String[] strings = {"Female", "Male"};
        Random random = new Random();
        String gender = strings[random.nextInt(strings.length)];

        CreateSpartan spartan = new CreateSpartan();

        spartan.setName(faker.name().firstName());
        spartan.setGender(gender);
        String fakerNumber = faker.phoneNumber().phoneNumber();
        String tempNum = "";
       for (int i = 0; i < fakerNumber.length(); i++) {
           if ( Character.isDigit(fakerNumber.charAt(i))){
               tempNum += fakerNumber.charAt(i)+"";
           }
       }
        long num = Long.parseLong(tempNum);
        spartan.setPhone(num);
        System.out.println("spartan = " + spartan);

        Response response = given().accept(ContentType.JSON).and() //what we are asking from api which is JSON response
                .contentType(ContentType.JSON) //what we are sending to api, which is JSON also
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        //verify status code
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedResponseMessage = "A Spartan is Born!";
        assertThat(response.path("success"),is(expectedResponseMessage));
        assertThat(response.path("data.name"),is(spartan.getName()));
        assertThat(response.path("data.gender"),is(spartan.getGender()));
        assertThat(response.path("data.phone"),is(spartan.getPhone()));

        response.prettyPrint();
    }
}
