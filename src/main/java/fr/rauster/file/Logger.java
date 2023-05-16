package fr.rauster.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    
    private final BufferedWriter writer;
    private int state = 1;
    private String line = "";
    public Logger() {
        try {
            this.writer = new BufferedWriter(new FileWriter("log.fish"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void log(String string) {
        line = line + ", " + string;
    }
    public void newLine() {
        try {
            writer.write("state:" + state + line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        line = "";
        state++;
    }
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
