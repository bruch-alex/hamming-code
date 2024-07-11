package main;

import java.util.Scanner;

public class Coder {
    public static String startCoder(Scanner s) {
        System.out.println("Enter the message you want to encode: ");
        String message = s.nextLine();
        //Helpers.clearConsole();
        //System.out.println("You entered: " + message);

        StringBuilder result = new StringBuilder();

        for (char c : message.toCharArray()){
            StringBuilder binaryCharWithEmptyParityBits = convertToBinaryWithParityBits(c);
            StringBuilder binaryCharWithCalculatedParityBits = Helpers.replaceParityBits(binaryCharWithEmptyParityBits);

            result.append(binaryCharWithCalculatedParityBits);
        }
        return result.toString();
        //System.out.println("Your encoded text: \n\n" + result);
    }

    /**
     * Converts single symbol to a binary String with 4 parity bits
     * @param symbol symbol to convert
     * @return binary string with 12 bits
     */
    private static StringBuilder convertToBinaryWithParityBits(char symbol){
        StringBuilder binaryString = new StringBuilder();
        int val = symbol;
        for (int i = 0; i < 12  ; i++)
        {
            if(Helpers.checkPosition(i))
            {
                binaryString.append(0);
            }
            else {
                binaryString.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binaryString;
    }
}