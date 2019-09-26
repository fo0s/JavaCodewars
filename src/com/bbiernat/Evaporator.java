package com.bbiernat;

import static org.junit.Assert.*;
import org.junit.*;

public class Evaporator {
    public static int evaporator(double content, double evap_per_day, double threshold) {
           int days=0;
           double originalThreshHold = content*(threshold/100);
            while(true) {
                if(content < originalThreshHold) break;

                content = content - (content*(evap_per_day/100));
                days++;
            }
        return days;
    }

        @Test
        public void testEvaporatorOne() {
            assertEquals("1",22 , Evaporator.evaporator(10, 10, 10));
            assertEquals("2",299 , Evaporator.evaporator(100.0, 1.0, 5));
        }
    }

//            EvaporatorTest
//            testEvaporatorEight
//            Log
//            |1| 100.0 |2| 1.0 |3| 5.0
//
//            expected:<299> but was:<1>
//        Stack Trace
//        Completed in 40ms
//        testEvaporatorSeven
//        Log
//        |1| 10.0 |2| 1.0 |3| 1.0
//
//        expected:<459> but was:<1>
//        Stack Trace
//
//        testEvaporatorThree
//        Log
//        |1| 100.0 |2| 5.0 |3| 5.0
//
//        expected:<59> but was:<1>
//        Stack Trace
//
//        testEvaporatorOne
//        Log
//        |1| 10.0 |2| 10.0 |3| 10.0
//
//        expected:<22> but was:<1>
//        Stack Trace
//
//        testEvaporatorSix
//        Log
//        |1| 100.0 |2| 1.0 |3| 1.0
//
//        expected:<459> but was:<1>
//        Stack Trace
//        Completed in 1ms
//        testEvaporatorTwo
//        Log
//        |1| 10.0 |2| 10.0 |3| 5.0
//
//        expected:<29> but was:<1>
//        Stack Trace
//
//        testEvaporatorFive
//        Log
//        |1| 47.5 |2| 8.0 |3| 8.0
//
//        expected:<31> but was:<1>
//        Stack Trace
//
//        testEvaporatorFour
//        Log
//        |1| 50.0 |2| 12.0 |3| 1.0


