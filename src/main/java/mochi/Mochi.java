package mochi;

import mochi.storage.Storage;
import mochi.task.TaskList;
import mochi.parser.Parser;
import mochi.ui.Ui;
import mochi.exception.MochiException;
import mochi.task.Task;

import java.util.Scanner;

public class Mochi {

    // Constants
    private TaskList tasks;
    private Storage storage;
    private Ui ui;

    public Mochi(String filePath) {
        try {
            ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(this.storage.readTasks());
        } catch (MochiException e) {
            ui.warn(e.getMessage());
        }
    }

    public void run() {
        // Loop to get input
        getInput();

        exit();
    }

    public void exit() {
        ui.goodbye();
        System.exit(0);
    }

    public int getTasksCount() {
        return this.tasks.getTasksCount();
    }

    // Calls mochi.task.TaskList's printList()
    public void printList() {
        ui.showTasks(this.tasks);
    }

    // Calls mochi.task.TaskList's markTask()
    public void markTask(int taskPosition) throws MochiException {
        Task temp = this.tasks.markTask(taskPosition);
        ui.notifyMarkTask(temp.toString());
        this.saveTasks(this.tasks);
    }

    // Calls mochi.task.TaskList's unmarkTask()
    public void unmarkTask(int taskPosition) throws MochiException {
        Task temp = this.tasks.unmarkTask(taskPosition);
        ui.notifyUnmarkTask(temp.toString());
        this.saveTasks(this.tasks);
    }

    // Takes in array of strings, containing parsed information about a mochi.task.Task to be created
    public void addTask(String[] input) throws MochiException {
        Task temp = this.tasks.addTask(input);
        ui.notifyAddTask(temp.toString(), this.tasks.getTasksCount());
        this.saveTasks(this.tasks);
    }

    // Calls mochi.task.TaskList's deleteTask()
    public void deleteTask(int taskPosition) throws MochiException {
        Task temp = this.tasks.deleteTask(taskPosition);
        ui.notifyDeleteTask(temp.toString(), this.tasks.getTasksCount());
        this.saveTasks(this.tasks);
    }

    // Calls mochi.storage.Storage's saveTasks()
    public void saveTasks(TaskList tasks) throws MochiException {
        this.storage.saveTasks(this.tasks.getTasks());
    }

    // Loop to getInput
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

    public static void main(String[] args) {
//        new mochi.Mochi().run();
        new Mochi("data/tasks.txt").run();
    }
}
