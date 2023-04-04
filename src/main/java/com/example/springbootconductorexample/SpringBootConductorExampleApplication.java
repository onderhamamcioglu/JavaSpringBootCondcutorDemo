package com.example.springbootconductorexample;

import com.example.springbootconductorexample.Worker.*;
import com.netflix.conductor.client.automator.TaskRunnerConfigurer;
import com.netflix.conductor.client.http.TaskClient;
import com.netflix.conductor.client.worker.Worker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.ArrayList;
import java.util.Collection;

//FIXME | Port is changed (application.properties) because Conductor uses 8080

/*
1 -) Client makes request for meal - Order Created
2 -) Restaurant gets order - Order Accepted
3 -) Restaurant Prepares order - Preparing Order
4 -) Courier Takes Order - Order In Delivery
5 -) Courier arrives the customers site - Order Delivered & Payment Taken
 */

@SpringBootApplication
public class SpringBootConductorExampleApplication {

    public static void main(String[] args) {

        //Set up server api
        TaskClient taskClient = new TaskClient();
        taskClient.setRootURI("http://localhost:8080/api/");

        int threadCount = 6; // number of threads used to execute workers.

        //List of workers to give conductor
        Collection workerArrayList = new ArrayList<Worker>();

        Worker createOrderWorker = new CreateOrderWorker("create_order_task");
        workerArrayList.add(createOrderWorker);
        Worker acceptOrderWorker = new AcceptOrderWorker("accept_order_task");
        workerArrayList.add(acceptOrderWorker);
        Worker prepareOrderWorker = new PrepareOrderWorker("prepare_order_task");
        workerArrayList.add(prepareOrderWorker);
        Worker sendOrderWorker = new SendOrderWorker("send_order_task");
        workerArrayList.add(sendOrderWorker);
        Worker deliverOrderWorker = new DeliverOrderWorker("deliver_order_task");
        workerArrayList.add(deliverOrderWorker);
        Worker takePaymentWorker = new TakePaymentWorker("take_payment_task");
        workerArrayList.add(takePaymentWorker);



        // Create TaskRunnerConfigurer
        TaskRunnerConfigurer configurer =
                new TaskRunnerConfigurer.Builder(taskClient, workerArrayList)
                        .withThreadCount(threadCount)
                        .build();
        // Start the polling and execution of tasks
        configurer.init();
        

        SpringApplication.run(SpringBootConductorExampleApplication.class, args);
    }

}
