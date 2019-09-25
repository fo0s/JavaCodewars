package com.bbiernat;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FindOdd {

    public static int findIt(int[] a) {
        int count, output = 0;

        for (int number : a) {
            count = 0;

            for(int i=0; i<a.length; i++) {
                if(a[i] == number) count++;
            }
            if((count % 2) != 0) output = number;
        }
        return output;
    }


    @Test
    public void findTest() {
        System.out.println("Start of tests...");
        assertEquals(5, FindOdd.findIt(new int[]{20, 1, -1, 2, -2, 3, 3, 5, 5, 1, 2, 4, 20, 4, -1, -2, 5}));
        assertEquals(-1, FindOdd.findIt(new int[]{1, 1, 2, -2, 5, 2, 4, 4, -1, -2, 5}));
        assertEquals(5, FindOdd.findIt(new int[]{20, 1, 1, 2, 2, 3, 3, 5, 5, 4, 20, 4, 5}));
        assertEquals(10, FindOdd.findIt(new int[]{10}));
        assertEquals(10, FindOdd.findIt(new int[]{1, 1, 1, 1, 1, 1, 10, 1, 1, 1, 1}));
        assertEquals(1, FindOdd.findIt(new int[]{5, 4, 3, 2, 1, 5, 4, 3, 2, 10, 10}));
        System.out.println("Joy! All passed!");
    }
}