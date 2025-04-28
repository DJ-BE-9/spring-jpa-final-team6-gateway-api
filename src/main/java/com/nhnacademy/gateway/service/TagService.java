package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.common.adaptor.tag.ProjectGetTagsAdaptor;
import com.nhnacademy.gateway.common.adaptor.tag.TagDeleteAdapter;
import com.nhnacademy.gateway.common.adaptor.tag.TagRegisterAdapter;
import com.nhnacademy.gateway.common.adaptor.tag.TagUpdateAdapter;
import com.nhnacademy.gateway.exception.EmptyRequestException;
import com.nhnacademy.gateway.model.dto.tag.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@Transactional
public class TagService {

    @Autowired
    private ProjectGetTagsAdaptor projectGetTagsAdaptor;
    @Autowired
    private TagRegisterAdapter tagRegisterAdapter;
    @Autowired
    private TagUpdateAdapter tagUpdateAdapter;
    @Autowired
    private TagDeleteAdapter tagDeleteAdapter;

    public List<ResponseGetTagDto> getTagsByProjectId(TagRequest tagRequest) {
        if(Objects.isNull(tagRequest)){
            throw  new EmptyRequestException("Tag 값을 받지 못했습니다.");
        }
        ResponseGetTagsDto responseGetTagsDto = projectGetTagsAdaptor.sendAndGetProjectTags(tagRequest);
        log.info(responseGetTagsDto.toString());

        return responseGetTagsDto.getTagList();
    }

    public void registerTag(TagRegisterToProjectRequest tagRegisterToProjectRequest, long projectId) {
        if(Objects.isNull(tagRegisterToProjectRequest) || tagRegisterToProjectRequest.getTagName().isEmpty()){
            throw new EmptyRequestException("Tag Name을 받지 못했습니다.");
        }
        tagRegisterAdapter.sendRegisterRequest(projectId,tagRegisterToProjectRequest);
    }

    public void updateTag(long tagId,TagUpdateRequest tagUpdateRequest,long projectId) {
        if(tagUpdateRequest.getTagName().isEmpty()){
            throw new EmptyRequestException("Tag 값을 받지 못했습니다.");
        }
        tagUpdateAdapter.sendUpdateRequest(tagId,tagUpdateRequest,projectId);
    }

    public void deleteTag(long tagId,long projectId) {
        tagDeleteAdapter.sendDeleteRequest(tagId,projectId);
    }


}
