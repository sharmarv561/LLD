package TaskmanagementSystem.Observer;

import TaskmanagementSystem.Task;

public interface TaskObserver {

    void update(Task task, String action);

}
