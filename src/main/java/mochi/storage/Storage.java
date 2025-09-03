package mochi.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mochi.exception.MochiException;
import mochi.task.Deadlines;
import mochi.task.Event;
import mochi.task.Task;
import mochi.task.ToDo;


/**
 * Storage class for storing and retrieving tasks from a file.
 * This class is responsible for saving and retrieving tasks from a specified file path.
 * The storage facilitates the persistence of task information across program executions.
 */
public class Storage {

    /**
     * Represents the file used to store and retrieve task data.
     * This object points to the file location where task information is saved and loaded.
     */
    private File f;

    /**
     * Initializes a storage instance for managing tasks data based on the specified file path.
     * This constructor ensures that the necessary directories and files for storage are created
     * if they do not already exist.
     *
     * @param filePath The file path where the storage file should be created or accessed.
     * @throws MochiException If an I/O error occurs during the file or storage initialization.
     */
    public Storage(String filePath) throws MochiException {
        try {
            // Checks if data directory exists. If not, mkdir
            File dir = new File("data");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File temp = new File(filePath);

            // Attempts to create a new file if storage file does not exist
            temp.createNewFile();

            this.f = temp;
        } catch (IOException e) {
            throw new MochiException("Error encountered during storage initialisation: " + e.getMessage());
        }
    }

    /**
     * Reads tasks from the instanced file and parses them into a list of Task objects.
     * The tasks are expected to be stored in a specific format where the
     * first part indicates the task type (e.g., "T" for ToDos, "D" for Deadlines, "E" for Events),
     * followed by relevant task details. Invalid or malformed task entries
     * will throw an exception.
     *
     * @return An ArrayList of Task objects parsed from the file. It contains
     *         tasks of type ToDo, Deadlines, or Event, based on the stored data.
     * @throws MochiException If there is an error while reading or parsing the file.
     */
    public ArrayList<Task> readTasks() throws MochiException {

        ArrayList<Task> temp = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.f);
            while (sc.hasNext()) {
                String tempTask = sc.nextLine();
                if (tempTask.isEmpty()) {
                    // Last line will be empty
                    break;
                } else {
                    // First part task type, second part description
                    String[] parts = tempTask.split(" \\| ", 2);
                    if (parts[0].equals("T")) {
                        temp.add(ToDo.parseString(parts[1]));
                    } else if (parts[0].equals("D")) {
                        temp.add(Deadlines.parseString(parts[1]));
                    } else if (parts[0].equals("E")) {
                        temp.add(Event.parseString(parts[1]));
                    } else {
                        throw new MochiException("Error encountered while parsing: " + tempTask);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new MochiException("File not found while reading tasks");
        }

        return temp;
    }

    /**
     * Saves a list of tasks to the instanced file.
     * This method serializes the provided list of tasks into a string format
     * suitable for storage and writes it to the designated file.
     *
     * @param tasks The list of tasks to be saved to the file. Each task in the list
     *              should implement the {@code toSaveString} method to produce
     *              its serialized representation.
     * @throws MochiException If an error occurs while writing the tasks to the file.
     */
    public void saveTasks(List<Task> tasks) throws MochiException {
        String temp = "";
        for (int i = 0; i < tasks.size(); i++) {
            temp = temp + tasks.get(i).toSaveString() + "\n";
        }
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(temp);
            fw.close();
        } catch (IOException e) {
            throw new MochiException("Error encountered while saving tasks: " + e.getMessage());
        }
    }

}
