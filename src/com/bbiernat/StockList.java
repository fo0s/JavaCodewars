package com.bbiernat;

import static org.junit.Assert.*;
import org.junit.Test;

public class StockList {
    public static String stockSummary(String[] lstOfArt, String[] lstOf1stLetter) {
        if(lstOfArt.length == 0 || lstOf1stLetter.length == 0) return "";

        int tempTotal;
        for( int i=0;i<lstOf1stLetter.length; i++) {
            tempTotal = 0;
            for (String book : lstOfArt) {
                // Get first letter of string and compare
                if(book.substring(0, 1).equals(lstOf1stLetter[i])) {
                    // Extract book numbers using regex and add to total
                    tempTotal += Integer.parseInt(book.replaceAll("[^0-9]", ""));
                }
            }
            lstOf1stLetter[i] = "(" + lstOf1stLetter[i] + " : " + tempTotal + ")";
        }
        return String.join(" - ", lstOf1stLetter);
    }

    @Test
    public void test1() {
        String art[] = new String[]{"ABAR 200", "CDXE 500", "BKWR 250", "BTSQ 890", "DRTY 600"};
        String cd[] = new String[] {"A", "B"};
        assertEquals("(A : 200) - (B : 1140)",
                StockList.stockSummary(art, cd));
    }
}
