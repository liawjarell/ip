package mochi.task;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import mochi.exception.MochiException;

public class TaskListTest {
    /**
     * Tests for the addTask method of the TaskList class.
     * <p>
     * The addTask method is responsible for adding a new task to the list. It takes
     * an array of strings as input to determine the type of task (ToDo, Deadlines, or Event)
     * and throws an exception or assertion error for invalid input.
     */

    @Test
    public void addTask_addToDo_success() throws Exception {
        // Initialize TaskList
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);

        // Input
        String[] input = {"todo", "Read a book"};

        // Act
        Task addedTask = taskList.addTask(input);

        // Assert
        assertNotNull(addedTask);
        assertEquals("Read a book", addedTask.description);
        assertEquals(1, taskList.getTasksCount());
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void addTask_addDeadline_success() throws Exception {
        // Initialize TaskList
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);

        // Input
        String[] input = {"deadline", "Submit assignment", "2025-09-20"};

        // Act
        Task addedTask = taskList.addTask(input);

        // Assert
        assertNotNull(addedTask);
        assertEquals("Submit assignment", addedTask.description);
        assertEquals(1, taskList.getTasksCount());
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void addTask_addEvent_success() throws Exception {
        // Initialize TaskList
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);

        // Input
        String[] input = {"event", "Attend conference", "2025-09-18", "2025-09-20"};

        // Act
        Task addedTask = taskList.addTask(input);

