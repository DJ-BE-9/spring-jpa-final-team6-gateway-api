package com.nhnacademy.gateway.controller.tag;

import com.nhnacademy.gateway.common.adaptor.tag.ProjectRegisterTagAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("project/{projectId}/tag")
public class TagsRegisterController {
    @Autowired
    private ProjectRegisterTagAdapter projectRegisterTagAdapter;



//    @PostMapping
//    public
}
