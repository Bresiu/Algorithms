package List2.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MakeList {
    // make list of random values
    public static List<Integer> makePermutation(int size) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            Random random = new Random();
            list.add(random.nextInt(size));
        }
        return list;
    }

    public static List<Integer> makeEratosthenesSieve() {
        List<Integer> primes = new ArrayList<Integer>();
        int upperBound = 10000;

        int upperBoundSquareRoot = (int) Math.sqrt(upperBound);
        boolean[] isComposite = new boolean[upperBound + 1];
        for (int m = 2; m <= upperBoundSquareRoot; m++) {
            if (!isComposite[m]) {
                primes.add(m);
                for (int k = m * m; k <= upperBound; k += m)
                    isComposite[k] = true;
            }
        }
        for (int m = upperBoundSquareRoot; m <= upperBound; m++)
            if (!isComposite[m])
                primes.add(m);

        // 25 liczb pierwszych < 100
        primes = primes.subList(25, primes.size());
        return getTenPrimes(primes);
    }

    // Co dziesiata liczba pierwsza z przedzialu 100 - 10000
    private static List<Integer> getTenPrimes(List<Integer> primes) {
        List<Integer> tenPrimes = new ArrayList<Integer>();
        for (int i = 0; i < primes.size(); i += 10) {
            tenPrimes.add(primes.get(i));
        }
        return tenPrimes;
    }

}
