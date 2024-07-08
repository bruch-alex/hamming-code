package main;

public class Helpers {

    public static double log2(int n) {
        return Math.log(n) / Math.log(2);
    }

    public static String calculateParityBitValue(StringBuilder binaryMessageWithEmptyParityBits, int i){
        StringBuilder sb = new StringBuilder(binaryMessageWithEmptyParityBits);
        int sum = getSum(i, sb);
        return String.valueOf(sum % 2);
    }


    /**
     * Calculate and replace all control bits
     * @param binaryMessageWithEmptyParityBits
     * @return
     */
    public static String replaceControlBits(String binaryMessageWithEmptyParityBits) {
        StringBuilder sb = new StringBuilder(binaryMessageWithEmptyParityBits);

        for (int i = 0; i < binaryMessageWithEmptyParityBits.length() - 1; i++)
        {
            if (Helpers.checkPosition(i))
            {
                String controlBitValue = calculateParityBitValue(sb, i);
                sb.replace(i, i+1, controlBitValue);
            }
        }
        return sb.toString();
    }

    public static int getSum(int parityBitPosition, StringBuilder messageWithEmptyParityBits) {
        int sum = 0;
        for (int i = parityBitPosition; i < messageWithEmptyParityBits.length(); i += 2 * (parityBitPosition+1)){

            for (int j = 0; j < parityBitPosition+1 && j+i<messageWithEmptyParityBits.length(); j++) {

                int value = Character.digit(messageWithEmptyParityBits.charAt(i  + j),10);

                sum += value;
            }
        }
        return sum;
    }

    public static int calculateAmountOfParityBits(int messageLength){
        int amountOfParityBits = 1;
        while (Math.pow(2, amountOfParityBits) < amountOfParityBits + messageLength) {
            amountOfParityBits++;
        }
        return amountOfParityBits;
    }

    /**
     * This method checks if the @param position is the location for the control bit.
     * @param position Number of element in a binary sequence. Starts with 1.
     * @return true if position is a place for control bit
     */
    public static boolean checkPosition(int position){
        return Helpers.log2(position + 1) % 1 == 0;
    }

    public static String changeBit(int value){
        if (value == 0){
            return "1";
        }
        return "0";
    }
}