package LoggingFramework.StrategyFormatter;

import LoggingFramework.LogMessage;

public interface LogFormatter {

    String format(LogMessage message);

}
