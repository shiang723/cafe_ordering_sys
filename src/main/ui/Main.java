package ui;

import java.io.FileNotFoundException;

// This [class/method] references code from these [repo/website]
// Link: [https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git]
public class Main {
    public static void main(String[] args) {
        try {
            new CafeOrderingMenuApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
