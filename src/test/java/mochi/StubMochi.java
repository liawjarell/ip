package mochi;

import java.util.ArrayList;
import java.util.List;

import mochi.task.TaskList;

/**
 * StubMochi class for testing.
 */
public class StubMochi extends Mochi {

    private boolean exitRan = false;
    private boolean printListRan = false;
    private boolean markTaskRan = false;
    private boolean unmarkTaskRan = false;
    private boolean addTaskRan = false;
    private boolean deleteTaskRan = false;
    private boolean saveTasksRan = false;

    // Simple in-memory representation of tasks for assertions in tests
    private final List<String> stubTasks = new ArrayList<>();

    /**
     * Creates a new instance of the StubMochi class.
     */
    public StubMochi() {
        // Provide a dummy path; the real Mochi constructor handles its own exceptions/logging.
        super("data/test-tasks.txt");
    }

    @Override
    public void exit() {
        this.exitRan = true;
    }

    @Override
    public void printList() {
        this.printListRan = true;
    }

    @Override
    public void markTask(int taskPosition) {
        this.markTaskRan = true;
    }

    @Override
    public void unmarkTask(int taskPosition) {
        this.unmarkTaskRan = true;
    }

    @Override
    public void addTask(String[] input) {
        this.addTaskRan = true;
    }

    @Override
    public void deleteTask(int taskPosition) {
        this.deleteTaskRan = true;
    }

    @Override
    public void saveTasks(TaskList tasks) {
        this.saveTasksRan = true;
    }

    // Test helper methods used by ParserTest

    public boolean listDisplayed() {
        return printListRan;
    }

    public boolean isExited() {
        return exitRan;
    }

    public boolean markCalled() {
        return markTaskRan;
    }

    public boolean unmarkCalled() {
        return unmarkTaskRan;
    }

    public boolean addCalled() {
        return addTaskRan;
    }

    public boolean deleteCalled() {
        return deleteTaskRan;
    }

    /**
     * Seeds the task list with a specified number of tasks.
     * @param count The number of tasks to be seeded.
     */
    public void seedTasks(int count) {
        for (int i = 0; i < count; i++) {
            stubTasks.add("dummy " + (i + 1));
        }
    }

    @Override
    public int getTasksCount() {
        return stubTasks.size();
    }

}
