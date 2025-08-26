import java.util.ArrayList;

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

    public static void wrapPrint(String message) {
        System.out.println(wrap(message));
    }

    public void welcome() {
        wrapPrint(Messages.MESSAGE_WELCOME);
    }

    public void goodbye() {
        System.out.println(wrap(Messages.MESSAGE_GOODBYE));
    }

    public void notifyMarkTask(String taskString) {
        wrapPrint(String.format(Messages.MESSAGE_TASK_MARKED, taskString));
    }

    public void notifyUnmarkTask(String taskString) {
        wrapPrint(String.format(Messages.MESSAGE_TASK_UNMARKED, taskString));
    }

    public void notifyAddTask(String taskString, int numOfTasks) {
        wrapPrint(String.format(Messages.MESSAGE_TASK_ADDED, taskString, numOfTasks));
    }

    public void notifyDeleteTask(String taskString, int numOfTasks) {
        wrapPrint(String.format(Messages.MESSAGE_TASK_DELETED, taskString, numOfTasks));
    }

    public void showTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            wrapPrint(Messages.MESSAGE_EMPTY_LIST);
        } else {
            wrapPrint(String.format(Messages.MESSAGE_LIST_PRINT, tasks.toString()));
        }
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        try {
            TaskList testTasks = new TaskList(new ArrayList<>());

            testTasks.addTask("todo read book");
            ui.notifyAddTask(testTasks.getTasks().get(0).toString(), 1);

            testTasks.markTask(0);
            ui.notifyMarkTask(testTasks.getTasks().get(0).toString());

            testTasks.unmarkTask(0);
            ui.notifyUnmarkTask(testTasks.getTasks().get(0).toString());

            testTasks.addTask("deadline return book /by today");
            ui.notifyAddTask(testTasks.getTasks().get(1).toString(), 2);

            testTasks.printList();
            ui.showTasks(testTasks);

            Task temp = testTasks.getTasks().get(1);
            testTasks.deleteTask(1);
            ui.notifyDeleteTask(temp.toString(), 1);


        } catch (MochiException e) {
            wrapPrint(e.getMessage());
        }
    }


}
