package uk.co.payr.payrusersapi.user.util;

import java.security.SecureRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RandomUtils {

        private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        private static final SecureRandom RANDOM = new SecureRandom();

        public static String generateRandomAlphanumeric(int length) {
            return IntStream.range(0, length)
                    .map(i -> RANDOM.nextInt(ALPHANUMERIC.length()))
                    .mapToObj(ALPHANUMERIC::charAt)
                    .map(Object::toString)
                    .collect(Collectors.joining());
        }

}
