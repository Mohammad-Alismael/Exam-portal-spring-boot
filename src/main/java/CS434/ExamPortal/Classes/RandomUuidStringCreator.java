package CS434.ExamPortal.Classes;

import java.security.SecureRandom;
import java.util.Random;

public class RandomUuidStringCreator {

    private static final int RANDOM_VERSION = 4;
    private static RandomUuidStringCreator singleton = null;

    private RandomUuidStringCreator(){

    }
    public static RandomUuidStringCreator getInstance() {

        if (singleton == null)
            singleton = new RandomUuidStringCreator();
        return singleton;
    }
    /**
     * Returns a random-based UUID as String.
     *
     * It uses a thread local {@link SecureRandom}.
     *
     * @return a random-based UUID string
     */
    public static String getRandomUuid() {
        Random SECURE_RANDOM = new SecureRandom();
        return getRandomUuid(SECURE_RANDOM);
    }

    /**
     * Returns a random-based UUID String.
     *
     * It uses any instance of {@link Random}.
     *
     * @return a random-based UUID string
     */
    private static String getRandomUuid(Random random) {

        long msb = 0;
        long lsb = 0;

        // (3) set all bit randomly
        if (random instanceof SecureRandom) {
            // Faster for instances of SecureRandom
            final byte[] bytes = new byte[16];
            random.nextBytes(bytes);
            msb = toNumber(bytes, 0, 8); // first 8 bytes for MSB
            lsb = toNumber(bytes, 8, 16); // last 8 bytes for LSB
        } else {
            msb = random.nextLong(); // first 8 bytes for MSB
            lsb = random.nextLong(); // last 8 bytes for LSB
        }

        // Apply version and variant bits (required for RFC-4122 compliance)
        msb = (msb & 0xffffffffffff0fffL) | (RANDOM_VERSION & 0x0f) << 12; // apply version bits
        lsb = (lsb & 0x3fffffffffffffffL) | 0x8000000000000000L; // apply variant bits

        // Convert MSB and LSB to hexadecimal
        String msbHex = zerofill(Long.toHexString(msb), 16);
        String lsbHex = zerofill(Long.toHexString(lsb), 16);

        // Return the UUID
        return msbHex + lsbHex;
    }

    private static long toNumber(final byte[] bytes, final int start, final int length) {
        long result = 0;
        for (int i = start; i < length; i++) {
            result = (result << 8) | (bytes[i] & 0xff);
        }
        return result;
    }

    private static String zerofill(String string, int length) {
        return new String(lpad(string.toCharArray(), length, '0'));
    }

    private static char[] lpad(char[] chars, int length, char fill) {

        int delta = 0;
        int limit = 0;

        if (length > chars.length) {
            delta = length - chars.length;
            limit = length;
        } else {
            delta = 0;
            limit = chars.length;
        }

        char[] output = new char[chars.length + delta];
        for (int i = 0; i < limit; i++) {
            if (i < delta) {
                output[i] = fill;
            } else {
                output[i] = chars[i - delta];
            }
        }
        return output;
    }
}
