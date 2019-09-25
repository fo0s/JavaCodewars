package com.bbiernat;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KeepHydrated {
    public static int Liters(double time) {
        return (int)(time * 0.5);
    }

    @Test
    public void Tests() {
        System.out.println("Start of tests.....");
        assertEquals(1, KeepHydrated.Liters(2));
        assertEquals(0, KeepHydrated.Liters(0.97));
        assertEquals(7, KeepHydrated.Liters(14.64));
        assertEquals(800, KeepHydrated.Liters(1600.20));
        assertEquals(40, KeepHydrated.Liters(80));
        assertEquals(46, KeepHydrated.Liters(93.1918803246812));
        System.out.println("All passed");
    }
}