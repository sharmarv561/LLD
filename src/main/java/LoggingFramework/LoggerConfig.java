package LoggingFramework;

import LoggingFramework.StrategyAppender.LogAppender;
import LoggingFramework.StrategyFormatter.LogFormatter;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LoggerConfig {
    private final LogLevel levelThreshold;
    private final List<LogAppender> appenders;
    private final LogFormatter formatter;

    public LoggerConfig(LogLevel levelThreshold, List<LogAppender> appenders, LogFormatter formatter) {
        this.levelThreshold = Objects.requireNonNull(levelThreshold, "Log level is required");
        this.appenders = List.copyOf(Objects.requireNonNull(appenders, "Appenders list cannot be null"));
        this.formatter = Objects.requireNonNull(formatter, "Formatter is required");

        if (appenders.isEmpty()) {
            throw new IllegalArgumentException("At least one appender must be configured");
        }
    }

    public LogLevel getLevelThreshold() { return levelThreshold; }

    public List<LogAppender> getAppenders() {
        return Collections.unmodifiableList(appenders);
    }

    public LogFormatter getFormatter() { return formatter; }
}

