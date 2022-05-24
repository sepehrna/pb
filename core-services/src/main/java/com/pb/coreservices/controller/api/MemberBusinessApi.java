package com.pb.coreservices.controller.api;

import com.pb.coreservices.controller.dto.MemberDto;
import com.pb.coreservices.controller.handler.ApiResultHandler;
import com.pb.coreservices.controller.handler.ApiResultHandlerImpl;
import com.pb.coreservices.controller.mapper.MemberControllerMapper;
import com.pb.coreservices.domain.entity.Member;
import com.pb.coreservices.domain.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberBusinessApi {

    private final Logger logger = LoggerFactory.getLogger(MemberBusinessApi.class);

    private final MemberService memberService;
    private final MemberControllerMapper memberControllerMapper;

    @Autowired
    public MemberBusinessApi(MemberService memberService, MemberControllerMapper memberControllerMapper) {
        this.memberService = memberService;
        this.memberControllerMapper = memberControllerMapper;
    }

    @PostMapping("/add-member")
    public ResponseEntity<MemberDto> addMember(@RequestBody MemberDto memberDto) {
        logger.info("Add member api has been called");
        ApiResultHandler<MemberDto> apiResultHandler = new ApiResultHandlerImpl<>();
        ResponseEntity<MemberDto> response;
        try {
            Member member = memberControllerMapper.map(memberDto);
            Member savedMember = memberService.addMember(member);
            MemberDto mappedSavedMember = memberControllerMapper.map(savedMember);
            response = apiResultHandler.handle(mappedSavedMember);
        } catch (Exception e) {
            logger.error("Add member api call has been done with error");
            e.printStackTrace();
            response = apiResultHandler.handle(e);
        }
        return response;
    }
}
