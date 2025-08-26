package mochi.parser;

import mochi.Mochi;
import mochi.exception.MochiException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    // Parses first input to determine command
    public static void parseGeneralInput(Mochi mochi, String input) throws MochiException {
        if (input.equals("bye")) {
            mochi.exit();
        } else if (input.equals("list")) {
            mochi.printList();
        } else {
            String command = input.split(" ", 2)[0];

            switch (command) {
            case "mark", "unmark", "delete":
                parseNumberedAction(mochi, input, command);
                break;
            case "todo", "deadline", "event":
                parseAddTask(mochi, input, command);
                break;
            default:
                throw new MochiException("Oops! I'm sorry but I don't know what that means. Try again!");
            }
        }
    }

    // Checks if task description is empty
    public static void parseAddTask(Mochi mochi, String input, String command) throws MochiException {
        String description = "";
        try {
            // If task provided without description, splitting of string throws ArrayIndexOutfBoundsException
            description = input.split(" ",2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MochiException("mochi.task.Task description cannot be empty!");
        }

        switch (command) {
            case "todo" -> parseTodo(mochi, command, description);
            case "deadline" -> parseDeadline(mochi, command, description);
            case "event" -> parseEvent(mochi, command, description);

        }
    }

    public static void parseTodo(Mochi mochi, String command, String description) throws MochiException {
        String[] results = {command, description};
        mochi.addTask(results);
    }

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

    // Parsing for mark, unmark, delete commands
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

    // Parses a given string into a LocalDateTime object. For use in mochi.task.Deadlines.
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
                dateTime = date.atStartOfDay();
            } else {
                dateTime = date.atTime(LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm")));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            dateTime = date.atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new MochiException("Please input a proper date and time in the format [yyyy/MM/dd HHmm]");
        }

        return dateTime;

    }
}
