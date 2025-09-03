package mochi.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import mochi.exception.MochiException;

public class ToDoTest {

    @Test
    public void constructor_validDescription_createsTodo() throws MochiException {
        String[] result = {"todo", "read book"};
        assertEquals("[T][ ] read book", new ToDo(result).toString());
        assertEquals("[T][X] read book", new ToDo("read book", true).toString());
    }

    @Test
    public void constructor_emptyDescription_throwsException() throws MochiException {
        assertThrows(MochiException.class, () -> {
            new ToDo("", false);
        });
    }

    @Test
    public void parseString_validString_returnsTodo() throws MochiException {
        String toParse = "1 | read book";
        ToDo temp = new ToDo("read book", true);
        assertEquals(temp.toString(), ToDo.parseString(toParse).toString());
    }

    @Test
    public void parseString_invalidString_throwsException() {
        String toParse = "1 | ";
        assertThrows(MochiException.class, () -> {
            ToDo.parseString(toParse);
        });
    }

    @Test
    public void toSaveString_returnsCorrectFormat() throws MochiException {
        String[] result = {"todo", "read book"};
        ToDo temp = new ToDo(result);
        assertEquals("T | 0 | read book", temp.toSaveString());

        temp.mark();
        assertEquals("T | 1 | read book", temp.toSaveString());
    }

    @Test
    public void toString_returnsCorrectFormat() throws MochiException {
        String[] result = {"todo", "read book"};
        ToDo todo = new ToDo(result);
        assertEquals("[T][ ] read book", todo.toString());

        todo.mark();
        assertEquals("[T][X] read book", todo.toString());
    }

}
