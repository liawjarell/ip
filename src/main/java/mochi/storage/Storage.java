package mochi.storage;

import mochi.task.Deadlines;
import mochi.task.Event;
import mochi.task.Task;
import mochi.task.ToDos;
import mochi.exception.MochiException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    // Constants
    private File f;

    // Initialise a storage from the given filepath
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
//            System.out.println("Error encountered during storage creation: " + e.getMessage());
            throw new MochiException("Error encountered during storage initialisation: " + e.getMessage());
        }
    }

    // Parses tasks from file
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
                        temp.add(ToDos.parseString(parts[1]));
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
//            System.out.println("Error encountered while saving tasks: " + e.getMessage());
            throw new MochiException("Error encountered while saving tasks: " + e.getMessage());
        }
    }

}
