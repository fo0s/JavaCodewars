package com.bbiernat;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;

public class RemovedNumbers {
    public static List<long[]> removNb(long n) {
        // Get total sum
        long sum = (n * n + n) / 2;

        List<long[]> list = new LinkedList<>();
        long halfN = n / 2;
        long sqrtN = (long) Math.sqrt(n);

        for (long i = halfN; i <= n - sqrtN; i++) {
            long sumMinusI = (sum - i);
            if ((sumMinusI % (i + 1)) == 0) {
                double j = (sumMinusI / (i + 1));
                list.add(new long[]{i, (long) j});
            }
        }
        return list;
    }

    @Test
    public void test12() {
        List<long[]> res = new ArrayList<long[]>();
        res.add(new long[] {15, 21});
        res.add(new long[] {21, 15});
        List<long[]> a = RemovedNumbers.removNb(26);
        assertArrayEquals(res.get(0), a.get(0));
        assertArrayEquals(res.get(1), a.get(1));
    }
    @Test
    public void test14() {
        List<long[]> res = new ArrayList<long[]>();
        List<long[]> a = RemovedNumbers.removNb(100);
        assertEquals(res.size(), a.size());
    }

    @Test
    public void test1() {
        List<long[]> res = new ArrayList<long[]>();
        List<long[]> a = RemovedNumbers.removNb(249);
        res.add(new long[] {157, 196});
        res.add(new long[] {196, 157});

        assertArrayEquals(res.get(0), a.get(0));
    }

    @Test
    public void test2() {
        List<long[]> res = new ArrayList<long[]>();
        List<long[]> a = RemovedNumbers.removNb(101);
        res.add(new long[] {55, 91});
        res.add(new long[] {91, 55});

        assertArrayEquals(res.get(0), a.get(0));
    }

    @Test
    public void test3() {
        List<long[]> res = new ArrayList<long[]>();
        List<long[]> a = RemovedNumbers.removNb(1006);
        res.add(new long[] {546, 925});
        res.add(new long[] {925, 546});

        assertArrayEquals(res.get(0), a.get(0));
    }

    @Test
    public void test4() {
        List<long[]> res = new ArrayList<long[]>();
        List<long[]> a = RemovedNumbers.removNb(1000008);
        res.add(new long[] {677076, 738480});
        res.add(new long[] {738480, 677076});

        assertArrayEquals(res.get(0), a.get(0));
    }
}
//    A friend of mine takes a sequence of numbers from 1 to n (where n > 0).
//        Within that sequence, he chooses two numbers, a and b.
//        He says that the product of a and b should be equal to the sum of all numbers in the
//        sequence, excluding a and b.
//        Given a number n, could you tell me the numbers he excluded from the sequence?
//
//        The function takes the parameter: n (n is always strictly greater than 0) and returns
//        an array or a string (depending on the language) of the form:
//
//        [(a, b), ...] or [[a, b], ...] or {{a, b}, ...} or or [{a, b}, ...]
//
//        with all (a, b) which are the possible removed numbers in the sequence 1 to n.
//
//        [(a, b), ...] or [[a, b], ...] or {{a, b}, ...} or ...will be sorted in increasing order of the "a".
//
//        It happens that there are several possible (a, b). The function returns an empty array
//        (or an empty string) if no possible numbers are found which will prove that my friend has
//        not told the truth! (Go: in this case return nil).
//
//        (See examples of returns for each language in "RUN SAMPLE TESTS")
//        Examples:
//
//        removNb(26) should return [(15, 21), (21, 15)]
//
//        or
//
//        removNb(26) should return { {15, 21}, {21, 15} }
//
//        or
//
//        removeNb(26) should return [[15, 21], [21, 15]]
//
//        or
//
//        removNb(26) should return [ {15, 21}, {21, 15} ]
//
//        or
//
//        removNb(26) should return "15 21, 21 15"
//
//        or
//
//        in C:
//        removNb(26) should return  **an array of pairs {{15, 21}{21, 15}}**
//        tested by way of strings.