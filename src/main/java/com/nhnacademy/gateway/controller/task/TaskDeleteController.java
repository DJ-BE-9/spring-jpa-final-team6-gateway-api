package com.nhnacademy.gateway.controller.task;

import com.nhnacademy.gateway.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/project/{projectId}/task/{taskId}")
public class TaskDeleteController {

    private TaskService taskService;

    public TaskDeleteController(TaskService taskService) {
        this.taskService = taskService;
    }

    @DeleteMapping
    public String deleteTask(@PathVariable("projectId") long projectId,
                             @PathVariable("taskId") long taskId) {
        taskService.deleteTask(projectId, taskId);
        return "redirect:/project/" + projectId;
    }

}
