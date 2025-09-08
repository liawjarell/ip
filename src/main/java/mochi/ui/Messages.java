package mochi.ui;

/**
 * Messages class for storing messages to be displayed to the user.
 * The class is meant to be static and is not meant to be instantiated.
 */
public class Messages {

    public static final String MESSAGE_WELCOME = "Hello! I'm MOCHI!\nWhat can I do for you?";

    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again!";

    public static final String MESSAGE_TASK_ADDED =
            "Got it. I've added this task:\n    %s\nNow you have %d tasks in the list";

    public static final String MESSAGE_TASK_DELETED =
            "Noted. I've removed this task:\n    %s\nNow you have %d tasks in the list.";

    public static final String MESSAGE_TASK_MARKED =
            "Nice! I've marked this task as done:\n   %s";

    public static final String MESSAGE_TASK_UNMARKED =
            "Ok, I've marked this task as not done yet:\n %s";

    public static final String MESSAGE_EMPTY_LIST =
            "You have no existing tasks!";

    public static final String MESSAGE_LIST_PRINT =
            "Here are the tasks in your list:\n%s";

    public static final String MESSAGE_TAGGED =
            "Nice! I've tagged this task:\n    %s";
}
