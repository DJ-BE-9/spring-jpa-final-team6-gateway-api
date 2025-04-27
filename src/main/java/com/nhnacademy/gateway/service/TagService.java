package com.nhnacademy.gateway.service;

import com.nhnacademy.gateway.common.adaptor.tag.ProjectGetTagsAdaptor;
import com.nhnacademy.gateway.exception.EmptyRequestException;
import com.nhnacademy.gateway.model.dto.tag.ResponseTagDto;
import com.nhnacademy.gateway.model.dto.tag.ResponseTagsDto;
import com.nhnacademy.gateway.model.dto.tag.TagRequest;
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

    public List<ResponseTagDto> getTagsByProjectId(TagRequest tagRequest) {
        if(Objects.isNull(tagRequest)){
            throw  new EmptyRequestException("Tag 값을 받지 못했습니다.");
        }
        ResponseTagsDto responseTagsDto = projectGetTagsAdaptor.sendAndGetProjectTags(tagRequest);
        log.info(responseTagsDto.toString());

        return responseTagsDto.getTagList();
    }

}
