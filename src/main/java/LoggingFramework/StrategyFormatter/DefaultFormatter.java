package LoggingFramework.StrategyFormatter;

import LoggingFramework.LogMessage;

import java.time.format.DateTimeFormatter;

public class DefaultFormatter implements LogFormatter {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;

    @Override
    public String format(LogMessage message) {
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append(formatter.format(message.getTimestamp()))
                .append("] [")
                .append(message.getLevel())
                .append("] [")
                .append(message.getThreadName())
                .append("] ")
                .append(message.getMessage());

        if (message.hasError()) {
            sb.append(" | Exception: ").append(message.getError().toString());
        }

        return sb.toString();
    }
}
