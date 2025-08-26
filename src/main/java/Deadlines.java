import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task {
    protected LocalDateTime by;

    public Deadlines(String description) throws MochiException {
        // Split at /by to get title of task.
        super(description.split("\\s*/by\\s*", 2)[0]);

        int byIndex = description.indexOf("/by");
        // Checks if /by exists
        if (byIndex == -1) {
            throw new MochiException("Please specify a deadline using /by");
        }

        // First part title, second part deadline
        String[] parts = description.split("\\s*/by\\s*", 2);

        if (parts[1].isEmpty()) {
            throw new MochiException("Please specify a date/time after /by");

        }

        this.by = Parser.stringToLocalDateTime(parts[1]);
    }

    public Deadlines(String description, boolean isCompleted, String by) throws MochiException{
        super(description);
        this.isCompleted = isCompleted;
//        this.by = Parser.stringToLocalDateTime(by);
        this.by = LocalDateTime.parse(by);
    }


    public static Deadlines parseString(String toParse) throws MochiException{
        String[] result = toParse.strip().split(" \\| ", 3);

        return new Deadlines(result[1], result[0].equals("1"), result[2]);
    }

    @Override
    public String toSaveString() {
        return String.format("D | %d | %s | %s",
                this.isCompleted ? 1 : 0, this.description, this.by.toString());
    }

    @Override
    public String toString() {
        DateTimeFormatter byFormatter = DateTimeFormatter.ofPattern("MMM/dd/yyyy HHmm");
        return "[D]" + super.toString() + " (by: " + by.format(byFormatter) + ")";
    }
}
