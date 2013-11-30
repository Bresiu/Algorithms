package List2.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bresiu on 29.11.13.
 */

public class Sieve {


    public static void main(String[] args) {
        List<Integer> primes = runEratosthenesSieve(10000);
        System.out.println(primes);
    }


    public static List<Integer> runEratosthenesSieve(int upperBound) {
        List<Integer> primes = new ArrayList<Integer>();
        int upperBoundSquareRoot = (int) Math.sqrt(upperBound);
        boolean[] isComposite = new boolean[upperBound + 1];
        for (int m = 2; m <= upperBoundSquareRoot; m++) {
            if (!isComposite[m] && m > 100) {
                primes.add(m);
                for (int k = m * m; k <= upperBound; k += m)
                    isComposite[k] = true;
            }
        }
        for (int m = upperBoundSquareRoot; m <= upperBound; m++)
            if (!isComposite[m] && m > 100)
                primes.add(m);
        return primes;
    }
}

