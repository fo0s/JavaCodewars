package com.bbiernat;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GiveMeADiamond {
    public static String print(int n) {

        // Edge cases
        if( n<0 || (n%2) == 0) return null;

        // Initialize variables
        String[] diamond = new String[n];
        String diamondSize;
        int count = 0;

        while(true) {

            if(n<0) break;
            diamondSize = "";
            diamondSize += " ".repeat(count);
            diamondSize += "*".repeat(n);
            diamondSize += "\n";

            if(count > 0 )  {
                diamond[(diamond.length/2) + count] = diamondSize;
                diamond[(diamond.length/2) - count] = diamondSize;

            } else {
                diamond[diamond.length/2] = diamondSize;
            }

            n = n-2;
            count++;
        }

        return String.join("", diamond);
    }

    @Test
    public void testDiamond1() {
        StringBuffer expected = new StringBuffer();
        expected.append("*\n");
        assertEquals(expected.toString(), GiveMeADiamond.print(1));
    }


    @Test
    public void testDiamond3() {
        StringBuffer expected = new StringBuffer();
        expected.append(" *\n");
        expected.append("***\n");
        expected.append(" *\n");

        assertEquals(expected.toString(), GiveMeADiamond.print(3));
    }

    @Test
    public void testDiamond5() {
        StringBuffer expected = new StringBuffer();
        expected.append("  *\n");
        expected.append(" ***\n");
        expected.append("*****\n");
        expected.append(" ***\n");
        expected.append("  *\n");

        assertEquals(expected.toString(), GiveMeADiamond.print(5));
    }

    @Test
    public void testDiamond7() {
        StringBuffer expected = new StringBuffer();
        expected.append("   *\n");
        expected.append("  ***\n");
        expected.append(" *****\n");
        expected.append("*******\n");
        expected.append(" *****\n");
        expected.append("  ***\n");
        expected.append("   *\n");
    }


    @Test
    public void testDiamond0() {
        assertEquals(null, GiveMeADiamond.print(0));
    }

    @Test
    public void testDiamondMinus2() {
        assertEquals(null, GiveMeADiamond.print(-2));
    }

    @Test
    public void testDiamond2() {
        assertEquals(null, GiveMeADiamond.print(2));
    }
}
