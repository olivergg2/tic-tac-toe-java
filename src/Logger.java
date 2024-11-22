import java.util.Scanner;

public class Logger {
    private static final Scanner scanner = new Scanner(System.in);

    public static void print(String... strings) {
        // Join all strings to a single string
        var stringToPrint = String.join(" ", strings);

        System.out.println(stringToPrint);
    }

    public static void newline() {
        print();
    }

    public static String read() {
        return read("");
    }

    public static String read(String prompt) {
        if (!prompt.isEmpty()) print(prompt);

        System.out.print("> ");

        return scanner.nextLine();
    }

    public static int readInt() {
        return readInt("");
    }

    public static int readInt(String prompt) {
        return readInt(prompt, "");
    }

    public static int readInt(String prompt, String errorMessage) {
        int result;

        while (true) {
            try {
                var input = Logger.read(prompt);
                result = Integer.parseInt(input);
                break;
            } catch (Exception e) {
                if (!errorMessage.isEmpty()) print(errorMessage);
            }
        }

        return result;
    }
}
