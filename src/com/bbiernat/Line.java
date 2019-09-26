package com.bbiernat;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class Line {
        public static String Tickets(int[] peopleInLine) {
           String[] options = {"YES", "NO" };

           // Change amount: 25, 50
           int[] changeTotal = new int[2];

            for( int sale : peopleInLine) {
                switch (sale) {
                    case 25:
                        changeTotal[0]++;
                        break;

                    case 50:
                        // Check if there is any change
                        if(changeTotal[0] < 1) {return options[1];}
                        // Otherwise remove 25 and add 50 to kity
                        changeTotal[1]++; changeTotal[0]--;
                        break;

                    case 100:
                        // Check if there is enough for 75
                        if(changeTotal[1] < 1 && changeTotal[0] < 1) return options[1];
                        if(changeTotal[1] == 0 && changeTotal[0] < 3) return options[1];
                        if(changeTotal[0] == 0) return options[1];

                        if(changeTotal[1] > 0) {
                            changeTotal[1]--; changeTotal[0]--;
                        } else {
                            changeTotal[0] = changeTotal[0] - 3;
                        }
                }
            }
            return options[0];
        }

        @Test
        public void tests() {
//            assertEquals("1", "YES", Line.Tickets(new int[]{25, 25, 50}));
//            assertEquals("2", "NO", Line.Tickets(new int[]{50}));
//            assertEquals("3", "NO", Line.Tickets(new int[]{25, 100}));
//            assertEquals("4", "NO", Line.Tickets(new int[]{25, 50, 50}));
//            assertEquals("5", "NO", Line.Tickets(new int[]{50, 100}));
//            assertEquals("6", "YES", Line.Tickets(new int[]{25, 25, 25, 100}));
//            assertEquals("7", "YES", Line.Tickets(new int[]{25, 25, 50, 100}));
            assertEquals("8", "NO", Line.Tickets(new int[] {25, 25, 50, 50, 100}));

            System.out.println("All tests passed!");
        }
}

//        The new "Avengers" movie has just been released! There are a lot of
//        people at the cinema box office standing in a huge line. Each of
//        them has a single 100, 50 or 25 dollar bill. An "Avengers" ticket
//        costs 25 dollars.

