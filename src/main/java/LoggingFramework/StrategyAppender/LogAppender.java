package LoggingFramework.StrategyAppender;

import LoggingFramework.LogMessage;

public interface LogAppender {

    public void append(LogMessage message);
}
