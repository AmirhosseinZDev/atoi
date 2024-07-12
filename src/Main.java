public class Main {
    public static void main(String[] args) {
        System.out.println(generateAtoiInteger("  00000000000123456780"));
    }

    public static int generateAtoiInteger(String s) {
        String whiteSpaceLessString = s.trim();
        if (whiteSpaceLessString.isEmpty()) {
            return 0;
        }
        boolean hasSignedChar = hasSignedChar(whiteSpaceLessString);
        boolean negativeSign = hasSignedChar && isNegativeSign(whiteSpaceLessString);
        String extractedDigits = extractDigits(whiteSpaceLessString, hasSignedChar);
        String atoiStr = removeUselessZeros(extractedDigits);
        return extractAtoiInteger(atoiStr, negativeSign);
    }

    private static boolean hasSignedChar(String s) {
        char c = s.charAt(0);
        return c == '-' || c == '+';
    }

    private static boolean isNegativeSign(String s) {
        return s.charAt(0) == '-';
    }

    private static String extractDigits(String s, boolean hasSignedChar) {
        StringBuilder atoiNum = new StringBuilder();
        for (int i = hasSignedChar ? 1 : 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                atoiNum.append(s.charAt(i));
            } else {
                break;
            }
        }
        return atoiNum.toString();
    }

    private static String removeUselessZeros(String s) {
        boolean isFindFirstNonZeroDigit = false;
        StringBuilder withoutZeros = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '0' && !isFindFirstNonZeroDigit) {
                continue;
            } else {
                isFindFirstNonZeroDigit = true;
                withoutZeros.append(c);
            }
        }
        return withoutZeros.toString();
    }

    private static int extractAtoiInteger(String s, boolean negativeSign) {
        long atoiNumLong;
        if (s.isEmpty()) {
            return 0;
        } else if (isCharCountBiggerThanInteger(s)) {
            return negativeSign ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        } else if (negativeSign) {
            atoiNumLong = Long.parseLong(s);
            atoiNumLong *= -1;
        } else {
            atoiNumLong = Long.parseLong(s);
        }
        if (atoiNumLong < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (atoiNumLong > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return Math.toIntExact(atoiNumLong);
    }

    private static boolean isCharCountBiggerThanInteger(String s) {
        return s.toCharArray().length > 10;
    }
}
