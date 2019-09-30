package com.bbiernat;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FindOutlier {
    static int find(int[] integers) {

        return returnNumber(evenOddMore(integers), integers);
    }

    private static int evenOddMore(int[] input) {
        int even    = 0;
        int odd     = 0;

        for(int number  : input) {
            if((number % 2) == 0) {
                even++;
            } else {
                odd++;
            }
        }

        return even > odd ? 1 : 0;
    }

    private static int returnNumber(int selection, int[] input) {
        int output = 0;
        switch (selection) {
            case 0:
                for(int even : input) {
                    if((even %2) == 0) {
                        output = even;
                        break;
                    }
                }
                break;

            case 1:
                for(int odd : input) {
                    if((odd %2) != 0) {
                        output = odd;
                        break;
                    }
                }
        }

        return output;
    }

    @Test
    public void testExample() {
        int[] exampleTest1 = {2,6,8,-10,3};
        int[] exampleTest2 = {206847684,1056521,7,17,1901,21104421,7,1,35521,1,7781};
        int[] exampleTest3 = {Integer.MAX_VALUE, 0, 1};
        assertEquals(3, FindOutlier.find(exampleTest1));
        assertEquals(206847684, FindOutlier.find(exampleTest2));
        assertEquals(0, FindOutlier.find(exampleTest3));
    }
}

//    You are given an array (which will have a length of at least 3,
//    but could be very large) containing integers. The array is either
//    entirely comprised of odd integers or entirely comprised of even
//    integers except for a single integer N. Write a method that takes
//    the array as an argument and returns this "outlier" N.

//        Examples
//
//        [2, 4, 0, 100, 4, 11, 2602, 36]
//        Should return: 11 (the only odd number)
//
//        [160, 3, 1719, 19, 11, 13, -21]
//        Should return: 160 (the only even number)
//


// Best answer:
//public class FindOutlier{
//    static int find(int[] integers) {
//        int even = 0;
//        int odd = 0;
//        int cycle = 0;
//
//        for(Integer value : integers) {
//            if(value % 2 == 0) {
//                cycle++;
//                even = value;
//            }else {
//                odd = value;
//            }
//        }
//        return (cycle > 1) ? odd : even;
//    }
//}