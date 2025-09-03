package TaskmanagementSystem.State;

import TaskmanagementSystem.Enums.TaskStatus;
import TaskmanagementSystem.Task;

public class CompletedState implements TaskState{
    @Override
    public void startProgress(Task task) {
        System.out.println("Cannot start progress on a completed task. Reopen it first.");
    }

    @Override
    public void completeTask(Task task) {
        System.out.println("Task is already completed.");
    }

    @Override
    public void reopenTask(Task task) {
        task.setState(new TODOState());
    }

    @Override
    public TaskStatus getStatus() {
        return TaskStatus.COMPLETED;
    }
}
