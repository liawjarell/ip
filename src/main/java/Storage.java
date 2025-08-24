import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private File f;
    private String finalString;


    public Storage(String filePath) throws IOException {
        File temp = new File(filePath);
        if (temp.createNewFile()) {
        }
        this.f = temp;
    }

//    public ArrayList<Task> load() {
//
//    }

//    public ArrayList<Task> readTasks() {
//        ArrayList<Task> temp = new ArrayList<>();
//        try {
//            Scanner sc = new Scanner(this.f);
//            while (sc.hasNext()) {
//
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found");
//        }
//    }

    public void saveTask(List<Task> tasks) throws IOException {
        String temp = "";
        for (int i = 0; i < tasks.size(); i++) {
            temp = temp + tasks.get(i).toSaveString() + "\n";
        }
//        this.finalString = temp;
        FileWriter fw = new FileWriter(f);
        fw.write(temp);
        fw.close();

        System.out.println(temp);
    }

    public static void main(String[] args) {
        try {
            Storage s = new Storage("data/test.txt");
            ArrayList<Task> testTasks = new ArrayList<>();
            try {
                testTasks.add(new ToDos("read book"));
                testTasks.get(0).mark();
                testTasks.add(new Deadlines("return book /by June 6th"));
                testTasks.add(new Event("project meeting /from 2pm /to 5pm"));
                testTasks.get(2).mark();
                s.saveTask(testTasks);
            } catch (MochiException e ) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
