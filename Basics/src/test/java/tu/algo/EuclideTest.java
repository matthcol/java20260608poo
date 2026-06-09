package tu.algo;

import algo.Euclide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class EuclideTest {

    @Test
    void testGcd_whenPrimes() {
        // given
        int a = 7;
        int b = 13;
        // when
        int g = Euclide.gcd(a, b);
        // then
        assertEquals(1, g);
    }

    @Test
    void testGcd_whenBothOnes() {
        // given
        int a = 1;
        int b = 1;
        // when
        int g = Euclide.gcd(a, b);
        // then
        assertEquals(1, g);
    }

    @ParameterizedTest(name = "when {0}")
    @CsvSource({
            "first is 1, 1 ,5, 1",
            "second is 1, 5, 1, 1",
            "both 1, 1, 1, 1",
            "primes, 7, 13, 1",
            "gcd > 1 and first greater, 21, 15, 3",
            "gcd > 1 and second greater, 15, 21, 3",
            // TODO: 2 values of Fibonacci series => iterate a lot
    })
    void testGcd_ok(String label, int a, int b, int expected_gcd){
        // when
        int actual_gcd = Euclide.gcd(a, b);
        // then
        assertEquals(expected_gcd, actual_gcd);
    }

    @ParameterizedTest(name="when {0}")
    @CsvSource({
            "first zero, 0, 5",
            "second zero, 5, 0",
            "both zero, 0, 0",
            "first negative, -5, 7",
            "second negative, 7, -5",
            "both negatives, -5, -7"
    })
    void testGcd_ko(String label, int a, int b){
        // when + then
        assertTimeoutPreemptively( // other thread
                Duration.ofSeconds(1),
                () -> assertThrows(
                    IllegalArgumentException.class,
                    () -> Euclide.gcd(a, b)
                )
        );
    }



















}