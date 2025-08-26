import java.util.ArrayList;
import java.util.List;

public class TaskList {

    protected List<Task> tasks;
    protected int tasksCount;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.tasksCount = tasks.size();
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int getTasksCount() {
        return this.tasksCount;
    }

    public Task addTask(String input) throws MochiException {
        Task task = null;
        String taskType = input.split(" ",2)[0];
        String description = "";
        try {
            // If task is provided without description, splitting of string throws ArrayIndexOutofBoundsException
            description = input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MochiException("Task description cannot be empty!");
        }

        switch (taskType) {
            case "todo" -> {task = new ToDos(description);}
            case "deadline" -> {task = new Deadlines(description);}
            case "event" -> {task = new Event(description);}
        }

        this.tasks.add(task);
        this.tasksCount++;
        return task;
    }

    public Task addTask(String[] input) throws MochiException {
        Task task = null;

        switch (input[0]) {
        case "todo" -> {task = new ToDos(input);}
        case "deadline" -> {task = new Deadlines(input);}
        case "event" -> {task = new Event(input);}
        }

        this.tasks.add(task);
        this.tasksCount++;
        return task;

    }

    public Task deleteTask(int taskPosition) throws MochiException {
        if (taskPosition < 0 || taskPosition >= this.tasksCount) {
            throw new MochiException("Please input a valid task number");
        }

        Task task = this.tasks.get(taskPosition);
        this.tasks.remove(taskPosition);
        this.tasksCount--;
        return task;
    }

    public Task markTask(int taskPosition)  throws MochiException {
        if (taskPosition < 0 || taskPosition >= this.tasksCount) {
            throw new MochiException("Please input a valid task number!");
        }

        Task task = this.tasks.get(taskPosition);
        task.mark();
        return task;
    }

    public Task unmarkTask(int taskPosition) throws MochiException {
        if (taskPosition < 0 || taskPosition >= this.tasksCount) {
            throw new MochiException("Please input a valid task number!");
        }
        Task task = this.tasks.get(taskPosition);
        task.unmark();
        return task;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            result = result.concat(String.format("%d.%s\n", i + 1, this.tasks.get(i).toString()));
        }
        return result.trim();
    }
}
