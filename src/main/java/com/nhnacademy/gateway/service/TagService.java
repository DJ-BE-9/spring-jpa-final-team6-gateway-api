package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.common.adaptor.tag.ProjectGetTagsAdaptor;
import com.nhnacademy.gateway.common.adaptor.tag.ProjectRegisterTagAdapter;
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
    private ProjectRegisterTagAdapter projectRegisterTagAdapter;

    public List<ResponseGetTagDto> getTagsByProjectId(TagRequest tagRequest) {
        if(Objects.isNull(tagRequest)){
            throw  new EmptyRequestException("Tag 값을 받지 못했습니다.");
        }
        ResponseGetTagsDto responseGetTagsDto = projectGetTagsAdaptor.sendAndGetProjectTags(tagRequest);
        log.info(responseGetTagsDto.toString());

        return responseGetTagsDto.getTagList();
    }

    public ResponsePostTagDto registerTag(TagRegisterToProjectRequest tagRegisterToProjectRequest) {
        if(Objects.isNull(tagRegisterToProjectRequest) || tagRegisterToProjectRequest.getTagName().isEmpty()){
            throw new EmptyRequestException("Tag Name을 받지 못했습니다.");
        }
        tagRegisterToProjectRequest.setTagName(tagRegisterToProjectRequest.getTagName());

        boolean isSuccess = projectRegisterTagAdapter.sendRegisterRequest(tagRegisterToProjectRequest);
        if(!isSuccess){
            throw new RuntimeException("Tag에 등록 실패하였습니다.");
        }
        return new ResponsePostTagDto(tagRegisterToProjectRequest.getTagName());
    }

}
