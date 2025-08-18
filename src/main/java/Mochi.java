import java.util.Scanner;

public class Mochi {

    private static final String HELLO_MESSAGE = """
        ________________________________________
        Hello! I'm MOCHI
        What can I do for you?
        ________________________________________""";

    private static final String BYE_MESSAGE = """
        ________________________________________
        Bye. Hope to see you again!
        ________________________________________""";

    // Function to print out all contents in the list
    private static void printList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                break;
            }
            System.out.printf("%d. %s%n", i+1, list[i]);
        }
    }

    public static void main(String[] args) {

        // Variables
        String[] inputs = new String[100];
        int inputsCount = 0;
        Scanner myObj = new Scanner(System.in);

        // Intro message
        System.out.println(HELLO_MESSAGE);

        // Loop to get input
        while (true) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                // Exit condition
                break;
            } else if (input.equals("list")) {
                // print out inputs
                printList(inputs);
            } else {
                // Add input to list
                inputs[inputsCount] = input;
                System.out.println("added: " + input);
                inputsCount++;
            }
        }

        // Exit message
        System.out.println(BYE_MESSAGE);
    }
}
