package com.example.springbootconductorexample.Worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

public class TakePaymentWorker implements Worker {

    private final String taskDefName;

    public TakePaymentWorker(String taskDefName) {
        this.taskDefName = taskDefName;
    }

    @Override
    public String getTaskDefName() {
        return taskDefName;
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);
        int input = (int) task.getInputData().get("cost");

        System.out.println("***Payment Taken: " + input + "TL***");

        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }
}
