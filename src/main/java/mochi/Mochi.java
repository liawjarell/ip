package mochi;

import mochi.storage.Storage;
import mochi.task.TaskList;
import mochi.parser.Parser;
import mochi.ui.Ui;
import mochi.exception.MochiException;
import mochi.task.Task;

import java.util.Scanner;

/**
 * Mochi is a task management application that facilitates the management of tasks
 * in a list. It provides a user interface to interact with the user and display
 * the list of tasks. It also facilitates the persistence of tasks across program executions.
 */
public class Mochi {

    /**
     * A TaskList object that represents the list of tasks currently managed by Mochi.
     * It contains methods for manipulating the task list, such as adding, deleting,
     * marking, and unmarking tasks. This instance is initialized with tasks loaded
     * from storage and is shared across various operations to manage the user's tasks.
     */
    private TaskList tasks;

    /**
     * Represents the storage system for managing task data in the Mochi application.
     * It is responsible for saving and retrieving tasks from a specified file path.
     * The storage facilitates the persistence of task information across program executions.
     */
    private Storage storage;

    /**
     * Represents the user interface component of the application. This instance is responsible
     * for communicating with the user, displaying messages, and formatting outputs consistently.
     * It provides feedback and prompts to the user based on actions performed in the application.
     * <p>
     * The Ui instance handles all user-facing interactions by wrapping messages with a consistent
     *  delimiter and informs the user about different task actions such as adding, marking,
     * unmarking, or deleting tasks.
     */
    private Ui ui;

    /**
     * Constructs a new Mochi instance. This initializes the user interface (Ui),
     * sets up storage for tasks from the specified file path, and loads tasks
     * from storage into a TaskList.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Mochi(String filePath) {
        try {
            ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.readTasks());
        } catch (MochiException e) {
            ui.warn(e.getMessage());
        }
    }

    /**
     * Executes the main program logic of the Mochi application.
     * This method enters a loop to continuously read and parse user input
     * until the user decides to exit the program explicitly.
     * It invokes the {@code getInput()} method to process user commands
     * and ensures application termination by calling the {@code exit()} method.
     */
    public void run() {
        // Loop to get input
        getInput();

        exit();
    }

    /**
     * Terminates the Mochi application.
     * This method performs cleanup actions before exiting the program.
     * It ensures that a goodbye message is displayed to the user via the user interface
     * and then ends the program by invoking {@code System.exit(0)}.
     */
    public void exit() {
        ui.goodbye();
        System.exit(0);
    }

    /**
     * Retrieves the total number of tasks in the task list.
     *
     * @return The total count of tasks currently stored.
     */
    public int getTasksCount() { return this.tasks.getTasksCount(); }

    /**
     * Prints the list of tasks currently stored in the application.
     * This method delegates the display functionality to the user interface component,
     * which formats and prints the list of tasks from the internal task list.
     * If the task list is empty, a corresponding message is displayed to indicate
     * that there are no tasks.
     */
    public void printList() {
        ui.showTasks(this.tasks);
    }

    /**
     * Marks a task at the specified position in the task list as completed.
     * This method updates the internal task list, notifies the user of the change,
     * and saves the updated tasks to storage.
     *
     * @param taskPosition The zero-based index of the task to be marked as completed.
     * @throws MochiException If the specified task position is invalid, or any error occurs while marking the task.
     */
    public void markTask(int taskPosition) throws MochiException {
        Task temp = this.tasks.markTask(taskPosition);
        ui.notifyMarkTask(temp.toString());
        this.saveTasks(this.tasks);
    }

    /**
     * Unmarks a task at the specified position in the task list as incomplete.
     * This method updates the internal task list, notifies the user of the change,
     * and saves the updated tasks to storage.
     *
     * @param taskPosition The zero-based index of the task to be unmarked as incomplete.
     * @throws MochiException If the specified task position is invalid, or any error occurs while unmarking the task.
     */
    public void unmarkTask(int taskPosition) throws MochiException {
        Task temp = this.tasks.unmarkTask(taskPosition);
        ui.notifyUnmarkTask(temp.toString());
        this.saveTasks(this.tasks);
    }

    /**
     * Adds a new task to the task list.
     * This method updates the internal task list, notifies the user of the change,
     * and saves the updated tasks to storage.
     *
     * @param input The input string representing the task to be added.
     * @throws MochiException If any error occurs while parsing the input or adding the task.
     */
    public void addTask(String[] input) throws MochiException {
        Task temp = this.tasks.addTask(input);
        ui.notifyAddTask(temp.toString(), this.tasks.getTasksCount());
        this.saveTasks(this.tasks);
    }

    /**
     * Deletes a task at the specified position in the task list.
     * This method updates the internal task list, notifies the user of the change,
     * and saves the updated tasks to storage.
     *
     * @param taskPosition The zero-based index of the task to be deleted.
     * @throws MochiException If the specified task position is invalid, or any error occurs while deleting the task.
     */
    public void deleteTask(int taskPosition) throws MochiException {
        Task temp = this.tasks.deleteTask(taskPosition);
        ui.notifyDeleteTask(temp.toString(), this.tasks.getTasksCount());
        this.saveTasks(this.tasks);
    }

    /**
     * Saves the current list of tasks to storage.
     * This method delegates the task saving functionality to the storage component.
     *
     * @param tasks The list of tasks to be saved.
     * @throws MochiException If any error occurs while saving the tasks.
     */
    public void saveTasks(TaskList tasks) throws MochiException {
        this.storage.saveTasks(this.tasks.getTasks());
    }

    /**
     * Loop that processes user input and invokes the appropriate methods to handle the input.
     * This method delegates the task of parsing user input to the Parser class.
     * It continuously reads user input until the user decides to exit the program.
     */
    private void getInput() {
        // Variables
        Scanner myObj = new Scanner(System.in);

        while (true) {
            try {
                String input = myObj.nextLine();
                Parser.parseGeneralInput(this, input);
            } catch (MochiException e) {
                this.ui.warn(e.getMessage());
            }
        }
    }

    /**
     * The entry point for the Mochi task management application.
     * This method initializes the application with a specified task storage file
     * and starts the main program logic by invoking the {@code run()} method.
     *
     * @param args Command-line arguments passed to the program.
     *             Currently not used by the Mochi application.
     */
    public static void main(String[] args) {
//        new mochi.Mochi().run();
        new Mochi("data/tasks.txt").run();
    }
}
