package mochi;

import java.util.ArrayList;
import java.util.List;

import mochi.task.TaskList;

/**
 * StubMochi class for testing.
 */
public class StubMochi extends Mochi {

    private boolean isExitRan = false;
    private boolean isPrintListRan = false;
    private boolean isMarkTaskRan = false;
    private boolean isUnmarkTaskRan = false;
    private boolean isAddTaskRan = false;
    private boolean isDeleteTaskRan = false;
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
    public String exit() {
        this.isExitRan = true;
        return "";
    }

    @Override
    public String printList() {
        this.isPrintListRan = true;
        return "";
    }

    @Override
    public String markTask(int taskPosition) {
        this.isMarkTaskRan = true;
        return "";
    }

    @Override
    public String unmarkTask(int taskPosition) {
        this.isUnmarkTaskRan = true;
        return "";
    }

    @Override
    public String addTask(String[] input) {
        this.isAddTaskRan = true;
        return "";
    }

    @Override
    public String deleteTask(int taskPosition) {
        this.isDeleteTaskRan = true;
        return "";
    }

    @Override
    public void saveTasks(TaskList tasks) {
        this.saveTasksRan = true;
    }

    // Test helper methods used by ParserTest

    public boolean listDisplayed() {
        return isPrintListRan;
    }

    public boolean isExited() {
        return isExitRan;
    }

    public boolean markCalled() {
        return isMarkTaskRan;
    }

    public boolean unmarkCalled() {
        return isUnmarkTaskRan;
    }

    public boolean addCalled() {
        return isAddTaskRan;
    }

    public boolean deleteCalled() {
        return isDeleteTaskRan;
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
