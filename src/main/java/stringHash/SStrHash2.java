package stringHash;

/**
 * Purpose: Calculate the SStrHash2 equivalent of a plaintext String
 */
public class SStrHash2 {

    private static final long[] p = {0, 8, 16, 24, 0, 8, 16, 24, 8, 16, 24};
    private static final long[] r = {-13, 8, -13, -12, 16, -5, -3, 10, -15};
    private static final long finalCalcPart = (1L << 32L);

    /**
     * Converts a signed int (represented as long)
     * into an unsigned int (represented as long)
     *
     * This is necessary because this algorithm uses
     * numeric overflow as a feature.
     *
     * @param input signed int (long)
     * @return  uint32 representation
     */
    private static long intToUnsignedInt(long input) {
        return input & 0b011111111111111111111111111111111L;
    }

    /**
     * Bit manipulation calculates the resulting SStrHash from a
     *
     * @param a The hash a value
     * @return  The resulting hash
     */
    private static long calculateFinal(long a) {
        return a - (finalCalcPart * (a >> 31L));
    }

    /**
     * Gets 4 bytes as a little endian unsigned int (represented as long)
     *
     * @param charIndexes   Bytes to extract from
     * @param offset    Offset to start at
     * @return  Unsigned int of 4 bytes
     */
    private static long extractUnsignedInt(byte[] charIndexes, int index, int offset) {
        byte[] extracted = new byte[4];
        extracted[0] = charIndexes[index+offset];
        extracted[1] = charIndexes[index+offset+1];
        extracted[2] = charIndexes[index+offset+2];
        extracted[3] = charIndexes[index+offset+3];
        return java.nio.ByteBuffer.wrap(extracted).order(java.nio.ByteOrder.LITTLE_ENDIAN).getInt();
    }

    /**
     * Gets a byte array of uppercase letters and slash replaced with backslash
     *
     * @param plainText Text string
     * @param len   Length of string
     * @return  Usable byte array for hashing
     */
    private static byte[] prepareByteArrayFromPlaintext(String plainText, int len) {
        byte[] charIndexes = new byte[len];
        for(int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if(97 <= c && c <= 122) {
                charIndexes[i] = (byte)(c - 32);
            } else if(c == 47) {
                charIndexes[i] = (byte)92;
            } else {
                charIndexes[i] = (byte)c;
            }
        }
        return charIndexes;
    }

    /**
     * Converts the given String into a Hash.
     *
     * @return  Hash of Plaintext String
     */
    public static String hash(String plainText) {
        int len = plainText.length();
        byte[] charIndexes = prepareByteArrayFromPlaintext(plainText, len);

        int index = 0;
        long a = 0;
        long b = 0x9e3779b9L;
        long c = 0x9e3779b9L;
        long tempA;
        long tempB;
        long tempC;

        while(len >= 12) {
            long eightTo12Byte = extractUnsignedInt(charIndexes, index, 8);
            long fourToEightByte = extractUnsignedInt(charIndexes, index, 4);
            long zeroToFourByte = extractUnsignedInt(charIndexes, index, 0);

            a += eightTo12Byte;
            b += fourToEightByte;
            c += zeroToFourByte;

            for(int j = 0; j < r.length; j++) {
                long i = r[j];
                tempA = a;
                tempB = b;
                tempC = c;
                c = intToUnsignedInt(tempB);
                b = tempA;
                if(i > 0) {
                    a = (tempC - tempB - tempA) ^ (tempA << i);
                } else {
                    a = (tempC - tempB - tempA) ^ (intToUnsignedInt(tempA) >> -i);
                }
            }
            index += 12;
            len -= 12;
        }

        long[] d = {c, b, a + len+index /* original length of string */};
        for(int j = 0; j < charIndexes.length - index; j++) {
            d[(j/4)] += charIndexes[index+j] << p[j];
        }
        c = d[0];
        b = d[1];
        a = d[2];
        for(int j = 0; j < r.length; j++) {
            long i = r[j];
            tempA = a;
            tempB = b;
            tempC = c;
            c = intToUnsignedInt(tempB);
            b = tempA;
            if(i > 0) {
                a = (tempC - tempB - tempA) ^ (tempA << i);
            } else {
                a = (tempC - tempB - tempA) ^ (intToUnsignedInt(tempA) >> -i);
            }
        }
        return "" + calculateFinal(intToUnsignedInt(a));
    }
}
