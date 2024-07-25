import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

import java.util.regex.Pattern;

public class Main {
    static String result;

    public static void main(String[] args) {
        try {
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();
            startUI(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startUI(LineReader reader) {
        while (true) {
            printMainMenu();
            switch (reader.readLine()) {
                case "1":
                    encodeSequence(reader);
                    break;
                case "2":
                    decodeSequence(reader);
                    break;
                case "3":
                    System.exit(0);
                default:
                    System.err.println("Incorrect input, try again");
                    break;
            }
        }
    }

    public static void printMainMenu() {
        System.out.println("========== Main Menu ==========");
        System.out.println(
                "1. Encode text with Hamming code\n" +
                        "2. Decode text\n" +
                        "3. Exit program");
    }

    public static void encodeSequence(LineReader reader) {
        System.out.println("Read from console (1) or from file (2)");
        switch (reader.readLine()) {
            case "1":
                System.out.println("Enter the message you want to encode: ");
                result = Coder.start(reader.readLine());
                break;
            case "2":
                System.out.println("Enter file name to encode");
                String src = reader.readLine();
                result = Coder.start(Helpers.readFile(src));
                break;
            default:
                System.err.println("Invalid input. Try again");
        }
        showPrintOptions();
        handlePrintOptions(reader);
    }

    public static void showPrintOptions() {
        System.out.println("\nYour message has been processed successfully.\nChoose a way to see result:");
        System.out.println("1 - print it in console\n2 - write to file (not yet implemented)\n3. Go back");
    }

    public static void handlePrintOptions(LineReader reader) {
        boolean exit = false;
        while (!exit) {
            switch (reader.readLine()) {
                case "1":
                    printResultToConsole(result);
                    break;
                case "2": // Plug while writing to a file is not supported
                    System.out.println("Enter output file name ");
                    Helpers.writeToFile(result, reader.readLine());
                    break;
                case "3":
                    exit = true;
                    break;
                default:
                    System.err.println("Invalid input. Try again");
                    showPrintOptions();
                    break;
            }
            System.out.println("Show print options again? y/n");
            switch (reader.readLine()) {
                case "y":
                    showPrintOptions();
                    break;
                case "n":
                    exit = true;
                    break;
            }
        }
    }

    public static void printResultToConsole(String result) {
        System.out.println("Your message:");
        System.out.println("\n" + result + "\n");
    }

    public static void decodeSequence(LineReader reader) {
        String message;
        System.out.println("Read from console (1) or from file (2)");
        switch (reader.readLine()) {
            case "1":
                while (true) {
                    System.out.println("Enter the message you want to decode: ");
                    message = reader.readLine();
                    if (!validateBinaryInput(message)) {
                        System.out.println("Try again! Input must contain only 0 and 1");
                    } else {
                        break;
                    }
                }
                result = Decoder.start(message);
                break;
            case "2":
                while (true) {
                    System.out.println("Enter file name to decode");
                    String src = reader.readLine();
                    message = Helpers.readFile(src);
                    if (!validateBinaryInput(message)) {
                        System.out.println("Try again! Input file must contain only 0 and 1");
                    } else {
                        break;
                    }
                }
                result = Decoder.start(message);
                break;
            default:
                System.err.println("Invalid input. Try again");
        }
        showPrintOptions();
        handlePrintOptions(reader);
    }

    private static boolean validateBinaryInput(String message) {
        Pattern pattern = Pattern.compile("[01]*");
        return pattern.matcher(message).matches();
    }
}
