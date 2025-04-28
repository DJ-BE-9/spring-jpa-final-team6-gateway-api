package com.nhnacademy.gateway.controller.task;

import com.nhnacademy.gateway.exception.ValidationFailedException;
import com.nhnacademy.gateway.model.request.task.RegisterTaskTagRequest;
import com.nhnacademy.gateway.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/project/{projectId}/task/register")
public class TaskRegisterController {

    private TaskService taskService;

    public TaskRegisterController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public String registerTask(@PathVariable("projectId") long projectId, @Valid @ModelAttribute RegisterTaskTagRequest request, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ValidationFailedException(bindingResult);
        }

        taskService.registerTask(projectId, request);
        return "redirect:/project/" + projectId;
    }
}
