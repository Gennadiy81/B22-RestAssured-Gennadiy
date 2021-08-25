package com.cybertek.day7;


import com.cybertek.utilities.SpartanUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyTest extends SpartanUtils {

    @DisplayName("Create new Spartan")
    @Test
    public void test1(){
        SpartanUtils.createSpartan();
    }
}
