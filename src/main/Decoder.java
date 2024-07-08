package main;

import java.util.Scanner;

public class Decoder {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the message you want to decode: ");

        String messageWithParityBits = s.nextLine();

        int positionOfDamagedBit = checkAllControlBits(messageWithParityBits);

        String result = "";
        if (positionOfDamagedBit != 0) {
            String fixedString = fixErrorAndPrintPosition(positionOfDamagedBit, messageWithParityBits);
            result = decode(fixedString);
        } else {
            result = decode(messageWithParityBits);
        }

        System.out.println("Repaired text: " + convertToText(result));
    }

    private static String decode(String messageWithControlBits){
        int j = 0;
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < messageWithControlBits.length(); i++)
        {
            if (!(Helpers.checkPosition(i)))
            {
                outputString.append(messageWithControlBits.charAt(i));

            }
        }
        return outputString.toString();
    }

    private static String fixErrorAndPrintPosition(int y, String messageToFix) {
        StringBuilder sb = new StringBuilder(messageToFix);
        if (y != 0)
        {
            sb.replace(y-1,y,Helpers.changeBit(Character.digit(sb.charAt(y-1),10)));
            System.out.println("\nFound error in " + (y) + " bit");
        }
        return sb.toString();
    }

    private static int checkAllControlBits(String messageWithControlBits){
        int positionsOfWrongBits = 0; // y
        StringBuilder sb = new StringBuilder(messageWithControlBits);

        for (int i = 0; i < messageWithControlBits.length(); i++) {
            if (Helpers.checkPosition(i)) {
                sb.replace(i,i+1, "0");
                String controlBitValue = Helpers.calculateParityBitValue(sb, i);
                if (Character.digit(messageWithControlBits.charAt(i),10) != Character.digit(controlBitValue.charAt(0),10))
                {
                    positionsOfWrongBits += (i +1);
                }
            }
        }
        return positionsOfWrongBits;
    }

    private static void resetControlBits(String messageWithControlBits){

    }

    /**
     * Converts binary string to human-readable string
     * @param messageWithoutControlBits
     * @return human-readable string
     */
    private static String convertToText(String messageWithoutControlBits) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < messageWithoutControlBits.length()/8; i++) {

            int a = Integer.parseInt(messageWithoutControlBits.substring(8*i,(i+1)*8),2);
            sb.append((char)(a));
        }
        return sb.toString();
    }
}