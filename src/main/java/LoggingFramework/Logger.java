package LoggingFramework;

import LoggingFramework.StrategyAppender.LogAppender;

import java.util.List;
import java.util.Objects;

public class Logger {
    private final String name;
    private LoggerConfig config;

    public Logger(String name) {
        this.name = name != null ? name : "DefaultLogger";
    }

    public void setConfig(LoggerConfig config) {
        this.config = Objects.requireNonNull(config, "LoggerConfig cannot be null");
    }

    public void log(LogLevel level, String message) {
        log(level, message, null);
    }

    public void log(LogLevel level, String message, Throwable error) {
        if (config == null) throw new IllegalStateException("LoggerConfig not set");

        if (level.isAtLeast(config.getLevelThreshold())) {
            LogMessage logMessage = new LogMessage(level, message, name, error);
            List<LogAppender> appenders = config.getAppenders();
            for (LogAppender appender : appenders) {
                appender.append(logMessage);
            }
        }
    }

    // Convenience methods
    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warning(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void fatal(String message) {
        log(LogLevel.FATAL, message);
    }

    public void error(String message, Throwable error) {
        log(LogLevel.ERROR, message, error);
    }

    public void fatal(String message, Throwable error) {
        log(LogLevel.FATAL, message, error);
    }
}
