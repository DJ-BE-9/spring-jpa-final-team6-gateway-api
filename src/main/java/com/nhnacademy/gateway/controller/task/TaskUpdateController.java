package com.nhnacademy.gateway.controller.task;

import com.nhnacademy.gateway.exception.ValidationFailedException;
import com.nhnacademy.gateway.model.request.task.RegisterTaskTagRequest;
import com.nhnacademy.gateway.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project/{projectId}/task/{taskId}")
public class TaskUpdateController {

    private TaskService taskService;

    public TaskUpdateController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PutMapping
    public void updateTask(@PathVariable("projectId") long projectId, @PathVariable("taskId") long taskId,
                           @Valid @ModelAttribute RegisterTaskTagRequest registerTaskRequest,
                           BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }
        taskService.updateTask(projectId, taskId, registerTaskRequest);
    }

}
