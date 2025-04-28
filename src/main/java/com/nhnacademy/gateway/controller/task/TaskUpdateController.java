package com.nhnacademy.gateway.controller.task;

import com.nhnacademy.gateway.model.request.projectTag.RegisterProjectTagRequest;
import com.nhnacademy.gateway.model.request.task.RegisterTaskRequest;
import com.nhnacademy.gateway.model.request.task.RegisterTaskTagRequest;
import com.nhnacademy.gateway.service.TaskService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project/{projectId}/task/{taskId}")
public class TaskUpdateController {

    private TaskService taskService;

    public TaskUpdateController(TaskService taskService) {
        this.taskService = taskService;
    }

    // TODO task 수정
    @PutMapping
    public void updateTask(@PathVariable("projectId") long projectId, @PathVariable("taskId") long taskId, @ModelAttribute RegisterTaskTagRequest registerTaskRequest) {

    }

}
