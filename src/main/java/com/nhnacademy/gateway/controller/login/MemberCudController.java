package com.nhnacademy.gateway.controller.login;

import com.nhnacademy.gateway.common.adaptor.login.MemberStatusAdaptor;
import com.nhnacademy.gateway.model.request.member.MemberCudRequest;
import com.nhnacademy.gateway.model.dto.ResponseDto;
import com.nhnacademy.gateway.model.type.Cud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberCudController {

    @Autowired
    private MemberStatusAdaptor memberStatusAdaptor;

    @PostMapping("/task/{memberId}/state")
    public String postMemberCud(@PathVariable("memberId") String memberId,
                                @RequestParam("cud") Cud cud) {

        ResponseDto response = memberStatusAdaptor.sendMemberStatusRequest(new MemberCudRequest(memberId, cud));
        if(cud == Cud.WITHDRAWAL || cud == Cud.DORMANT) {
            return "redirect:/";
        }

        return "redirect:/task/" + memberId;
    }

}
