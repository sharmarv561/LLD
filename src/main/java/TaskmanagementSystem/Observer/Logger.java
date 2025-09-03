package TaskmanagementSystem.Observer;

import TaskmanagementSystem.Task;

public class Logger implements TaskObserver{
    @Override
    public void update(Task task, String action) {
        System.out.println("[LOG] " + action + " on Task: "
                + task.getTitle() + " [Status: "
                + task.getState().getStatus() + "]");
    }
}
