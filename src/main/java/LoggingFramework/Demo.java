package LoggingFramework;

import LoggingFramework.StrategyAppender.ConsoleAppender;
import LoggingFramework.StrategyAppender.FileAppender;
import LoggingFramework.StrategyAppender.LogAppender;
import LoggingFramework.StrategyFormatter.DefaultFormatter;
import LoggingFramework.StrategyFormatter.LogFormatter;

import java.util.List;

public class Demo {


        public static void main(String[] args) {
            // 1. Create a formatter
            LogFormatter formatter = new DefaultFormatter();

            // 2. Create appenders
            LogAppender consoleAppender = new ConsoleAppender(formatter);
            LogAppender fileAppender = new FileAppender(formatter, "logs.txt", true); // append to file

            // 3. Build logger config
            LoggerConfig config = new LoggerConfig(
                    LogLevel.DEBUG,                        // log everything DEBUG and above
                    List.of(consoleAppender, fileAppender), // fan out to both
                    formatter
            );

            // 4. Register global config
            LoggerManager loggerManager = LoggerManager.getInstance();
            loggerManager.setGlobalConfig(config);

            // 5. Get logger and use it
            Logger logger = loggerManager.getLogger("MainLogger");

            logger.debug("This is a debug message.");
            logger.info("Application started.");
            logger.warning("Low disk space.");
            logger.error("Something went wrong.");
            logger.fatal("Critical failure!", new RuntimeException("System shutdown"));

            // Done! Logs will go to both console and file
        }
    }


