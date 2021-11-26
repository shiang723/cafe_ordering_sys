package model;

import ui.CafeOrderingSystem;

// This [class/method] references code from these [repo/website]
// Link: [https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git]
// A class that prints the log
public class LogPrinter {

    // EFFECTS: Constructs a LogPrinter object that takes a CafeOrderingSystem as a parameter
    public LogPrinter(CafeOrderingSystem session) {
    }

    //EFFECTS: formats and prints the event logged in Event log throws exception if not passed
    public void printLog(EventLog el) throws LogException {
        for (Event e: el) {
            System.out.println(e.toString() + "\n");
        }
    }

}
