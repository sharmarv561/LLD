package TaskmanagementSystem.State;

import TaskmanagementSystem.Enums.TaskStatus;
import TaskmanagementSystem.Task;

public class InProgressState implements TaskState{
    @Override
    public void startProgress(Task task) {
        System.out.println("Task is already in progress.");
    }

    @Override
    public void completeTask(Task task) {
        task.setState(new CompletedState());
    }

    @Override
    public void reopenTask(Task task) {
        task.setState(new TODOState());
    }

    @Override
    public TaskStatus getStatus() {
        return TaskStatus.IN_PROGRESS;
    }
}
