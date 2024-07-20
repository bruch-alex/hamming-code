import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Helpers {
    public static StringBuilder replaceParityBits(StringBuilder binaryMessageWithEmptyParityBits) {
        for (int i = 1; i <= 12; i *= 2)
        {
                int parityBitValue = calculateParityBitValue(i - 1, binaryMessageWithEmptyParityBits);
                binaryMessageWithEmptyParityBits.replace(i-1, i, String.valueOf(parityBitValue));
        }
        return binaryMessageWithEmptyParityBits;
    }

    /**
     * @param parityBitPosition - position of parity bit in message (starts with 0)
     * @return value of parity bit (1 or 0)
     */
    public static int calculateParityBitValue(int parityBitPosition, StringBuilder messageWithEmptyParityBits) {
        int sum = 0;
        for (int i = parityBitPosition; i < messageWithEmptyParityBits.length(); i += 2 * (parityBitPosition+1)){

            for (int j = 0; j < parityBitPosition+1 && j+i<messageWithEmptyParityBits.length(); j++) {

                int value = Character.digit(messageWithEmptyParityBits.charAt(i  + j),10);

                sum += value;
            }
        }
        return sum % 2;
    }

    /**
     * This method checks if the position is the location for the parity bit.
     * @param i Number of element in a binary sequence (starts with 0)
     * @return true if position is a place for parity bit
     */
    public static boolean checkPosition(int i){
        return i == 0 || i == 1 || i == 3 || i == 7;
    }

    /**
     * Changes value between 0 and 1;
     * @param value binary value to be converted
     * @return 1, if value is 0 and return 0, if value is 1.
     */
    public static int changeBit(int value){
        return value ^ 1;
    }

    public static String readFile(String file){
        StringBuilder sb = new StringBuilder();
        try(FileInputStream input = new FileInputStream(file))
        {
            byte[] buffer = input.readAllBytes();
            for (byte c : buffer){
                sb.append((char) c);
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeToFile(String result, String fileName){
        StringBuilder sb = new StringBuilder();
        try(FileOutputStream output = new FileOutputStream(fileName))
        {
            byte[] buffer = result.getBytes();
            output.write(buffer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

