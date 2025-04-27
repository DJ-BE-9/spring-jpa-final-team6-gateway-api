package com.nhnacademy.gateway.controller.tag;

import com.nhnacademy.gateway.model.dto.tag.ResponseTagDto;
import com.nhnacademy.gateway.model.dto.tag.TagRequest;
import com.nhnacademy.gateway.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project/{projectId}/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    //프로젝트 관련 테그 get
    @GetMapping
    public List<ResponseTagDto> getTags(@PathVariable("projectId") long projectId) {
        return tagService.getTagsByProjectId(new TagRequest(projectId));
    }





}