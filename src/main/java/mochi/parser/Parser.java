package mochi.parser;

import mochi.Mochi;
import mochi.exception.MochiException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class for parsing user input.
 * This class delegates the task of parsing user input to the appropriate methods.
 * It also handles exceptions and provides feedback to the user in case of errors.
 */
public class Parser {

    /**
     * Parses user input and invokes the appropriate parsing, or Mochi methods to handle the input.
     *
     * @param mochi The Mochi instance to be used for parsing and invoking actions.
     * @param input The user input to be parsed.
     * @throws MochiException If any error occurs while parsing the input or invoking the appropriate action.
     */
    public static void parseGeneralInput(Mochi mochi, String input) throws MochiException {
        if (input.equals("bye")) {
            mochi.exit();
        } else if (input.equals("list")) {
            mochi.printList();
        } else {
            // Takes care of white spaces before description
            String command = input.split("\s+", 2)[0];

            switch (command) {
            case "mark", "unmark", "delete":
                parseNumberedAction(mochi, input, command);
                break;
            case "todo", "deadline", "event":
                parseAddTask(mochi, input, command);
                break;
            case "find":
                System.out.println(input);
                parseFindTask(mochi, input, command);
                break;
            default:
                throw new MochiException("Oops! I'm sorry but I don't know what that means. Try again!");
            }
        }
    }

    /**
     * Parses user input for adding tasks.
     * This method delegates the task of parsing user input to the appropriate parsing methods.
     * It also handles exceptions and provides feedback to the user in case of errors.
     *
     * @param mochi The Mochi instance to be used for parsing and invoking actions.
     * @param input The user input to be parsed.
     * @param command The command indicating the type of task to be added.
     * @throws MochiException If any error occurs while parsing the input or invoking the appropriate action.
     */
    public static void parseAddTask(Mochi mochi, String input, String command) throws MochiException {
        String description = "";
        try {
            // If task provided without description, splitting of string throws ArrayIndexOutfBoundsException
            description = input.split(" ",2)[1].strip();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MochiException("Task description cannot be empty!");
        }

        switch (command) {
            case "todo" -> parseTodo(mochi, command, description);
            case "deadline" -> parseDeadline(mochi, command, description);
            case "event" -> parseEvent(mochi, command, description);
        }
    }

    /**
     * Parses the "todo" command and description provided by the user, and adds the task to the Mochi instance.
     *
     * @param mochi The Mochi instance to which the task will be added.
     * @param command The command string representing the type of task ("todo" in this case).
     * @param description The description of the task to be added.
     * @throws MochiException If an error occurs while adding the task.
     */
    public static void parseTodo(Mochi mochi, String command, String description) throws MochiException {
        String[] results = {command, description};
        mochi.addTask(results);
    }

    /**
     * Parses the "deadline" command and description provided by the user, and adds the task to the Mochi instance.
     *
     * @param mochi The Mochi instance to which the task will be added.
     * @param command The command string representing the type of task ("deadline" in this case).
     * @param input The description of the task to be added.
     * @throws MochiException If an error occurs while adding the task.
     */
    public static void parseDeadline(Mochi mochi, String command, String input) throws MochiException {
        int byIndex = input.indexOf("/by");
        // Check if "/by" exists
        if (byIndex == -1) {
            throw new MochiException("Please specify a deadline using /by");
        }

        // Second part title, Third part deadline
        String[] results = {
                command,
                input.split("\\s*/by\\s*", 2)[0],
                input.split("\\s*/by\\s*", 2)[1]};

        if (results[2].isEmpty()) {
            throw new MochiException("Please specify a date/time after /by");
        }

        mochi.addTask(results);
    }

    /**
     * Parses the "event" command and description provided by the user, and adds the task to the Mochi instance.
     *
     * @param mochi The Mochi instance to which the task will be added.
     * @param command The command string representing the type of task ("event" in this case).
     * @param input The description of the task to be added.
     * @throws MochiException If an error occurs while adding the task.
     */
    public static void parseEvent(Mochi mochi, String command, String input) throws MochiException {

        // Checks if /from and /to exists
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1) {
            throw new MochiException("Please specify a start date or time using /from");
        }
        if (toIndex == -1) {
            throw new MochiException("Please specify a end date or time using /to");
        }
        if (toIndex < fromIndex) {
            throw new MochiException("/from must come before /to");
        }

