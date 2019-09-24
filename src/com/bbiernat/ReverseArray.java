package com.bbiernat;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class ReverseArray {
    public static int[] reverse(long number) {
        int lengthOfNumber = String.valueOf(number).length();
        int[] output = new int[lengthOfNumber];

        for(int i=0; i<output.length; i++) {
            output[i] = (int)(number % 10);
            number = number / 10;
        }
        return output;
    }

    @Test
    public void tests() {
        assertArrayEquals(new int[] {1, 3, 2, 5, 3}, ReverseArray.reverse(35231));
        assertArrayEquals(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, ReverseArray.reverse(87654321));
    }
}