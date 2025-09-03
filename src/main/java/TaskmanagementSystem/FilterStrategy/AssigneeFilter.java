package TaskmanagementSystem.FilterStrategy;

import TaskmanagementSystem.Task;
import TaskmanagementSystem.User;

import java.util.ArrayList;
import java.util.List;

public class AssigneeFilter implements TaskFilterStrategy{
    private final User assignee;

    public AssigneeFilter(User assignee) {
        this.assignee = assignee;
    }

    @Override
    public List<Task> filter(List<Task> tasks) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getAssignee() != null && task.getAssignee().equals(assignee)) {
                result.add(task);
            }
        }
        return result;
    }
}
