package main;

import java.util.Scanner;

public class Coder {
    private static int k = 1; // Amount of control bits
    private static int m = 0; // Length of a message without control bits
    private static int a = 0;

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter the message you want to encode: ");
        String message = s.nextLine();
        s.close();

        String binaryMessage = convertToBinary(message);
        System.out.println(binaryMessage);

        m = binaryMessage.length();
        k = Helpers.calculateAmountOfParityBits(m);
        System.out.printf("m = %d k = %d \n", m, k);

        String binaryMessageWithControlBits = addSpaceForControlBits(binaryMessage);
        System.out.println(binaryMessageWithControlBits);
        String result = Helpers.replaceControlBits(binaryMessageWithControlBits);

        System.out.println("Your decoded result is: " + result);
    }

    /**
     * Converts message to a binary String
     * @param message string to be converted
     * @return binary string with 8 bits
     */
    private static String convertToBinary (String message){
        byte[] bytes = message.getBytes();
        StringBuilder binaryString = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binaryString.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binaryString.toString();
    }

    private static String addSpaceForControlBits(String binaryMessage){
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m + k  ; i++)      // этим цмклом заполняем массив с контрольными битами = 0
        {
            if(Helpers.checkPosition(i))
            {
                sb.append("0");
                a++;
            }
            else sb.append(binaryMessage.charAt(i - a));
        }
        return sb.toString();
    }
}