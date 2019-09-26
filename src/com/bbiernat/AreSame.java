package com.bbiernat;

import org.junit.Test;

import java.util.Arrays;
import static org.junit.Assert.assertEquals;


public class AreSame {
    public static boolean comp(int[] a, int[]b) {
        boolean output = true;

        // Edge cases
        if((a ==null) || (b == null)) return false;
        if(a.length != b.length) return false;

        // Apples for apples
        for(int i = 0; i<a.length; i++){
            a[i] = a[i] * a[i];
        }
        Arrays.sort(a); Arrays.sort(b);

        for(int j=0; j<a.length; j++) {
            if(a[j] != b[j]) return false;
        }

        return output;
    }


    @Test
    public void tests() {
        System.out.println("Starting tests....");
        int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = new int[]{121, 14641, 20736, 361, 25921, 361, 20736, 361};
        assertEquals("1",true, AreSame.comp(a, b));

        int[] c = new int[]{ 132, 14641, 20736, 361, 25921, 361, 20736, 361 };
        assertEquals("2", false, AreSame.comp(a, c));

        int[] d = new int[]{ 2, 2, 3};
        int[] e = new int[]{ 4, 4, 9};
        assertEquals("3", true, AreSame.comp(d, e));

        int[] f = new int[]{121, 144, 19, 161, 19, 144, 19, 11};
        int[] g = new int[]{121, 14641, 20736, 36100, 25921, 361, 20736, 361};
        assertEquals("4", false, AreSame.comp(f, g));

        int[] x = new int[0];
        int[] y = new int[0];
        assertEquals("5",false, AreSame.comp(a, x));

        assertEquals("6", true, AreSame.comp(x, y));

        int[] h = new int[]{ 4, 4 };
        int[] i = new int[]{ 1, 31};
        assertEquals("7", false, AreSame.comp(h, i));
        assertEquals("8", false, AreSame.comp(null, null));




        System.out.println("Joy! All tests pass!");
    }
}