package com.nhnacademy.gateway.controller.tag;

import com.nhnacademy.gateway.model.dto.tag.ResponseGetTagDto;
import com.nhnacademy.gateway.model.dto.tag.ResponsePostTagDto;
import com.nhnacademy.gateway.model.dto.tag.TagRegisterToProjectRequest;
import com.nhnacademy.gateway.model.dto.tag.TagRequest;
import com.nhnacademy.gateway.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project/{projectId}/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    //프로젝트 관련 테그 get
    @GetMapping
    public List<ResponseGetTagDto> getTags(@PathVariable("projectId") long projectId) {
        return tagService.getTagsByProjectId(new TagRequest(projectId));
    }

    //프로젝트에 태그 등록하기
    @PostMapping
    public ResponseEntity<ResponsePostTagDto> createTag(@RequestBody TagRegisterToProjectRequest tagRegisterToProjectRequest,
                                                       @PathVariable("projectId") long projectId) {
        ResponsePostTagDto responsePostTagDto = tagService.registerTag()
    }

 //TODO 태그 등록 하기
}