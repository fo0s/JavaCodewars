package com.bbiernat;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class XO {
    public static boolean getXO(String input) {
        int x = 0;
        int o = 0;
        String[] letters = input.split("");

        for (String letter : letters) {
            if (letter.toLowerCase().equals("x")) {
                x++;
            } else if (letter.toLowerCase().equals("o")) {
                o++;
            }
        }
        return x == o;
    }

//    Best Answer
//    public static boolean getOX(String input) {
//        int x=0; int o=0;
//
//        for(int i=0; i<input.length(); i++) {
//            if(input.toLowerCase().charAt(i) == 'o') o++;
//            if(input.toLowerCase().charAt(i) == 'x') x++;
//        }
//        return x == o;
//    }

    @Test
    public void testSomething1() {
        assertEquals(true, XO.getXO("xxxooo"));
    }

    @Test
    public void testSomething2() {
        assertEquals(true, XO.getXO("xxxXooOo"));
    }

    @Test
    public void testSomething3() {
        assertEquals(false, XO.getXO("xxx23424esdsfvxXXOOooo"));
    }

    @Test
    public void testSomething4() {
        assertEquals(false, XO.getXO("xXxxoewrcoOoo"));
    }

    @Test
    public void testSomething5() {
        assertEquals(false, XO.getXO("XxxxooO"));
    }

    @Test
    public void testSomething6() {
        assertEquals(true, XO.getXO("zssddd"));
    }

    @Test
    public void testSomething7() {
        assertEquals(false, XO.getXO("Xxxxertr34"));
    }

    @Test
    public void testSomething8() {
        assertEquals( false, XO.getXO( "8OXX"));
    }
}
