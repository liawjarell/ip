import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Mochi {

    // Constants
//    private static Task[] tasks = new Task[100];
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int tasksCount = 0;

    // Starting message. Wrapped
    private static final String HELLO_MESSAGE = WrapMessage.wrap(
            "Hello! I'm MOCHI!\nWhat can i do for you?"
    );

    // Ending message. Wrapped
    private static final String BYE_MESSAGE = WrapMessage.wrap("Bye. Hope to see you again!");

    // Function to print out all contents in the Task array
    private static void printList() {
        String toPrint = "Here are the tasks in your list:";
        for (int i = 0; i < tasks.size(); i++) {
//            if (tasks.get(i) == null) {
//                break;
//            }
            toPrint = toPrint.concat(String.format("\n%d.%s", i + 1, tasks.get(i).toString()));
        }
        // Wrap final message and print
        System.out.println(WrapMessage.wrap(toPrint));
    }

    private static void markTask(int taskPosition) throws MochiException{
        if (taskPosition < 0 || taskPosition >= tasksCount) {
            throw new MochiException("Please input a valid task number!");
        }
        tasks.get(taskPosition).mark();
        String toPrint = WrapMessage.wrap(
                String.format("Nice! I've marked this task as done:\n   %s", tasks.get(taskPosition).toString()));
        System.out.println(toPrint);
    }

    private static void unmarkTask(int taskPosition) throws MochiException {
        if (taskPosition < 0 || taskPosition >= tasksCount) {
            throw new MochiException("Please input a valid task number!");
        }
        tasks.get(taskPosition).unmark();
        String toPrint = WrapMessage.wrap(
                String.format("Ok, I've marked this task as not done yet:\n %s", tasks.get(taskPosition).toString()));
        System.out.println(toPrint);
    }

    private static void addTask(String input) throws MochiException {
        String taskType = input.split(" ", 2)[0];
        String description = "";

        try {
            // If task is provided without description, splitting of string throws ArrayIndexOutofBoundsException
            description = input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MochiException("Task description cannot be empty!");
        }

        if (taskType.equals("todo")) {
            tasks.add(new ToDos(description));
        } else if (taskType.equals("deadline")) {
            tasks.add(new Deadlines(description));
        } else if (taskType.equals("event")) {
            tasks.add(new Event(description));
        }

        String toPrint = WrapMessage.wrap(String.format(
                "Got it. I've added this task:\n    %s\nNow you have %d tasks in the list",
                tasks.get(tasksCount).toString(),
                tasksCount + 1));
        System.out.println(toPrint);
        tasksCount++;

    }

    private static void tryAgain(String message) {
        String toPrint = WrapMessage.wrap(message);
        System.out.println(toPrint);
    }

    private static void getInput() throws MochiException{
        // Variables
        Scanner myObj = new Scanner(System.in);

        while (true) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                // Exit condition
                break;
            } else if (input.equals("list")) {
                // Print out inputs
                printList();
            } else if (input.split(" ")[0].equals("mark")) {
                // Mark task
                int taskPosition = Integer.parseInt(input.split(" ")[1]) - 1;
                markTask(taskPosition);
            } else if (input.split(" ")[0].equals("unmark")) {
                // Unmark task
                int taskPosition = Integer.parseInt(input.split(" ")[1]) - 1;
                unmarkTask(taskPosition);
            } else if (input.split(" ", 2)[0].equals("todo")) {
                // Add input to list
                addTask(input);
            } else if (input.split(" ", 2)[0].equals("deadline")) {
                addTask(input);
            } else if (input.split(" ", 2)[0].equals("event")) {
                addTask(input);
            } else {
                // Input does not match any of the given commands
                throw new MochiException("OOPS! I'm sorry but I don't know what that means!");
            }
        }
    }

    public static void main(String[] args) {

        // Intro message
        System.out.println(HELLO_MESSAGE);

        // Loop to get input
        try {
            getInput();
        } catch (MochiException e) {
            System.out.println(e.getMessage());
        }

        // Exit message
        System.out.println(BYE_MESSAGE);
    }
}
