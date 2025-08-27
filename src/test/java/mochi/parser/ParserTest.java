package mochi.parser;

import mochi.StubMochi;
import mochi.exception.MochiException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parseGeneralInput_mark_routesToNumberedAction() throws MochiException {
        StubMochi mochi = new StubMochi();
        mochi.seedTasks(2); // ensure index 0/1 is valid

        Parser.parseGeneralInput(mochi, "mark 1");

        assertTrue(mochi.markCalled(), "Expected markTask to be called");
    }

    @Test
    public void parseGeneralInput_unmark_routesToNumberedAction() throws MochiException {
        StubMochi mochi = new StubMochi();
        mochi.seedTasks(2);

        Parser.parseGeneralInput(mochi, "unmark 2");

        assertTrue(mochi.unmarkCalled(), "Expected unmarkTask to be called");
    }

    @Test
    public void parseGeneralInput_delete_routesToNumberedAction() throws MochiException {
        StubMochi mochi = new StubMochi();
        mochi.seedTasks(3);

        Parser.parseGeneralInput(mochi, "delete 3");

        assertTrue(mochi.deleteCalled(), "Expected deleteTask to be called");
    }

    @Test
    public void parseGeneralInput_numberedTask_invalidIndex_exceptionThrown() {
        StubMochi mochi = new StubMochi();
        mochi.seedTasks(2);

        assertThrows(MochiException.class, () -> Parser.parseGeneralInput(mochi, "mark 3"));
        assertThrows(MochiException.class, () -> Parser.parseGeneralInput(mochi, "unmark 3"));
        assertThrows(MochiException.class, () -> Parser.parseGeneralInput(mochi, "delete 3"));
        assertThrows(MochiException.class, () -> Parser.parseGeneralInput(mochi, "mark 0"));
    }

    @Test
    public void parseGeneralInput_todo_routesToAddTask() throws MochiException {
        StubMochi mochi = new StubMochi();

        Parser.parseGeneralInput(mochi, "todo Learn Java");

        assertTrue(mochi.addCalled(), "Expected addTask to be called");
    }

    @Test
    public void parseGeneralInput_deadline_routesToAddTask() throws MochiException {
        StubMochi mochi = new StubMochi();

        Parser.parseGeneralInput(mochi, "deadline Submit /by 2025-08-28");

        assertTrue(mochi.addCalled(), "Expected addTask to be called");
    }

    @Test
    public void parseGeneralInput_event_routesToAddTask() throws MochiException {
        StubMochi mochi = new StubMochi();

        Parser.parseGeneralInput(mochi, "event Workshop /from 2025-08-29 /to 2025-08-30");

        assertTrue(mochi.addCalled(), "Expected addTask to be called");
    }

    @Test
    public void parseGeneralInput_listCommand_listDisplayed() throws MochiException {
        StubMochi mochi = new StubMochi();
        Parser.parseGeneralInput(mochi, "list");
        assertTrue(mochi.listDisplayed());
    }

    @Test
    public void parseGeneralInput_byeCommand_exitsMochi() throws MochiException {
        StubMochi mochi = new StubMochi();
        Parser.parseGeneralInput(mochi, "bye");
        assertTrue(mochi.isExited());
    }

    @Test
    public void parseGeneralInput_invalidCommand_exceptionThrown() {
        StubMochi mochi = new StubMochi();
        assertThrows(MochiException.class, () -> Parser.parseGeneralInput(mochi, "invalidCommand"));
    }

    @Test
    public void parseGeneralInput_incompleteMarkInput_exceptionThrown() {
        StubMochi mochi = new StubMochi();
        assertThrows(MochiException.class, () -> Parser.parseGeneralInput(mochi, "mark"));
    }
}
