curl --location 'http://localhost:8080/api/metadata/workflow' \
--header 'Content-Type: application/json' \
--data-raw '{
  "name": "Food_Order_Workflow",
  "description": "Food Order Workflow",
  "version": 2,
  "tasks": [
    {
      "name": "create_order_task",
      "taskReferenceName": "create_order_task",
      "inputParameters": {
        "order": "${workflow.input.order}"
      },
      "type": "SIMPLE",
      "startDelay": 0,
      "optional": false,
      "asyncComplete": false
    },
    {
      "name": "accept_order_task",
      "taskReferenceName": "accept_order_task",
      "inputParameters": {},
      "type": "SIMPLE",
      "startDelay": 0,
      "optional": false,
      "asyncComplete": false
    },
    {
      "name": "prepare_order_task",
      "taskReferenceName": "prepare_order_task",
      "inputParameters": {},
      "type": "SIMPLE",
      "startDelay": 0,
      "optional": false,
      "asyncComplete": false
    },
    {
      "name": "send_order_task",
      "taskReferenceName": "send_order_task",
      "inputParameters": {},
      "type": "SIMPLE",
      "startDelay": 0,
      "optional": false,
      "asyncComplete": false
    },
    {
      "name": "fork_join",
      "taskReferenceName": "fork_join",
      "inputParameters": {},
      "type": "FORK_JOIN",
      "forkTasks": [
        [
          {
            "name": "deliver_order_task",
            "taskReferenceName": "deliver_order_task",
            "inputParameters": {},
            "type": "SIMPLE",
            "startDelay": 0,
            "optional": false,
            "asyncComplete": false
          }
        ],
        [
          {
            "name": "take_payment_task",
            "taskReferenceName": "take_payment_task",
            "inputParameters": {
              "cost": "${create_order_task.output.cost}"
            },
            "type": "SIMPLE",
            "startDelay": 0,
            "optional": false,
            "asyncComplete": false
          }
        ]
      ],
      "startDelay": 0,
      "optional": false,
      "asyncComplete": false
    },
    {
      "name": "complete_order_join",
      "taskReferenceName": "complete_order_join",
      "inputParameters": {},
      "type": "JOIN",
      "startDelay": 0,
      "joinOn": [
        "deliver_order_task",
        "take_payment_task"
      ],
      "optional": false,
      "asyncComplete": false
    }
  ],
  "inputParameters": [],
  "outputParameters": {},
  "schemaVersion": 2,
  "restartable": true,
  "workflowStatusListenerEnabled": false,
  "ownerEmail": "example@mail.com",
  "timeoutPolicy": "ALERT_ONLY",
  "timeoutSeconds": 0,
  "variables": {},
  "inputTemplate": {}
}'