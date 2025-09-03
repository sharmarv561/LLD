package TaskmanagementSystem.FilterStrategy;

import TaskmanagementSystem.Enums.TaskPriority;
import TaskmanagementSystem.Task;

import java.util.ArrayList;
import java.util.List;

public class PriorityFilter implements TaskFilterStrategy {

    private final TaskPriority priority;

    public PriorityFilter(TaskPriority priority) {
        this.priority = priority;
    }
    @Override
    public List<Task> filter(List<Task> tasks) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                result.add(task);
            }
        }
        return result;    }
}
