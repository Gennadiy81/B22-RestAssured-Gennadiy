package com.cybertek.utiliteis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {
    @BeforeAll
    public static void init() {
        baseURI = "http://100.26.214.41:8000";

        String dbUrl = "jdbc:oracle:thin:100.26.214.41:1521:xe";
        String dbUsername = "SP";
        String dbPassword = "SP";

        com.cybertek.utilities.DBUtils.createConnection(dbUrl, dbUsername, dbPassword);
    }

    @AfterAll
    public void teardown() {

        com.cybertek.utilities.DBUtils.destroy();
    }
}
