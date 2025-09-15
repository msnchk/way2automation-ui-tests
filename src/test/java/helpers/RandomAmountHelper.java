package helpers;

import java.math.BigDecimal;
import java.util.Random;

public class RandomAmountHelper {
    private static final Random random = new Random();

    public static BigDecimal generateRandomAmount(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Incorrect numbers for random generator");
        }
        int randomInt = random.nextInt(max - min + 1) + min;
        return BigDecimal.valueOf(randomInt);
    }
}
