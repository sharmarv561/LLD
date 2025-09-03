package TaskmanagementSystem;

import TaskmanagementSystem.Enums.TaskPriority;
import TaskmanagementSystem.FilterStrategy.TaskFilterStrategy;
import TaskmanagementSystem.Observer.Logger;

import java.time.LocalDate;
import java.util.*;

/**
 * Singleton class to manage all users and tasks in the system.
 * Implements task creation, assignment, filtering, commenting, and priority updates.
 */
public class TaskManagementSystem {

    private static TaskManagementSystem instance;
    private final Map<String, User> users;
    private final Map<String, Task> tasks;

    // Private constructor for singleton
    private TaskManagementSystem() {
        users = new HashMap<>();
        tasks = new HashMap<>();
    }

    public static synchronized TaskManagementSystem getInstance() {
        if (instance == null) {
            instance = new TaskManagementSystem();
        }
        return instance;
    }

    public User createUser(String name, String email) {
        User user = new User(UUID.randomUUID().toString(), name, email);
        users.put(user.getId(), user);
        return user;
    }

    public Task createTask(String title, String description, LocalDate dueDate,
                           TaskPriority priority, String createdByUserId) {
        User createdBy = users.get(createdByUserId);

        // Validate user exists
        if (createdBy == null) {
            throw new IllegalArgumentException("User not found.");
        }

        // Build task using Builder pattern
        Task task = new Task.TaskBuilder(title)
                .description(description)
                .dueDate(dueDate)
                .priority(priority)
                .createdBy(createdBy)
                .build();

        // Add logger as observer
        task.addObserver(new Logger());

        // Store task
        tasks.put(task.getId(), task);
        return task;
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values()); // return copy to avoid external modification
    }

    public Task getTaskById(String taskId) {
        return tasks.get(taskId);
    }

    public void deleteTask(String taskId) {
        tasks.remove(taskId);
    }

    public void addCommentToTask(String taskId, Comment comment) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.addComment(comment);
        }
    }

    public List<Task> filterTasks(TaskFilterStrategy filter) {
        return filter.filter(getAllTasks());
    }

    //in case of re assigning
    public void assignUserToTask(String taskId, String userId) {
        Task task = tasks.get(taskId);
        User user = users.get(userId);
        if (task != null && user != null) {
            task.assignUser(user);
        }
    }

    public void updateTaskPriority(String taskId, TaskPriority newPriority) {
        Task task = tasks.get(taskId);
        if (task != null) {
            task.updatePriority(newPriority);
        }
    }


}
