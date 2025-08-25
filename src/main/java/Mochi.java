import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mochi {

    // Constants
    private TaskList tasks;
    private Storage storage;

    public Mochi(String filePath) {
        try {
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.readTasks());
        } catch (MochiException e) {
            System.out.println(e.getMessage());
        }
    }

    public void run() {
        // Intro message
        System.out.println(HELLO_MESSAGE);

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
    public void printList() {
        this.tasks.printList();
    }

    // Calls TaskList's markTask()
    public void markTask(int taskPosition) throws MochiException{
        this.tasks.markTask(taskPosition);
        this.saveTasks(this.tasks);
    }

    // Calls TaskList's unmarkTask()
    public void unmarkTask(int taskPosition) throws MochiException {
        this.tasks.unmarkTask(taskPosition);
        this.saveTasks(this.tasks);
    }

    // Calls TaskList's addTask()
    public void addTask(String input) throws MochiException {
        this.tasks.addTask(input);
        this.saveTasks(this.tasks);
    }

    // Calls TaskList's deleteTask()
    public void deleteTask(int taskPosition) throws MochiException {
        this.tasks.deleteTask(taskPosition);
        this.saveTasks(this.tasks);
    }

    // Calls Storage's saveTasks()
    public void saveTasks(TaskList tasks) throws MochiException{
        this.storage.saveTasks(this.tasks.getTasks());
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
                    this.printList();
                } else if (head.equals("mark") || head.equals("unmark") || head.equals("delete")) {
                    // Mark , unmark or delete task
                    int taskPosition = 0;
                    try {
                        taskPosition = Integer.parseInt(input.split(" ")[1]) - 1;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new MochiException("Please input the task number!");
                    }
                    if (head.equals("mark")) {
                        this.markTask(taskPosition);
                    } else if (head.equals("unmark")) {
                        this.unmarkTask(taskPosition);
                    } else {
                        this.deleteTask(taskPosition);
                    }
                } else if (head.equals("todo")) {
                    this.addTask(input);
                } else if (head.equals("deadline")) {
                    this.addTask(input);
                } else if (head.equals("event")) {
                    this.addTask(input);
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
//        new Mochi().run();
        new Mochi("data/tasks.txt").run();
    }
}
