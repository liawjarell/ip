import java.util.Scanner;

public class Mochi {

    // Constants
    private static Task[] tasks = new Task[100];
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
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                break;
            }
            toPrint = toPrint.concat(String.format("\n%d.%s", i + 1, tasks[i].toString()));
        }
        // Wrap final message and print
        System.out.println(WrapMessage.wrap(toPrint));
    }

    private static void markTask(int taskPosition) {
        tasks[taskPosition].mark();
        String toPrint = WrapMessage.wrap(
                String.format("Nice! I've marked this task as done:\n   %s", tasks[taskPosition].toString()));
        System.out.println(toPrint);
    }

    private static void unmarkTask(int taskPosition) {
        tasks[taskPosition].unmark();
        String toPrint = WrapMessage.wrap(
                String.format("Ok, I've marked this task as not done yet:\n %s", tasks[taskPosition].toString()));
        System.out.println(toPrint);
    }

    private static void addTask(String input) {
        String taskType = input.split(" ", 2)[0];
        String description = input.split(" ", 2)[1];
        if (taskType.equals("todo")) {
            tasks[tasksCount] = new ToDos(description);
        } else if (taskType.equals("deadline")) {
            String date = description.split(" /by ", 2)[1];
            description = description.split(" /by ", 2)[0];
            tasks[tasksCount] = new Deadlines(description, date);
        } else if (taskType.equals("event")) {
            tasks[tasksCount] = new Event(description);
        }

        String toPrint = WrapMessage.wrap(String.format(
                "Got it. I've added this task:\n    %s\nNow you have %d tasks in the list",
                tasks[tasksCount].toString(),
                tasksCount + 1));
        System.out.println(toPrint);
        tasksCount++;

    }

    public static void main(String[] args) {

        // Variables
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
            }
        }

        // Exit message
        System.out.println(BYE_MESSAGE);
    }
}
