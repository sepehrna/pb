package com.pb.coreservices.controller.api;

import com.pb.coreservices.controller.dto.ApplicationUsersDto;
import com.pb.coreservices.controller.mapper.ApplicationUsersControllerMapper;
import com.pb.coreservices.domain.entity.ApplicationUsers;
import com.pb.coreservices.domain.service.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class ApplicationUserApi {

    private final ApplicationUserService applicationUserService;
    private final ApplicationUsersControllerMapper applicationUsersControllerMapper;

    @Autowired
    public ApplicationUserApi(ApplicationUserService applicationUserService, ApplicationUsersControllerMapper applicationUsersControllerMapper) {
        this.applicationUserService = applicationUserService;
        this.applicationUsersControllerMapper = applicationUsersControllerMapper;
    }

    @GetMapping(value = "/get-application-users")
    public ApplicationUsersDto getApplicationUsersDto() {
        ApplicationUsers applicationUsers = applicationUserService.getApplicationUsers();
        return applicationUsersControllerMapper.map(applicationUsers);
    }

    public void initiateUsers() throws Exception {
        applicationUserService.initiateUsers();
    }

}
