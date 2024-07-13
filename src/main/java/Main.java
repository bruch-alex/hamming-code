import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.InfoCmp;

public class Main {
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
            terminal.puts(InfoCmp.Capability.clear_screen);
            terminal.flush();
            System.out.println("Hello Human!\nWhat do u want to do now?");
            System.out.println("1 - Encode text\t2 - Decode Text\t3 - Exit");
            String result;

            switch (Integer.parseInt(reader.readLine())) {
                case 1:
                    while (true) {
                        System.out.println("Enter the message you want to encode: ");
                        result = Coder.startCoder(reader.readLine());
                        terminal.puts(InfoCmp.Capability.clear_screen);
                        terminal.flush();
                        System.out.println("Your message was encoded successfully.\nChoose a way to see result:");
                        System.out.println("1 - print it in console\n2 - write to file (Not yet implemented)");
                        switch (Integer.parseInt(reader.readLine())) {
                            case 1:
                                terminal.puts(InfoCmp.Capability.clear_screen);
                                terminal.flush();
                                System.out.println("Pls copy your encoded text:\n\n" + result + "\n");
                                System.out.println("Do u want to decode it now? (y/n)");
                                switch (reader.readLine()) {
                                    case "y":
                                        while (true) {
                                            terminal.puts(InfoCmp.Capability.clear_screen);
                                            terminal.flush();
                                            System.out.println("Enter your encoded text:");
                                            result = Decoder.startDecoder(reader.readLine());
                                            System.out.println("Your decoded text:\n\n" + result + "\n");
                                            System.out.println("Try decode again? (y/n)");
                                            switch (reader.readLine()) {
                                                case "y":
                                                    continue;
                                                case "n":
                                                    break;
                                            }
                                            break;
                                        }
                                        break;
                                    case "n":
                                        System.exit(0);
                                }
                                break;
                            case 2:
                                System.out.println("not yet implemented");
                                break;
                        }
                        System.out.println("Do u want to encode something else? (y/n)");
                        switch (reader.readLine()) {
                            case "y":
                                break;
                            case "n":
                                System.exit(0);
                                break;
                        }
                    }
                case 2:
                    while (true) {
                        System.out.println("Enter the message you want to decode: ");
                        result = Decoder.startDecoder(reader.readLine());
                        System.out.println("Your decoded text:\n\n" + result + "\n");
                        System.out.println("\nTry again? (y/n)");
                        switch (reader.readLine()) {
                            case "y":
                                break;
                            case "n":
                                System.exit(0);
                        }
                    }
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Incorrect input, try again");
                    break;
            }
        }
    }
}
