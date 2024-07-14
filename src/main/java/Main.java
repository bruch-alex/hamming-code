import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class Main {
    static String result;

    public static void main(String[] args) {
        try {
            Terminal terminal = TerminalBuilder.builder().system(true).build();
            LineReader reader = LineReaderBuilder.builder().terminal(terminal).build();
            startUI(reader, terminal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startUI(LineReader reader, Terminal terminal) {
        while (true) {
            printMainMenu(terminal);
            switch (reader.readLine()) {
                case "1":
                    encodeSequence(reader, terminal);
                    break;
                case "2":
                    decodeSequence(reader, terminal);
                    break;
                case "3":
                    System.exit(0);
                default:
                    System.err.println("Incorrect input, try again");
                    break;
            }
        }
    }

    public static void printMainMenu(Terminal terminal) {
        System.out.println("========== Main Menu ==========");
        System.out.println(
                "1. Encode text with Hamming code\n" +
                        "2. Decode text\n" +
                        "3. Exit program");
    }

    public static void encodeSequence(LineReader reader, Terminal terminal) {
        System.out.println("Enter the message you want to encode: ");
        result = Coder.start(reader.readLine());

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
                    exit = true;
                    break;
                case "2": // Plug while writing to a file is not supported
                    System.err.println("Not implemented");
                    showPrintOptions();
                    break;
                case "3":
                    exit = true;
                    break;
                default:
                    System.err.println("Invalid input. Try again");
                    showPrintOptions();
                    break;
            }
        }
    }

    public static void printResultToConsole(String result) {
        System.out.println("Your message:");
        System.out.println("\n" + result + "\n");
    }

    public static void decodeSequence(LineReader reader, Terminal terminal) {
        System.out.println("Enter text you want to decode:");
        result = Decoder.start(reader.readLine());

        showPrintOptions();
        handlePrintOptions(reader);
    }
}
