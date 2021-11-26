package model;

// This [class/method] references code from these [repo/website]
// Link: [https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git]
// An exception for LogPrinter that extends Exception
public class LogException extends Exception {

    // Constructs a Log Exception
    public LogException() {
        super("Error printing log");
    }
}
