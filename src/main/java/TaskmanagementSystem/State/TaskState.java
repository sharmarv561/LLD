package TaskmanagementSystem.State;

import TaskmanagementSystem.Enums.TaskStatus;
import TaskmanagementSystem.Task;

public interface TaskState {
    void startProgress(Task task);
    void completeTask(Task task);
    void reopenTask(Task task);
    TaskStatus getStatus();
}
