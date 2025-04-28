package com.nhnacademy.gateway.controller.tag;

import com.nhnacademy.gateway.model.dto.tag.*;
import com.nhnacademy.gateway.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    // 프로젝트에 태그 등록하기
    @PostMapping
    public ResponseEntity<ResponsePostTagDto> createTag(@RequestBody TagRegisterToProjectRequest tagRegisterToProjectRequest,
                                                        @PathVariable("projectId") long projectId) {
        tagService.registerTag(tagRegisterToProjectRequest, projectId);

        ResponsePostTagDto response = new ResponsePostTagDto(tagRegisterToProjectRequest.getTagName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    //태그 수정
    @PutMapping("/{tagId}")
    public ResponseEntity<ResponseUpdateTagDto> updateTag(@RequestBody TagUpdateRequest tagUpdateRequest,
                                                          @PathVariable("tagId") long tagId,
                                                          @PathVariable("projectId") long projectId) {
        tagService.updateTag(tagId,tagUpdateRequest, projectId);
        ResponseUpdateTagDto response = new ResponseUpdateTagDto(tagId, tagUpdateRequest.getTagName());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //태그 삭제
    @DeleteMapping("/{tagId}")
    public ResponseEntity<ResponseDeleteTagDto> deleteTag(@PathVariable("tagId") long tagId,
                                                          @PathVariable("projectId") long projectId){
        tagService.deleteTag(tagId,projectId);
        ResponseDeleteTagDto response = new ResponseDeleteTagDto(tagId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}