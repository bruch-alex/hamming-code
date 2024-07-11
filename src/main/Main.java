package main;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startUI();
    }

    public static void startUI() {
        Helpers.clearConsole();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Hello Human!\nPls select a way to interact with an app");
            System.out.println("1 - Encode text\n2 - Decode Text\n3 - Exit");
            String result;

            switch (Integer.parseInt(scanner.nextLine())) {
                case 1:
                    while (true) {
                        result = Coder.startCoder(scanner);
                        System.out.println("Your message was encoded successfully.\nChoose a way to see result:");
                        System.out.println("1 - print it in console\n2 - write to file (Not yet implemented)");
                        switch (Integer.parseInt(scanner.nextLine())) {
                            case 1:
                                System.out.println("Pls copy your encoded text: \n\n" + result + "\n");
                                System.out.println("Do u want to decode it now? (y/n)");
                                switch (scanner.nextLine()) {
                                    case "y":
                                        while (true) {
                                            Helpers.clearConsole();
                                            Decoder.startDecoder();
                                            System.out.println("Try decode again? (y/n)");
                                            switch (scanner.nextLine()) {
                                                case "y":
                                                    Helpers.clearConsole();
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
                        switch (scanner.nextLine()) {
                            case "y":
                                Helpers.clearConsole();
                                break;
                            case "n":
                                System.exit(0);
                                break;
                        }
                    }
                case 2:
                    while (true) {
                        Decoder.startDecoder();
                        System.out.println("\nTry again? (y/n)");
                        switch (scanner.nextLine()) {
                            case "y":
                                Helpers.clearConsole();
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
