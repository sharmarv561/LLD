package TaskmanagementSystem.FilterStrategy;

import TaskmanagementSystem.Task;

import java.util.List;

public interface TaskFilterStrategy {
    List<Task> filter(List<Task> tasks);

}
