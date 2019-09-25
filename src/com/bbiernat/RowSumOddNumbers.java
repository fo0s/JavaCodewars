package com.bbiernat;

import static org.junit.Assert.*;
import org.junit.Test;

public class RowSumOddNumbers {
    public static int rowSumOddNumbers(int n) {
        return n * n * n;
    }


    @Test
    public void tests() {
        System.out.println("Start of test...");
        assertEquals("First row should return 1: 1",1, RowSumOddNumbers.rowSumOddNumbers(1));
        assertEquals("Second row should return 8: 3, 5",8, RowSumOddNumbers.rowSumOddNumbers(2));
        assertEquals("Third row should return 27: 7, 9, 11", 27, RowSumOddNumbers.rowSumOddNumbers(3));
        assertEquals(74088, RowSumOddNumbers.rowSumOddNumbers(42));
        System.out.println("All tests passed");
    }
}
