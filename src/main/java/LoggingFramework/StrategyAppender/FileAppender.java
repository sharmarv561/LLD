package LoggingFramework.StrategyAppender;

import LoggingFramework.LogMessage;
import LoggingFramework.StrategyFormatter.LogFormatter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class FileAppender implements LogAppender {
    private final LogFormatter formatter;
    private final String filePath;
    private final boolean append;
    private final PrintWriter writer;

    public FileAppender(LogFormatter formatter, String filePath, boolean append) {
        this.formatter = Objects.requireNonNull(formatter, "Formatter cannot be null");
        this.filePath = Objects.requireNonNull(filePath, "File path cannot be null");
        this.append = append;

        try {
            this.writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath, append)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to open file for logging: " + filePath, e);
        }
    }

    @Override
    public synchronized void append(LogMessage message) {
        String output = formatter.format(message);
        writer.println(output);
        writer.flush(); // ensures it's written immediately
    }

    public void close() {
        writer.close();
    }
}
