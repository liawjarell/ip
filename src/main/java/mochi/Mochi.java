package mochi;

import java.util.Scanner;

import mochi.exception.MochiException;
import mochi.parser.Parser;
import mochi.storage.Storage;
import mochi.task.Task;
import mochi.task.TaskList;
import mochi.ui.Ui;


/**
 * Mochi is a task management application that facilitates the management of tasks
 * in a list. It provides a user interface to interact with the user and display
 * the list of tasks. It also facilitates the persistence of tasks across program executions.
 */
public class Mochi {

    /**
     * A TaskList object that represents the list of tasks currently managed by Mochi.
     * <p>
     * It contains methods for manipulating the task list, such as adding, deleting,
     * marking, and unmarking tasks. This instance is initialized with tasks loaded
     * from storage and is shared across various operations to manage the user's tasks.
     */
    private TaskList tasks;

    /**
     * Represents the storage system for managing task data in the Mochi application.
     * <p>
     * It is responsible for saving and retrieving tasks from a specified file path.
     * The storage facilitates the persistence of task information across program executions.
     */
    private Storage storage;

    /**
     * Represents the user interface component of the application.
     * <p>
     * This instance is responsible for communicating with the user, displaying messages,
     * and formatting outputs consistently. It provides feedback and prompts to the user based
     * on actions performed in the application.
     * <p>
     * The Ui instance handles all user-facing interactions by wrapping messages with a consistent
     * delimiter and informs the user about different task actions such as adding, marking,
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
     * <p>
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
     * Prints a welcome message to the user.
     * <p>
     * @return A welcome message to be displayed to the user.
     */
    public String welcome() {
        return ui.welcome();
    }

    /**
     * Terminates the Mochi application.
     * <p>
     * This method performs cleanup actions before exiting the program.
     * It ensures that a goodbye message is displayed to the user via the user interface
     * and then ends the program by invoking {@code System.exit(0)}.
     */
    public String exit() {
        return ui.goodbye();
    }

    /**
     * Retrieves the total number of tasks in the task list.
     * <p>
     * @return The total count of tasks currently stored.
     */
    public int getTasksCount() {
        return this.tasks.getTasksCount();
    }

    /**
     * Prints the list of tasks currently stored in the application.
     * <p>
     * This method delegates the display functionality to the user interface component,
     * which formats and prints the list of tasks from the internal task list.
     * If the task list is empty, a corresponding message is displayed to indicate
     * that there are no tasks.
     */
    public String printList() {
        return ui.showTasks(this.tasks);
    }

    /**
     * Marks a task at the specified position in the task list as completed.
     * <p>
     * This method updates the internal task list, notifies the user of the change,
     * and saves the updated tasks to storage.
     *
     * @param taskPosition The zero-based index of the task to be marked as completed.
     * @throws MochiException If the specified task position is invalid, or any error occurs while marking the task.
     */
    public String markTask(int taskPosition) throws MochiException {
        Task temp = this.tasks.markTask(taskPosition);
        this.saveTasks(this.tasks);
        return ui.notifyMarkTask(temp.toString());
    }

    /**
     * Unmarks a task at the specified position in the task list as incomplete.
     * <p>
     * This method updates the internal task list, notifies the user of the change,
     * and saves the updated tasks to storage.
     *
     * @param taskPosition The zero-based index of the task to be unmarked as incomplete.
     * @throws MochiException If the specified task position is invalid, or any error occurs while unmarking the task.
     */
    public String unmarkTask(int taskPosition) throws MochiException {
        Task temp = this.tasks.unmarkTask(taskPosition);
        this.saveTasks(this.tasks);
        return ui.notifyUnmarkTask(temp.toString());
    }

    /**
     * Adds a new task to the task list.
     * <p>
     * This method updates the internal task list, notifies the user of the change,
     * and saves the updated tasks to storage.
     *
     * @param input The input string representing the task to be added.
     * @throws MochiException If any error occurs while parsing the input or adding the task.
     */
    public String addTask(String[] input) throws MochiException {
        Task temp = this.tasks.addTask(input);
        this.saveTasks(this.tasks);
        return ui.notifyAddTask(temp.toString(), this.tasks.getTasksCount());
    }

    /**
     * Deletes a task at the specified position in the task list.
     * <p>
     * This method updates the internal task list, notifies the user of the change,
     * and saves the updated tasks to storage.
     *
     * @param taskPosition The zero-based index of the task to be deleted.
     * @throws MochiException If the specified task position is invalid, or any error occurs while deleting the task.
     */
    public String deleteTask(int taskPosition) throws MochiException {
        Task temp = this.tasks.deleteTask(taskPosition);
        this.saveTasks(this.tasks);
        return ui.notifyDeleteTask(temp.toString(), this.tasks.getTasksCount());
    }

    /**
     * Tags a task at the specified position in the task list.
     * <p>
     * This method updates the internal task list, notifies the user of the change,
     * and saves the updated tasks to storage.
     *
     * @param taskPosition The zero-based index of the task to be tagged.
     * @param tag The tag to be assigned to the task.
     * @return A string indicating the success of the tagging operation.
     * @throws MochiException If the specified task position is invalid, or any error occurs while tagging the task.
     */
    public String tagTask(int taskPosition, String tag) throws MochiException {
        Task temp = this.tasks.tagTask(taskPosition, tag);
        this.saveTasks(this.tasks);
        return ui.notifyTaggedTask(temp.toString());
    }

    /**
     * Untags a task at the specified position in the task list.
     * <p>
     * This method updates the internal task list, notifies the user of the change,
     * and saves the updated tasks to storage.
     *
     * @param taskPosition The zero-based index of the task to be untagged.
     * @return A string indicating the success of the tagging operation.
     * @throws MochiException If the specified task position is invalid, or any error occurs while untagging the task.
     */
    public String untagTask(int taskPosition) throws MochiException {
        Task temp = this.tasks.untagTask(taskPosition);
        this.saveTasks(this.tasks);
        return ui.notifyUntaggedTask(temp.toString());
    }

    /**
     * Finds tasks that match the specified keyword and displays them to the user.
     * <p>
     * This method searches the internal task list for tasks that contain the specified keyword, and returns
     * it as a new TaskList instance. If no tasks are found, a corresponding message is displayed to indicate
     * that no tasks were found.
     *
     * @param keyword The keyword to search for in the task descriptions.
     */
    public String find(String keyword) {
        TaskList newTasks = this.tasks.find(keyword);
        return ui.showMatchingTasks(newTasks);
    }

    /**
     * Saves the current list of tasks to storage.
     * <p>
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
     * <p>
     * This method delegates the task of parsing user input to the Parser class.
     * It continuously reads user input until the user decides to exit the program.
     */
    private void getInput() {
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
     * Parses the user input and returns the appropriate response.
     * <p>
     * This method delegates the task of parsing user input to the Parser class.
     *
     * @param input The user input to be parsed.
     * @return The response string to be displayed to the user.
     */
    public String getResponse(String input) {
        try {
            return Parser.parseGeneralInput(this, input);
        } catch (MochiException e) {
            return e.getMessage();
        }
    }

}
