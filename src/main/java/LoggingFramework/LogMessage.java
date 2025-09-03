package LoggingFramework;

import java.time.Instant;
import java.util.Objects;

public final class LogMessage {
    private final LogLevel level;
    private final String message;
    private final Instant timestamp;
    private final String threadName;
    private final String loggerName;
    private final Throwable error; // optional

    public LogMessage(LogLevel level, String message, String loggerName, Throwable error) {
        this.level = Objects.requireNonNull(level, "LogLevel is required");
        this.message = Objects.requireNonNull(message, "message is required");
        this.loggerName = loggerName != null ? loggerName : "DefaultLogger";
        this.timestamp = Instant.now();
        this.threadName = Thread.currentThread().getName();
        this.error = error; // can be null
    }

    public LogLevel getLevel() { return level; }
    public String getMessage() { return message; }
    public Instant getTimestamp() { return timestamp; }
    public String getThreadName() { return threadName; }
    public String getLoggerName() { return loggerName; }
    public Throwable getError() { return error; }

    public boolean hasError() {
        return error != null;
    }
}
