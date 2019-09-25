package com.bbiernat;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BasicOperations {
    public static Integer basicMath(String op, int v1, int v2) {
        int output = 0;
        switch (op) {
            case "+":
                output = v1 + v2;
                break;
            case "-":
                output = v1 - v2;
                break;
            case "*":
                output = v1 * v2;
                break;
            case "/":
                output = v1 / v2;
        }
        return output;
    }


        @Test
        public void testBasics()
        {
            System.out.println("Basic Tests");
            assertThat(BasicOperations.basicMath("+", 4, 7), is(11));
            assertThat(BasicOperations.basicMath("-", 15, 18), is(-3));
            assertThat(BasicOperations.basicMath("*", 5, 5), is(25));
            assertThat(BasicOperations.basicMath("/", 49, 7), is(7));
        }
    }
