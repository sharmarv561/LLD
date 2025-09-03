package LoggingFramework;

public enum LogLevel {
    DEBUG(1),
    INFO(2),
    WARNING(3),
    ERROR(4),
    FATAL(5);

    private final int severity;

    LogLevel(int severity) {
        this.severity = severity;
    }

    public int getSeverity() {
        return severity;
    }

    /**
     * Returns true if this level is at least as severe as the given level.
     * Used for threshold-based filtering.
     */
    public boolean isAtLeast(LogLevel other) {
        return this.severity >= other.severity;
    }
}
