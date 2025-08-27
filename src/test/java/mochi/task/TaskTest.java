package mochi.task;

import mochi.exception.MochiException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void constructor_validDescription_createsTask() throws MochiException {
        Task task = new Task("Do homework");
        assertEquals("Do homework", task.toString().substring(4));
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void constructor_emptyDescription_throwsException() {
        assertThrows(MochiException.class, () -> {
            new Task("");
        });
        assertThrows(MochiException.class, () -> {
            new Task(" ");
        });
    }

    @Test
    public void mark_setsTaskAsCompleted() throws MochiException {
        Task task = new Task("test");
        task.mark();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void unmark_setsTaskAsNotCompleted() throws MochiException {
        Task task = new Task("test");
        task.unmark();
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_completedTask_success() throws MochiException {
        Task task = new Task("test");
        task.mark();
        assertEquals("X", task.getStatusIcon());
    }

    @Test
    public void getStatusIcon_uncompletedTask_success() throws MochiException {
        Task task = new Task("test");
        assertEquals(" ", task.getStatusIcon());
    }

    @Test
    public void toString_returnsCorrectFormat() throws MochiException {
        Task task = new Task("test");
        assertEquals("[ ] test", task.toString());

        task.mark();
        assertEquals("[X] test", task.toString());
    }
}
