package com.bbiernat;

import org.junit.Test;
import static org.junit.Assert.*;


public class Square {
    public static boolean isSquare(int number) {
        boolean output = false;
        if(number>=0) {
            for(int i=0; i<(number/2)+1;i++) {
                if(i*i == number) {
                    output = true;
                    break;
                }
            }
        }
        return output;
    }

//    Best Answer:
//    import static java.lang.Math.*;
//    public class Square {
//        public static boolean isSquare(int n) {
//            return Math.sqrt(n) % 1 == 0;
//        }
//    }


    @Test
    public void shouldWorkForSomeExamples() throws Exception {
        assertEquals("negative numbers aren't square numbers", false, Square.isSquare(-1));
        assertEquals("0 is a square number (0 * 0)",    true,   Square.isSquare(0));
        assertEquals("3 isn't a square number", false,  Square.isSquare(3));
        assertEquals("4 is a square number (2 * 2)",    true,   Square.isSquare(4));
        assertEquals("25 is a square number (5 * 5)",   true,   Square.isSquare(25));
        assertEquals("26 isn't a square number",false,  Square.isSquare(26));
    }
}
