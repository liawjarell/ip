import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mochi {

    // Constants
    private TaskList tasks;
    private static int tasksCount = 0;
    private Storage storage;

    public Mochi() {
        this.tasks = new TaskList(new ArrayList<>());
    }

    public void run() {
        // Intro message
        System.out.println(HELLO_MESSAGE);

        // Initialise Storage
//        Storage s = new Storage("data/test.txt");
//        tasks = s.readTasks();
//        tasksCount = tasks.size();

        // Loop to get input
        getInput();

        // Exit message
        System.out.println(BYE_MESSAGE);
    }

    // Starting message. Wrapped
    private static final String HELLO_MESSAGE = WrapMessage.wrap(
            "Hello! I'm MOCHI!\nWhat can i do for you?"
    );

    // Ending message. Wrapped
    private static final String BYE_MESSAGE = WrapMessage.wrap("Bye. Hope to see you again!");

    // Calls TaskList's printList()
    private void printList() {
        this.tasks.printList();
    }

    // Calls TaskList's markTask()
    private void markTask(int taskPosition) throws MochiException{
        this.tasks.markTask(taskPosition);
    }

    // Calls TaskList's unmarkTask()
    private void unmarkTask(int taskPosition) throws MochiException {
        this.tasks.unmarkTask(taskPosition);
    }

    // Calls TaskList's addTask()
    private void addTask(String input) throws MochiException {
        this.tasks.addTask(input);
    }

    // Calls TaskList's deleteTask()
    private void deleteTask(int taskPosition) throws MochiException {
        this.tasks.deleteTask(taskPosition);
    }

    // Loop to getInput
    private void getInput() {
        // Variables
        Scanner myObj = new Scanner(System.in);

        while (true) {
            try {
                String input = myObj.nextLine();
                String head = input.split(" ", 2)[0];
                if (input.equals("bye")) {
                    // Exit condition
                    break;
                } else if (input.equals("list")) {
                    // Print out inputs
                    this.tasks.printList();
                } else if (head.equals("mark") || head.equals("unmark") || head.equals("delete")) {
                    // Mark , unmark or delete task
                    int taskPosition = 0;
                    try {
                        taskPosition = Integer.parseInt(input.split(" ")[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new MochiException("Please input the task number!");
                    }
                    if (head.equals("mark")) {
                        this.tasks.markTask(taskPosition);
                    } else if (head.equals("unmark")) {
                        this.tasks.unmarkTask(taskPosition);
                    } else {
                        this.tasks.deleteTask(taskPosition);
                    }
                } else if (head.equals("todo")) {
                    this.tasks.addTask(input);
                } else if (head.equals("deadline")) {
                    this.tasks.addTask(input);
                } else if (head.equals("event")) {
                    this.tasks.addTask(input);
                } else {
                    // Input does not match any of the given commands
                    throw new MochiException("OOPS! I'm sorry but I don't know what that means!");
                }
//                storage.saveTask(tasks); // Remove all static commands.
            } catch (MochiException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Mochi().run();
    }
}
