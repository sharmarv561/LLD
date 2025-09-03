package TaskmanagementSystem;

import TaskmanagementSystem.Enums.TaskPriority;
import TaskmanagementSystem.Enums.TaskStatus;
import TaskmanagementSystem.Observer.TaskObserver;
import TaskmanagementSystem.State.TODOState;
import TaskmanagementSystem.State.TaskState;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task {

    private final String id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskPriority priority;
    private final User createdBy;
    private User assignee;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private TaskState state;  // State Pattern
    private final List<Comment> comments;
    private final List<TaskObserver> observers;

    private Task(TaskBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.description = builder.description;
        this.dueDate = builder.dueDate;
        this.priority = builder.priority;
        this.createdBy = builder.createdBy;
        this.assignee = builder.assignee;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.state = new TODOState();  // Initial state
        this.comments = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public static class TaskBuilder{
        private final String id;
        private final String title;
        private String description = "";
        private LocalDate dueDate;
        private TaskPriority priority = TaskPriority.MEDIUM;
        private User createdBy;
        private User assignee;

        public TaskBuilder(String title) {
            this.id = UUID.randomUUID().toString();
            this.title = title;
        }

        public TaskBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder dueDate(LocalDate dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public TaskBuilder priority(TaskPriority priority) {
            this.priority = priority;
            return this;
        }

        public TaskBuilder assignee(User assignee) {
            this.assignee = assignee;
            return this;
        }

        public TaskBuilder createdBy(User createdBy) {
            this.createdBy = createdBy;
            return this;
        }

        public Task build() {
            return new Task(this);
        }
    }

    // --- State Pattern Integration ---
    public void startProgress() {
        state.startProgress(this);
        updatedAt = LocalDateTime.now();
        notifyObservers("Status changed to IN_PROGRESS");
    }

    public void completeTask() {
        state.completeTask(this);
        updatedAt = LocalDateTime.now();
        notifyObservers("Status changed to COMPLETED");
    }

    public void reopenTask() {
        state.reopenTask(this);
        updatedAt = LocalDateTime.now();
        notifyObservers("Task reopened");
    }

    public void setState(TaskState newState) {
        this.state = newState;
    }

    public TaskState getState() {
        return state;
    }

    public TaskStatus getStatus() {
        return state.getStatus();
    }

    // --- Observer Pattern Integration ---
    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String action) {
        for (TaskObserver observer : observers) {
            observer.update(this, action);
        }
    }

    // --- Other Core Operations ---
    public void assignUser(User assignee) {
        this.assignee = assignee;
        updatedAt = LocalDateTime.now();
        notifyObservers("Task assigned to " + assignee.getName());
    }

    public void updatePriority(TaskPriority newPriority) {
        this.priority = newPriority;
        updatedAt = LocalDateTime.now();
        notifyObservers("Priority changed to " + newPriority);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        updatedAt = LocalDateTime.now();
        notifyObservers("New comment added by " + comment.getCreatedBy().getName());
    }

    public void display(String indent) {
        System.out.println(indent + "- " + title + " [Status: " + getStatus()
                + ", Priority: " + priority + ", Due: " + dueDate + "]");
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public TaskPriority getPriority() { return priority; }
    public User getCreatedBy() { return createdBy; }
    public User getAssignee() { return assignee; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public List<Comment> getComments() { return new ArrayList<>(comments); }

}
