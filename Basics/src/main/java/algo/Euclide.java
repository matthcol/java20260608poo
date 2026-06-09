package algo;

public class Euclide {

    /**
     * Compute greater common divider of 2 positive integers
     * @param a first integer
     * @param b second integer
     * @return greater common divider
     * @throws IllegalArgumentException if a or b is negative or zero
     */
    public static int gcd(int a, int b) {
        if ((a <= 0) || (b <= 0)) throw new IllegalArgumentException("a and b must be strictly positive");
        while (a != b) {
            if (a > b) {
                a -= b;
            } else {
                b -= a;
            }
        }
        return a;
    }
}
