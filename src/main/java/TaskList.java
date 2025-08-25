import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;
    private int tasksCount;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.tasksCount = tasks.size();
    }

    public void addTask(String input) throws MochiException {
        String taskType = input.split(" ",2)[0];
        String description = "";
        try {
            // If task is provided without description, splitting of string throws ArrayIndexOutofBoundsException
            description = input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MochiException("Task description cannot be empty");
        }
        try {
            // If task is provided without description, splitting of string throws ArrayIndexOutofBoundsException
            description = input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MochiException("Task description cannot be empty!");
        }

        switch (taskType) {
            case "todo" -> this.tasks.add(new ToDos(description));
            case "deadline" -> this.tasks.add(new Deadlines(description));
            case "event" -> this.tasks.add(new Event(description));
        }

        String toPrint = WrapMessage.wrap(String.format(
                "Got it. I've added this task:\n    %s\nNow you have %d tasks in the list",
                this.tasks.get(tasksCount).toString(),
                this.tasksCount + 1));
        System.out.println(toPrint);
        this.tasksCount++;
    }

    public void deleteTask(int taskPosition) throws MochiException {
        if (taskPosition < 0 || taskPosition >= this.tasksCount) {
            throw new MochiException("Please input a valid task number");
        }
        String toPrint = WrapMessage.wrap(String.format(
                "Noted. I've removed this task:\n    %s\nNow you have %d tasks in the list.",
                this.tasks.get(taskPosition).toString(),
                this.tasksCount - 1
        ));
        System.out.println(toPrint);
        this.tasks.remove(taskPosition);
        this.tasksCount--;
    }

    public void markTask(int taskPosition)  throws MochiException {
        if (taskPosition < 0 || taskPosition >= this.tasksCount) {
            throw new MochiException("Please input a valid task number!");
        }
        this.tasks.get(taskPosition).mark();
        String toPrint = WrapMessage.wrap(
                String.format("Nice! I've marked this task as done:\n   %s",
                        this.tasks.get(taskPosition).toString()));
        System.out.println(toPrint);
    }

    public void unmarkTask(int taskPosition) throws MochiException {
        if (taskPosition < 0 || taskPosition >= this.tasksCount) {
            throw new MochiException("Please input a valid task number!");
        }
        this.tasks.get(taskPosition).unmark();
        String toPrint = WrapMessage.wrap(
                String.format("Ok, I've marked this task as not done yet:\n %s",
                        this.tasks.get(taskPosition).toString()));
        System.out.println(toPrint);
    }

    // Function to print out all contents in the Task array
    public void printList() {
        String toPrint = "Here are the tasks in your list:";
        for (int i = 0; i < this.tasks.size(); i++) {
            toPrint = toPrint.concat(String.format("\n%d.%s", i + 1, this.tasks.get(i).toString()));
        }
        // Wrap final message and print
        System.out.println(WrapMessage.wrap(toPrint));
    }
}