        // Assert
        assertNotNull(addedTask);
        assertEquals("Attend conference", addedTask.description);
        assertEquals(1, taskList.getTasksCount());
        assertFalse(taskList.isEmpty());
    }

    @Test
    public void addTask_invalidTaskType_throwsAssertionError() {
        // Initialize TaskList
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);

        // Input
        String[] input = {"invalid", "Some description"};

        // Act & Assert
        assertThrows(AssertionError.class, () -> taskList.addTask(input));
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void addTask_emptyInput_throwsMochiException() {
        // Initialize TaskList
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);

        // Input
        String[] input = {"todo", ""};

        // Act & Assert
        assertThrows(MochiException.class, () -> taskList.addTask(input));
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void deleteTask_validIndex_success() throws Exception {
        // Initialize TaskList with one task
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        String[] input = {"todo", "Sample Task"};
        taskList.addTask(input);

        // Act
        Task deletedTask = taskList.deleteTask(0);

        // Assert
        assertNotNull(deletedTask);
        assertEquals("Sample Task", deletedTask.description);
        assertTrue(taskList.isEmpty());
        assertEquals(0, taskList.getTasksCount());
    }

    @Test
    public void deleteTask_invalidIndex_throwsIndexOutOfBoundsException() {
        // Initialize TaskList with one task
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        String[] input = {"todo", "Sample Task"};
        try {
            taskList.addTask(input);
        } catch (MochiException e) {
            e.printStackTrace();
        }

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(5));
    }

    @Test
    public void deleteTask_emptyTaskList_throwsIndexOutOfBoundsException() {
        // Initialize TaskList with no tasks
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.deleteTask(0));
    }

    @Test
    public void markTask_validIndex_success() throws Exception {
        // Initialize TaskList with one task
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        String[] input = {"todo", "Sample Task"};
        taskList.addTask(input);

        // Act
        Task markedTask = taskList.markTask(0);

        // Assert
        assertNotNull(markedTask);
        assertTrue(markedTask.isCompleted);
        assertEquals("Sample Task", markedTask.description);
    }

    @Test
    public void markTask_invalidIndex_throwsIndexOutOfBoundsException() {
        // Initialize TaskList with one task
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        String[] input = {"todo", "Sample Task"};
        try {
            taskList.addTask(input);
        } catch (MochiException e) {
            e.printStackTrace();
        }

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markTask(5));
    }

    @Test
    public void markTask_emptyTaskList_throwsIndexOutOfBoundsException() {
        // Initialize TaskList with no tasks
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.markTask(0));
    }

    @Test
    public void unmarkTask_validIndex_success() throws Exception {
        // Initialize TaskList with one marked task
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        String[] input = {"todo", "Sample Task"};
        Task addedTask = taskList.addTask(input);
        taskList.markTask(0); // Mark the task

        // Act
        Task unmarkedTask = taskList.unmarkTask(0);

        // Assert
        assertNotNull(unmarkedTask);
        assertFalse(unmarkedTask.isCompleted);
        assertEquals("Sample Task", unmarkedTask.description);
    }

    @Test
    public void unmarkTask_invalidIndex_throwsIndexOutOfBoundsException() {
        // Initialize TaskList with one marked task
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        String[] input = {"todo", "Sample Task"};
        try {
            taskList.addTask(input);
            taskList.markTask(0); // Mark the task
        } catch (MochiException e) {
            e.printStackTrace();
        }

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.unmarkTask(5));
    }

    @Test
    public void unmarkTask_emptyTaskList_throwsIndexOutOfBoundsException() {
        // Initialize TaskList with no tasks
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.unmarkTask(0));
    }

    @Test
    public void tagTask_validTask_success() throws Exception {
        // Initialize TaskList with one task
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        String[] input = {"todo", "Sample Task"};
        Task addedTask = taskList.addTask(input);

        // Act
        Task taggedTask = taskList.tagTask(0, "urgent");

        // Assert
        assertNotNull(taggedTask);
        assertEquals("urgent", taggedTask.tag);
        assertEquals("Sample Task", taggedTask.description);
    }

    @Test
    public void tagTask_invalidIndex_throwsIndexOutOfBoundsException() {
        // Initialize TaskList with one task
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        String[] input = {"todo", "Sample Task"};
        try {
            taskList.addTask(input);
        } catch (MochiException e) {
            e.printStackTrace();
        }

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.tagTask(5, "#important"));
    }

    @Test
    public void tagTask_emptyTaskList_throwsIndexOutOfBoundsException() {
        // Initialize TaskList with no tasks
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.tagTask(0, "#important"));
    }

    @Test
    public void untagTask_validTask_success() throws Exception {
        // Initialize TaskList with one tagged task
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        String[] input = {"todo", "Sample Task"};
        Task addedTask = taskList.addTask(input);
        taskList.tagTask(0, "urgent"); // Tag the task

        // Act
        Task untaggedTask = taskList.untagTask(0);

        // Assert
        assertNotNull(untaggedTask);
        assertEquals("", untaggedTask.getTag());
        assertEquals("Sample Task", untaggedTask.description);
    }

    @Test
    public void untagTask_invalidIndex_throwsIndexOutOfBoundsException() {
        // Initialize TaskList with one task
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        String[] input = {"todo", "Sample Task"};
        try {
            taskList.addTask(input);
        } catch (MochiException e) {
            e.printStackTrace();
        }

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.untagTask(5));
    }

    @Test
    public void untagTask_emptyTaskList_throwsIndexOutOfBoundsException() {
        // Initialize TaskList with no tasks
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);

        // Act & Assert
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.untagTask(0));
    }

    @Test
    public void find_noMatches_returnsEmptyList() throws Exception {
        // Initialize TaskList
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        taskList.addTask(new String[]{"todo", "Read a book"});
        taskList.addTask(new String[]{"todo", "Write a report"});

        // Act
        TaskList result = taskList.find("exercise");

        // Assert
        assertTrue(result.isEmpty());
        assertEquals(0, result.getTasksCount());
    }

    @Test
    public void find_singleMatch_returnsSingleTask() throws Exception {
        // Initialize TaskList
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        taskList.addTask(new String[]{"todo", "Read a book"});
        taskList.addTask(new String[]{"todo", "Write a report"});

        // Act
        TaskList result = taskList.find("Read");

        // Assert
        assertEquals(1, result.getTasksCount());
        assertEquals("Read a book", result.getTasks().get(0).description);
    }

    @Test
    public void find_multipleMatches_returnsAllMatchingTasks() throws Exception {
        // Initialize TaskList
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        taskList.addTask(new String[]{"todo", "Read a book"});
        taskList.addTask(new String[]{"todo", "Write a report"});
        taskList.addTask(new String[]{"deadline", "Submit reading report", "2025-09-20"});

        // Act
        TaskList result = taskList.find("report");

        // Assert
        assertEquals(2, result.getTasksCount());
        String[] descriptions = result.getTasks().stream().map(task -> task.description).toArray(String[]::new);
        assertArrayEquals(new String[]{"Write a report", "Submit reading report"}, descriptions);
    }

    @Test
    public void find_emptyTaskList_returnsEmptyList() {
        // Initialize empty TaskList
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);

        // Act
        TaskList result = taskList.find("keyword");

        // Assert
        assertTrue(result.isEmpty());
        assertEquals(0, result.getTasksCount());
    }

    @Test
    public void toString_emptyTaskList_returnsEmptyString() {
        // Initialize an empty TaskList
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);

        // Act
        String result = taskList.toString();

        // Assert
        assertEquals("", result);
    }

    @Test
    public void toString_singleTask_returnsFormattedString() throws Exception {
        // Initialize TaskList with one task
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        taskList.addTask(new String[]{"todo", "Read a book"});

        // Act
        String result = taskList.toString();

        // Assert
        assertEquals("1.[T][ ] Read a book", result);
    }

    @Test
    public void toString_multipleTasks_returnsFormattedString() throws Exception {
        // Initialize TaskList with multiple tasks
        ArrayList<Task> taskArrayList = new ArrayList<>();
        TaskList taskList = new TaskList(taskArrayList);
        taskList.addTask(new String[]{"todo", "Read a book"});
        taskList.addTask(new String[]{"deadline", "Submit assignment", "2025-09-20"});
        taskList.addTask(new String[]{"event", "Attend meeting", "2025-09-18", "2025-09-20"});

        // Act
        String result = taskList.toString();

        // Assert
        String expected =
                "1.[T][ ] Read a book\n"
                       + "2.[D][ ] Submit assignment (by: Sep 20 2025 2359)\n"
                       + "3.[E][ ] Attend meeting (from: 2025-09-18 to: 2025-09-20)";
        assertEquals(expected, result);
    }
}
