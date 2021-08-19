package com.cybertek.utiliteis;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {
    @BeforeAll
    public static void init() {
        baseURI = "http://100.26.214.41:8000";
    }
}
