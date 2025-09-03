package TaskmanagementSystem;

import TaskmanagementSystem.Enums.TaskPriority;
import TaskmanagementSystem.FilterStrategy.AssigneeFilter;
import TaskmanagementSystem.FilterStrategy.PriorityFilter;
import TaskmanagementSystem.FilterStrategy.TaskFilterStrategy;
import TaskmanagementSystem.Observer.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TaskManagementDemo {
    public static void main(String[] args) {

        TaskManagementSystem system = TaskManagementSystem.getInstance();

        // 1. Create Users
        User rohit = system.createUser("Rohit Sharma", "rohit@example.com");
        User virat = system.createUser("Virat Kohli", "virat@example.com");

        // 2. Create Task (without assignee)
        Task task1 = system.createTask(
                "Design API endpoints",
                "Design all REST endpoints for the task service",
                LocalDate.now().plusDays(5),
                TaskPriority.HIGH,
                rohit.getId()
        );

        // 3. Create another Task and assign directly
        Task task2 = new Task.TaskBuilder("Write Unit Tests")
                .description("Write JUnit tests for Task class")
                .dueDate(LocalDate.now().plusDays(2))
                .priority(TaskPriority.MEDIUM)
                .createdBy(virat)
                .assignee(rohit)
                .build();

        task2.addObserver(new Logger());
        system.getAllTasks().add(task2);

// 4. Assign Task1 to Virat
        system.assignUserToTask(task1.getId(), virat.getId());

        Comment comment = new Comment("1", "Please complete by Friday", rohit, LocalDate.now().atStartOfDay());
        system.addCommentToTask(task1.getId(), comment);

        // 6. Start Progress on Task1
        task1.startProgress();

        // 7. Filter by Priority = HIGH
        System.out.println("\n--- HIGH Priority Tasks ---");
        TaskFilterStrategy highPriorityFilter = new PriorityFilter(TaskPriority.HIGH);
        List<Task> highPriorityTasks = system.filterTasks(highPriorityFilter);
        for (Task task : highPriorityTasks) {
            task.display("  ");
        }

        /*
         * Updates a task's priority.
         */

        // 8. Filter by Assignee = Virat
        System.out.println("\n--- Tasks Assigned to Virat ---");
        TaskFilterStrategy assigneeFilter = new AssigneeFilter(virat);
        List<Task> assignedToVirat = system.filterTasks(assigneeFilter);
        for (Task task : assignedToVirat) {
            task.display("  ");
        }
    }
}