        // First part description, second part duration
        String[] parts = input.split("\\s*/from\\s*");

        // First part is from, second part is to
        String[] duration = parts[1].split("\\s*/to\\s*");

        String from = duration[0].trim();
        String to = duration[1].trim();
        if (from.isEmpty()) {
            throw new MochiException("Please provide a start date or time after /from");
        }
        if (to.isEmpty()) {
            throw new MochiException("Please provide an end date or time after /to");
        }

        String[] result = {command, parts[0], from, to};
        mochi.addTask(result);
    }

    /**
     * Parses user input for marking, unmarking or deleting tasks.
     * This method checks the validity of the task number provided by the user, and
     * delegates the task of parsing user input to the appropriate parsing methods.
     *
     * @param mochi The Mochi instance to be used for parsing and invoking actions.
     * @param input The task number provided by the user.
     * @param command The command indicating the type of action to be performed.
     * @throws MochiException If any error occurs while parsing the input or invoking the appropriate action.
     */
    public static void parseNumberedAction(Mochi mochi, String input, String command) throws MochiException {
        int taskPosition = 0;
        try {
            taskPosition = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskPosition < 0 || taskPosition >= mochi.getTasksCount()) {
                throw new MochiException("Please input a valid task number!");
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new MochiException("Please input the task number!");
        }

        switch (command) {
        case "mark":
            mochi.markTask(taskPosition);
            break;
        case "unmark":
            mochi.unmarkTask(taskPosition);
            break;
        case "delete":
            mochi.deleteTask(taskPosition);
            break;
        }
    }

    /**
     * Parses the "find" command and retrieves tasks from the Mochi instance that match the provided keyword.
     *
     * @param mochi The Mochi instance used for managing and searching tasks.
     * @param input The full input string provided by the user, which includes the command and keyword.
     * @param command The command string, which in this case is expected to be "find".
     * @throws MochiException If the input does not contain a keyword after the command.
     */
    public static void parseFindTask(Mochi mochi, String input, String command) throws MochiException {
        String keyword = "";
        try {
            keyword = input.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MochiException("Please input a keyword after find!");
        }
        keyword = keyword.trim();
        mochi.find(keyword);
    }


    /**
     * Converts a date and time string into a {@code LocalDateTime} object.
     * The method supports multiple date formats such as "yyyy/MM/dd" or "yyyy-MM-dd",
     * and also allows for special keywords like "today" or "tomorrow".
     * If the time part is missing, the default time is set to 23:59 (end of the day).
     *
     * @param dateTimeString The input string containing a date and time to be converted.
     *                       Accepted formats include "yyyy/MM/dd HHmm", "yyyy-MM-dd HHmm",
     *                       "today HHmm", or "tomorrow HHmm".
     * @return A {@code LocalDateTime} object representing the date and time specified in the input string.
     * @throws MochiException If the input string is not in a recognized format or invalid.
     */
    public static LocalDateTime stringToLocalDateTime(String dateTimeString) throws MochiException {
        LocalDateTime dateTime;
        LocalDate date;
        String[] dateTimeParts = dateTimeString.split(" ", 2);
        String dateString = dateTimeParts[0];
        // Parse date first, according to two different formats
        try {
            if (dateString.contains("/")) {
                date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
            } else if (dateString.contains("-")) {
                date = LocalDate.parse(dateString);
            } else if (dateString.equals("tomorrow")) {
                date = LocalDate.now().plusDays(1);
            } else if (dateString.equals("today")) {
                date = LocalDate.now();
            } else {
                throw new MochiException("Please input a proper date and time in the format [yyyy/MM/dd HHmm]");
            }
        } catch (DateTimeParseException e) {
            // Prompt for new input
            throw new MochiException("Please input a proper date and time in the format [yyyy/MM/dd HHmm]");
        }
        // Parse time
        try {
            // If timeString does not exist, ArrayIndexOutOfBoundsException will be thrown.
            String timeString = dateTimeParts[1];
            if (timeString.isEmpty()) {
                dateTime = date.atTime(23,59);
            } else {
                dateTime = date.atTime(LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm")));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            dateTime = date.atTime(23,59);
        } catch (DateTimeParseException e) {
            throw new MochiException("Please input a proper date and time in the format [yyyy/MM/dd HHmm]");
        }

        return dateTime;

    }
}
