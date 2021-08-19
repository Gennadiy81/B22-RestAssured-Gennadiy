package com.cybertek.utiliteis;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class HRTestBase {
    @BeforeAll
    public static void init() {
        baseURI = "http://100.26.214.41:1000/ords/hr";
    }

}
