package com.example.springbootconductorexample.Worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class PrepareOrderWorker implements Worker {

    private final String taskDefName;

    public PrepareOrderWorker(String taskDefName) {
        this.taskDefName = taskDefName;
    }

    @Override
    public String getTaskDefName() {
        return taskDefName;
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);

        System.out.println("***Preparing Order***");

        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
