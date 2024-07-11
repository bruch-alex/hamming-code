package main;

import java.util.Scanner;

public class Decoder {
    public static void startDecoder() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the message you want to decode: ");
        String messageWithParityBits = s.nextLine();

        boolean withErrors = false;
        StringBuilder sb = new StringBuilder(messageWithParityBits);
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < messageWithParityBits.length(); i+=12) {
            String binaryCharWithParityBits = sb.substring(i , i+12);
            int positionOfDamagedBit = checkAllControlBits(binaryCharWithParityBits);
            if (positionOfDamagedBit != 0) {
                withErrors = true;
                binaryCharWithParityBits = fixError(positionOfDamagedBit, binaryCharWithParityBits);
                System.out.printf("Fix error in symbol: %d at position: %d\n", i/12 + 1, positionOfDamagedBit + 1);
            }
            result.append(decode(binaryCharWithParityBits));
        }
        if (!withErrors){
            System.out.println("!!!Received without errors!!!");
        }
        System.out.println("Your message: \n\n" + result);
    }

    private static char decode(String messageWithControlBits){
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < messageWithControlBits.length(); i++)
        {
            if (i != 0 && i != 1 && i != 3 && i != 7)
            {
                outputString.append(messageWithControlBits.charAt(i));
            }
        }
        return (char)(Integer.parseInt(outputString.toString(),2));
    }

    private static String fixError(int y, String messageToFix) {
        StringBuilder sb = new StringBuilder(messageToFix);
        if (y != 0)
        {
            sb.replace(y-1,y,Helpers.changeBit(Character.digit(sb.charAt(y-1),10)));
        }
        return sb.toString();
    }

    private static int checkAllControlBits(String messageWithControlBits){
        int positionsOfWrongBits = 0; // y
        StringBuilder sb = new StringBuilder(messageWithControlBits);

        for (int i = 0; i < messageWithControlBits.length(); i++) {
            if (Helpers.checkPosition(i)) {
                sb.replace(i,i+1, "0");
                int controlBitValue = Helpers.calculateParityBitValue(sb, i);
                if (Character.digit(messageWithControlBits.charAt(i),10) != controlBitValue)
                {
                    positionsOfWrongBits += (i +1);
                }
            }
        }
        return positionsOfWrongBits;
    }
}