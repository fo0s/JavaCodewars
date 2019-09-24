package com.bbiernat;

import static org.junit.Assert.*;
import org.junit.Test;

// Mr. Scrooge has a sum of money 'P' that wants to invest,
// and he wants to know how many years 'Y' this sum has
// to be kept in the bank in order for this sum of money to amount to 'D'.

//The sum is kept for 'Y' years in the bank where interest 'I'
// is paid yearly, and the new sum is re-invested yearly
// after paying tax 'T'

// Note that the principal is not taxed but only the
// year's accrued interest
public class Money {

    public static int calculateYears(double principal, double interest,  double tax, double desired) {
        int years;
        double interestAmount;

        for(years=0; principal < desired; years++) {
            interestAmount = (principal * interest);
            principal = principal + (interestAmount - (interestAmount * tax));
        }

        return years;
    }

    @Test
    public void test() {
        System.out.println("Fixed Tests calculateYears");
        assertEquals(3, Money.calculateYears(1000,0.05,0.18,1100));
        assertEquals(14 , Money.calculateYears(1000,0.01625,0.18,1200));
        assertEquals(0, Money.calculateYears(1000,0.05,0.18,1000));
    }
}