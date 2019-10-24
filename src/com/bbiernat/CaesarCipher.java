package com.bbiernat;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class CaesarCipher {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";

    public static List<String> movingShift(String s, int shift) {
        StringBuilder newString = new StringBuilder(s);
        List<String> encodedOutput = new ArrayList<>();
        char letter;
        int getIndex;

        // Encoder
        for (int i = 0; i < newString.length(); i++) {

            letter = newString.charAt(i);
            String isLowerOrUpper = Character.isUpperCase(letter) ? UPPER : LOWER;

            // Check if letter exists, otherwise -1
            getIndex = isLowerOrUpper.indexOf(letter);

            // If -1 add as is, otherwise encode
            if(getIndex != -1) {
                getIndex = (getIndex + shift) % 26;
                newString.setCharAt(i, isLowerOrUpper.charAt(getIndex));
            } else {
                newString.setCharAt(i, newString.charAt(i));
            }

            shift++;
        }

        // Splitter
        int stringLength = newString.length() / 4;

        // -- Check if error in length
        while (stringLength - 1 >= newString.length() - 4 * (stringLength - 1)) {
            stringLength--;
        }
        // -- Build string
        for (int i = 0; i < 4; i++) {
            encodedOutput.add(newString.substring(i * stringLength, i * stringLength + stringLength));
        }
        encodedOutput.add(newString.substring(4 * stringLength));

        return encodedOutput;
    }

    public static String demovingShift(List<String> s, int shift) {
        StringBuilder newString = new StringBuilder();

        for(String section : s) {
            newString.append(section);
        }

        // Decoder
        for (int i = 0; i < newString.length(); i++) {

            char letter = newString.charAt(i);
            String isLowerOrUpper = Character.isUpperCase(letter) ? UPPER : LOWER;

            // Check if letter exists, otherwise -1
            int getIndex = isLowerOrUpper.indexOf(letter);

            // If -1 add as is, otherwise decode
            if (getIndex != -1) {

                getIndex = getIndex - shift >= 0 ? getIndex - shift : getIndex - shift + 26;
                newString.setCharAt(i, isLowerOrUpper.charAt(getIndex));
            } else {
                newString.setCharAt(i, newString.charAt(i));
            }
            shift = ++ shift % 26;
        }

        return newString.toString();
    }

    @Test
    public void test1() {
        String u = "I should have known that you would have a perfect answer for me!!!";
        List<String> v = Arrays.asList("J vltasl rlhr ", "zdfog odxr ypw", " atasl rlhr p ", "gwkzzyq zntyhv", " lvz wp!!!");
        assertEquals(v, CaesarCipher.movingShift(u, 1));
        assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 1), 1));
    }

    @Test
    public void test2() {
        String u = " uoxIirmoveNreefckgieaoiEcooqo";
        List<String> v = Arrays.asList(" xscOp", "zvygqA", "ftuwud", "adaxmh", "Edqrut");
        assertEquals(v, CaesarCipher.movingShift(u, 2));
        assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 2), 2));
    }

    @Test
    public void test3() {
        String u = "uaoQop jx eh osr okaKv vzagzwpxagokBKriipmc U";
        List<String> v = Arrays.asList("wdsVuw sh", " qu dii h", "evGs uzbi", "caudhoxuM", "Wewxfdu O");
        assertEquals(v, CaesarCipher.movingShift(u, 2));
        assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 2), 2));
    }

    @Test
    public void test4() {
        String u = "kgpiqislyhvmffdzlyehjiIteAaaotcoapk bbMgaHlda";
        List<String> v = Arrays.asList("mjtnwpaui", "shztutqdr", "ycffGseBc", "dsyiviyu ", "noAvqYdwu");
        assertEquals(v, CaesarCipher.movingShift(u, 2));
        assertEquals(u, CaesarCipher.demovingShift(CaesarCipher.movingShift(u, 2), 2));
    }
}

//
//    The action of a Caesar cipher is to replace each plaintext letter
//    with a different one a fixed number of places up or down the alphabet.
//
//        This program performs a variation of the Caesar shift.
//        The shift increases by 1 for each character (on each iteration).
//
//        If the shift is initially 1, the first character of the
//        message to be encoded will be shifted by 1, the second
//        character will be shifted by 2, etc...
//        Coding: Parameters and return of function "movingShift"
//
//        param s: a string to be coded
//
//        param shift: an integer giving the initial shift
//
//        The function "movingShift" first codes the entire string
//        and then returns an array of strings containing the coded
//        string in 5 parts (five parts because, to avoid more risks,
//        the coded message will be given to five runners, one piece
//        for each runner).
//
//        If possible the message will be equally divided by message
//        length between the five runners. If this is not possible,
//        parts 1 to 5 will have subsequently non-increasing lengths,
//        such that parts 1 to 4 are at least as long as when evenly
//        divided, but at most 1 longer. If the last part is the empty
//        string this empty string must be shown in the resulting array.
//
//        For example, if the coded message has a length of 17 the five
//        parts will have lengths of 4, 4, 4, 4, 1. The parts 1, 2, 3, 4
//        are evenly split and the last part of length 1 is shorter. If
//        the length is 16 the parts will be of lengths 4, 4, 4, 4, 0.
//        Parts 1, 2, 3, 4 are evenly split and the fifth runner will
//        stay at home since his part is the empty string. If the length
//        is 11, equal parts would be of length 2.2, hence parts will be
//        of lengths 3, 3, 3, 2, 0.
//
//        You will also implement a "demovingShift" function with two
//        parameters
//        Decoding: parameters and return of function "demovingShift"
//
//        1) an array of strings: s (possibly resulting from "movingShift",
//        with 5 strings)
//
//        2) an int shift
//
//        "demovingShift" returns a string.
//        Example:
//
//        u = "I should have known that you would have a perfect answer " +
//        "for me!!!"
//
//        movingShift(u, 1) returns :
//
//        v = ["J vltasl rlhr ", "zdfog odxr ypw", " atasl rlhr p ",
//        "gwkzzyq zntyhv", " lvz wp!!!"]
//
//        (quotes added in order to see the strings and the spaces,
//        your program won't write these quotes, see Example Test Cases)
//
//        and demovingShift(v, 1) returns u.