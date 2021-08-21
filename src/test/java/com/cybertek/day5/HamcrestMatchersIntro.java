package com.cybertek.day5;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class HamcrestMatchersIntro {
    @Test
    public void simpleTest1(){

        assertThat(5+5, is(10));
        assertThat(5+5, equalTo(10));
        assertThat(5+5, is(equalTo(10)));

        assertThat(5+5, is(greaterThan(9)));

    }

    @DisplayName("Assetion with String")
    @Test
    public void stringHamcrest(){

        String text = "B22 is learning Hamcrest";

        assertThat(text, is("B22 is learning Hamcrest"));
        assertThat(text, equalTo("B22 is learning Hamcrest"));
        assertThat(text, is(equalTo("B22 is learning Hamcrest")));

        assertThat(text, startsWith("B22"));

        assertThat(text,startsWithIgnoringCase("b22"));
        assertThat(text, endsWith("rest"));







    }
}
