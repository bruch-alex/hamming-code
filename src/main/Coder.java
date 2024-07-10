package main;

import java.util.Scanner;

/**
 *
 **/
public class Coder {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the message you want to encode: ");
        String message = s.nextLine();
        s.close();

        StringBuilder result = new StringBuilder();

        for (char c : message.toCharArray()){
            StringBuilder binaryCharWithEmptyParityBits = convertToBinaryWithParityBits(c);
            StringBuilder binaryCharWithParityBits = Helpers.replaceParityBits(binaryCharWithEmptyParityBits);

            result.append(binaryCharWithParityBits);
        }
        System.out.println("Second: \t" + result);
    }

    /**
     * Converts single symbol to a binary String with 4 parity bits
     * @param symbol symbol to convert
     * @return binary string with 12 bits
     */
    private static StringBuilder convertToBinaryWithParityBits(char symbol){
        StringBuilder binaryString = new StringBuilder();
        int val = symbol;
        System.out.println("value " + val);
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