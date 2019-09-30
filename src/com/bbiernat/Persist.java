package com.bbiernat;

import static org.junit.Assert.*;
import org.junit.Test;

public class Persist {
    public static int persistence(long n) {
        int count = 0;
        int newNumber;

        while(true) {
            newNumber = 1;
            if (Long.toString(n).length() != 1) {
                while(n>0) {
                    newNumber *= n % 10;
                    n /= 10;
                }

                n = newNumber;
                count++;
            } else { break; }
        }
        return count;
    }


        @Test
    public void BasicTests() {
        System.out.println("****** Basic Tests ******");
        assertEquals(0, Persist.persistence(4));
        assertEquals(1, Persist.persistence(11));
        assertEquals(3, Persist.persistence(39));
        assertEquals(2, Persist.persistence(25));
        assertEquals(4, Persist.persistence(999));
    }
}


//    Write a function, persistence, that takes in a positive
//    parameter num and returns its multiplicative persistence,
//    which is the number of times you must multiply the digits in
//    num until you reach a single digit.
//
//        For example:
//
//        persistence(39) == 3 // because 3*9 = 27, 2*7 = 14, 1*4=4
//        // and 4 has only one digit
//
//        persistence(999) == 4 // because 9*9*9 = 729, 7*2*9 = 126,
//        // 1*2*6 = 12, and finally 1*2 = 2
//
//        persistence(4) == 0 // because 4 is already a one-digit number