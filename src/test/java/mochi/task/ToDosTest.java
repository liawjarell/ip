package mochi.task;

import mochi.exception.MochiException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ToDosTest {

    @Test
    public void constructor_validDescription_createsTodo() throws MochiException {
        String[] result = {"todo", "read book"};
        assertEquals("[T][ ] read book", new ToDos(result).toString());
        assertEquals("[T][X] read book", new ToDos("read book", true).toString());
    }

    @Test
    public void constructor_emptyDescription_throwsException() throws MochiException {
        assertThrows(MochiException.class, () -> {
            new ToDos("", false);
        });
    }

    @Test
    public void parseString_validString_returnsTodo() throws MochiException {
        String toParse = "1 | read book";
        ToDos temp = new ToDos("read book", true);
        assertEquals(temp.toString(), ToDos.parseString(toParse).toString());
    }

    @Test
    public void parseString_invalidString_throwsException() {
        String toParse = "1 | ";
        assertThrows(MochiException.class, () -> {
            ToDos.parseString(toParse);
        });
    }

    @Test
    public void toSaveString_returnsCorrectFormat() throws MochiException {
        String[] result = {"todo", "read book"};
        ToDos temp = new ToDos(result);
        assertEquals("T | 0 | read book", temp.toSaveString());

        temp.mark();
        assertEquals("T | 1 | read book", temp.toSaveString());
    }

    @Test
    public void toString_returnsCorrectFormat() throws MochiException{
        String[] result = {"todo", "read book"};
        ToDos todo = new ToDos(result);
        assertEquals("[T][ ] read book", todo.toString());

        todo.mark();
        assertEquals("[T][X] read book", todo.toString());
    }

}
