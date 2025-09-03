package LoggingFramework;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class LoggerManager {
    private static final LoggerManager INSTANCE = new LoggerManager();

    private final ConcurrentMap<String, Logger> loggerMap = new ConcurrentHashMap<>();
    private LoggerConfig globalConfig; // Optional: apply same config to all

    private LoggerManager() {} // private constructor to enforce singleton

    public static LoggerManager getInstance() {
        return INSTANCE;
    }

    /**
     * Get or create a named logger.
     */
    public Logger getLogger(String name) {
        return loggerMap.computeIfAbsent(name, loggerName -> {
            Logger logger = new Logger(loggerName);
            if (globalConfig != null) {
                logger.setConfig(globalConfig);
            }
            return logger;
        });
    }

    /**
     * Set a default config for all loggers created after this call.
     */
    public void setGlobalConfig(LoggerConfig config) {
        this.globalConfig = config;
    }

    /**
     * Apply config to all existing loggers.
     */
    public void applyConfigToAll(LoggerConfig config) {
        this.globalConfig = config;
        for (Logger logger : loggerMap.values()) {
            logger.setConfig(config);
        }
    }
}
