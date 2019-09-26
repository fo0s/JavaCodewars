package com.bbiernat;

import static org.junit.Assert.*;
import org.junit.Test;

public class TriangleTester {
    public static boolean isTriangle(int a, int b, int c){
        if(a<= 0|| b<=0 || c<=0) return false;

        return (((a+b)>c) && ((b+c)>a)) && ((c+a)>b);
    }

    @Test
    public void publicTests() {
        assertEquals(true, TriangleTester.isTriangle(1,2,2));
        assertEquals(false, TriangleTester.isTriangle(7,2,2));
        System.out.println("All tests pass!");
    }
}
