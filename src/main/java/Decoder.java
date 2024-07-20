public class Decoder {
    public static String start(String message) {
        boolean withErrors = false;
        StringBuilder sb = new StringBuilder(message);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < message.length(); i += 12) {
            String binaryCharWithParityBits = sb.substring(i, i + 12);
            int positionOfDamagedBit = checkAllControlBits(binaryCharWithParityBits);
            if (positionOfDamagedBit != 0) {
                withErrors = true;
                binaryCharWithParityBits = fixError(positionOfDamagedBit, binaryCharWithParityBits);
                System.out.printf("Fix error in symbol: %d at position: %d\n", i / 12 + 1, positionOfDamagedBit + 1);
            }
            result.append(removeParityBits(binaryCharWithParityBits));
        }
        if (!withErrors) {
            System.out.println("\n!!!Received without errors!!!");
        }
        return result.toString();
    }

    private static char removeParityBits(String messageWithParityBits) {
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < messageWithParityBits.length(); i++) {
            if (i != 0 && i != 1 && i != 3 && i != 7) {
                outputString.append(messageWithParityBits.charAt(i));
            }
        }
        return (char) (Integer.parseInt(outputString.toString(), 2));
    }

    private static String fixError(int positionOfWrongBit, String messageToFix) {
        StringBuilder sb = new StringBuilder(messageToFix);
        if (positionOfWrongBit != 0) {
            sb.replace(positionOfWrongBit - 1, positionOfWrongBit, String.valueOf(Helpers.changeBit(Character.digit(sb.charAt(positionOfWrongBit - 1), 10))));
        }
        return sb.toString();
    }

    private static int checkAllControlBits(String messageWithControlBits) {
        int positionsOfWrongBits = 0; // y
        StringBuilder sb = new StringBuilder(messageWithControlBits);

        for (int i = 0; i < messageWithControlBits.length(); i++) {
            if (Helpers.checkPosition(i)) {
                sb.replace(i, i + 1, "0");
                int controlBitValue = Helpers.calculateParityBitValue(i, sb);
                if (Character.digit(messageWithControlBits.charAt(i), 10) != controlBitValue) {
                    positionsOfWrongBits += (i + 1);
                }
            }
        }
        return positionsOfWrongBits;
    }
}