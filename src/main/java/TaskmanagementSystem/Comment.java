package TaskmanagementSystem;

import java.time.LocalDateTime;

public class Comment {
    private final String id;
    private final String message;
    private final User createdBy;
    private final LocalDateTime createdAt;

    public Comment(String id, String message, User createdBy, LocalDateTime createdAt) {
        this.id = id;
        this.message = message;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public String getId() { return id; }
    public String getMessage() { return message; }
    public User getCreatedBy() { return createdBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "Comment{id='" + id + "', message='" + message + "', createdBy=" + createdBy.getName() +
                ", createdAt=" + createdAt + "}";
    }
}
