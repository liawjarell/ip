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

    // Creates the relevant tasks from parsed input, and adds it to the TaskList.
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
        Task task = this.tasks.get(taskPosition);
        this.tasks.remove(taskPosition);
        this.tasksCount--;
        return task;
    }

    public Task markTask(int taskPosition)  throws MochiException {
        Task task = this.tasks.get(taskPosition);
        task.mark();
        return task;
    }

    public Task unmarkTask(int taskPosition) throws MochiException {
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
