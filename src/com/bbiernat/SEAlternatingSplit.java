package com.bbiernat;

import org.junit.Test;
import static org.junit.Assert.*;

public class SEAlternatingSplit {

    public static String encrypt(final String text, final int n) {

        // Sanity checks
        if(text == null || text.isEmpty() || n<1 ) return text;

        String output = text;
        String copyOfText;
        int roundCount = 0;

        do {
            copyOfText = output;
            output = "";

            // Odd number split
            for(int j=0; j<copyOfText.length(); j++) if((j%2) != 0) output += copyOfText.charAt(j);

            // Even number split
            for(int i=0; i<copyOfText.length(); i++) if((i%2) == 0) output += copyOfText.charAt(i);

            roundCount++;
        } while(roundCount < n);

        return output;
    }

    public static String decrypt(final String encryptedText, final int n) {
        // Sanity checks
        if(encryptedText == null || encryptedText.isEmpty() || n<1 ) return encryptedText;

        // Initiate viariables
        String tempText; String output = encryptedText; String evenText; String oddText;
        int roundCount = 0; int index;


        do {
            evenText = "";
            oddText = "";
            index = 0;

            tempText = output;
            output = "";

            // Get even split
            for (int i = 0; i < tempText.length() / 2; i++) oddText += tempText.charAt(i);

            // Get odd split
            for (int j = tempText.length() / 2; j < tempText.length(); j++)
                evenText += tempText.charAt(j);

            // Put them together
            do {
                if (index < evenText.length()) output += evenText.charAt(index);
                if (index < oddText.length()) output += oddText.charAt(index);
                index++;
            } while (output.length() < tempText.length());

            roundCount++;
        } while(roundCount < n);

        return output;
    }


    @Test
    public void testEncrypt() {
        // assertEquals("expected", "actual");
        assertEquals("test","This is a test!", SEAlternatingSplit.encrypt("This is a test!", 0));
        assertEquals("hsi  etTi sats!", SEAlternatingSplit.encrypt("This is a test!", 1));
        assertEquals("s eT ashi tist!", SEAlternatingSplit.encrypt("This is a test!", 2));
        assertEquals(" Tah itse sits!", SEAlternatingSplit.encrypt("This is a test!", 3));
        assertEquals("This is a test!", SEAlternatingSplit.encrypt("This is a test!", 4));
        assertEquals("This is a test!", SEAlternatingSplit.encrypt("This is a test!", -1));
        assertEquals("hskt svr neetn!Ti aai eyitrsig", SEAlternatingSplit.encrypt("This kata is very interesting!", 1));
    }

    @Test
    public void testDecrypt() {
        // assertEquals("expected", "actual");
        assertEquals("This is a test!", SEAlternatingSplit.decrypt("This is a test!", 0));
        assertEquals("This is a test!", SEAlternatingSplit.decrypt("hsi  etTi sats!", 1));
        assertEquals("This is a test!", SEAlternatingSplit.decrypt("s eT ashi tist!", 2));
        assertEquals("This is a test!", SEAlternatingSplit.decrypt(" Tah itse sits!", 3));
        assertEquals("This is a test!", SEAlternatingSplit.decrypt("This is a test!", 4));
        assertEquals("This is a test!", SEAlternatingSplit.decrypt("This is a test!", -1));
        assertEquals("This kata is very interesting!", SEAlternatingSplit.decrypt("hskt svr neetn!Ti aai eyitrsig", 1));
    }

    @Test
    public void testNullOrEmpty() {
        // assertEquals("expected", "actual");
        assertEquals("", SEAlternatingSplit.encrypt("", 0));
        assertEquals("", SEAlternatingSplit.decrypt("", 0));
        assertEquals(null, SEAlternatingSplit.encrypt(null, 0));
        assertEquals(null, SEAlternatingSplit.decrypt(null, 0));
    }
}

//
//    For building the encrypted string:
//        Take every 2nd char from the string, then the other chars,
//        that are not every 2nd char, and concat them as new String.
//        Do this n times!
//
//        Examples:
//
//        "This is a test!", 1 -> "hsi  etTi sats!"
//        "This is a test!", 2 -> "hsi  etTi sats!" -> "s eT ashi tist!"
//
//        Write two methods:
//
//        String encrypt(final String text, final int n)
//        String decrypt(final String encryptedText, final int n)
//
//        For both methods:
//        If the input-string is null or empty return exactly this value!
//        If n is <= 0 then return the input text.
//
//        This kata is part of the Simple Encryption Series:
//        Simple Encryption #1 - Alternating Split
//        Simple Encryption #2 - Index-Difference
//        Simple Encryption #3 - Turn The Bits Around
//        Simple Encryption #4 - Qwerty

