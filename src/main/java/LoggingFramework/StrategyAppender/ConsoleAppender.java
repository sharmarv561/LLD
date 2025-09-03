package LoggingFramework.StrategyAppender;

import LoggingFramework.LogLevel;
import LoggingFramework.LogMessage;
import LoggingFramework.StrategyFormatter.LogFormatter;

public class ConsoleAppender implements LogAppender {
    private final LogFormatter formatter;

    public ConsoleAppender(LogFormatter formatter) {
        if (formatter == null) throw new IllegalArgumentException("Formatter cannot be null");
        this.formatter = formatter;
    }

    @Override
    public void append(LogMessage message) {
        String output = formatter.format(message);

        // You can route based on severity if you want to split stdout/stderr
        if (message.getLevel().isAtLeast(LogLevel.ERROR)) {
            System.err.println(output);
        } else {
            System.out.println(output);
        }
    }
}
