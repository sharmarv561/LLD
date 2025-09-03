package TaskmanagementSystem.State;

import TaskmanagementSystem.Enums.TaskStatus;
import TaskmanagementSystem.Task;

public class TODOState implements TaskState{
    @Override
    public void startProgress(Task task) {
        task.setState(new InProgressState());
    }

    @Override
    public void completeTask(Task task) {
        System.out.println("Cannot complete a task that has not started.");
    }

    @Override
    public void reopenTask(Task task) {
        System.out.println("Task is already in TODO state.");
    }

    @Override
    public TaskStatus getStatus() {
        return TaskStatus.TODO;
    }
}
