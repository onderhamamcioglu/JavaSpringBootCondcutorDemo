package com.example.springbootconductorexample.Worker;

import com.netflix.conductor.client.worker.Worker;
import com.netflix.conductor.common.metadata.tasks.Task;
import com.netflix.conductor.common.metadata.tasks.TaskResult;

import java.util.Random;

public class CreateOrderWorker implements Worker{

    private final String taskDefName;

    public CreateOrderWorker(String taskDefName) {
        this.taskDefName = taskDefName;
    }

    @Override
    public String getTaskDefName() {
        return taskDefName;
    }

    @Override
    public TaskResult execute(Task task) {
        TaskResult result = new TaskResult(task);

        String input = (String) task.getInputData().get("order");

        int cost = new Random().nextInt(100);
        result.addOutputData("cost", cost);

        System.out.println("***Order Created: " + input + "***");

        result.setStatus(TaskResult.Status.COMPLETED);
        return result;
    }

}
