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

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);

        System.out.println(HELLO_MESSAGE);


        String input = myObj.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = myObj.nextLine();
        }
        System.out.println(BYE_MESSAGE);
    }
}
