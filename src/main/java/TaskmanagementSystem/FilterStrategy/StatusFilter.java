package TaskmanagementSystem.FilterStrategy;

import TaskmanagementSystem.Enums.TaskStatus;
import TaskmanagementSystem.Task;

import java.util.ArrayList;
import java.util.List;

public class StatusFilter implements TaskFilterStrategy {
    private final TaskStatus status;

    public StatusFilter(TaskStatus status) {
        this.status = status;
    }

    @Override
    public List<Task> filter(List<Task> tasks) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getState().getStatus() == status) {
                result.add(task);
            }
        }
        return result;
    }
}
