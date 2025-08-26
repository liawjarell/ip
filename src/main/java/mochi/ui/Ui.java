package mochi.ui;

import mochi.task.TaskList;

public class Ui {

    public Ui() {
        this.welcome();
    }

    public static String wrap(String message) {
        return String.format("""
                ________________________________________________________________________________
                %s
                ________________________________________________________________________________""",
                message).indent(4);
    }

    public void wrapPrint(String message) {
        System.out.println(wrap(message));
    }

    public void warn(String message) {
        this.wrapPrint(message);
    }

    public void welcome() {
        this.wrapPrint(Messages.MESSAGE_WELCOME);
    }

    public void goodbye() {
        this.wrapPrint(Messages.MESSAGE_GOODBYE);
    }

    public void notifyMarkTask(String taskString) {
        this.wrapPrint(String.format(Messages.MESSAGE_TASK_MARKED, taskString));
    }

    public void notifyUnmarkTask(String taskString) {
        this.wrapPrint(String.format(Messages.MESSAGE_TASK_UNMARKED, taskString));
    }

    public void notifyAddTask(String taskString, int numOfTasks) {
        this.wrapPrint(String.format(Messages.MESSAGE_TASK_ADDED, taskString, numOfTasks));
    }

    public void notifyDeleteTask(String taskString, int numOfTasks) {
        this.wrapPrint(String.format(Messages.MESSAGE_TASK_DELETED, taskString, numOfTasks));
    }

    public void showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            this.wrapPrint(Messages.MESSAGE_EMPTY_LIST);
        } else {
            this.wrapPrint(String.format(Messages.MESSAGE_LIST_PRINT, tasks.toString()));
        }
    }

    public static void main(String[] args) {
//        mochi.ui.Ui ui = new mochi.ui.Ui();
//        try {
//            mochi.task.TaskList testTasks = new mochi.task.TaskList(new ArrayList<>());
//
//            testTasks.addTask("todo read book");
//            ui.notifyAddTask(testTasks.getTasks().get(0).toString(), 1);
//
//            testTasks.markTask(0);
//            ui.notifyMarkTask(testTasks.getTasks().get(0).toString());
//
//            testTasks.unmarkTask(0);
//            ui.notifyUnmarkTask(testTasks.getTasks().get(0).toString());
//
//            testTasks.addTask("deadline return book /by today");
//            ui.notifyAddTask(testTasks.getTasks().get(1).toString(), 2);
//
//            ui.showTasks(testTasks);
//
//            mochi.task.Task temp = testTasks.getTasks().get(1);
//            testTasks.deleteTask(1);
//            ui.notifyDeleteTask(temp.toString(), 1);
//
//
//        } catch (mochi.exception.MochiException e) {
//            ui.warn(e.getMessage());
//        }
    }


}
