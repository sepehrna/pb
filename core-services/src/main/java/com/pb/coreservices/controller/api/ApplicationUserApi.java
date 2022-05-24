package com.pb.coreservices.controller.api;

import com.pb.coreservices.controller.dto.ApplicationUsersDto;
import com.pb.coreservices.controller.handler.ApiResultHandler;
import com.pb.coreservices.controller.handler.ApiResultHandlerImpl;
import com.pb.coreservices.controller.mapper.ApplicationUsersControllerMapper;
import com.pb.coreservices.domain.entity.ApplicationUsers;
import com.pb.coreservices.domain.service.ApplicationUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/users")
public class ApplicationUserApi {

    private final Logger logger = LoggerFactory.getLogger(ApplicationUserApi.class);

    private final ApplicationUserService applicationUserService;
    private final ApplicationUsersControllerMapper applicationUsersControllerMapper;

    @Autowired
    public ApplicationUserApi(ApplicationUserService applicationUserService, ApplicationUsersControllerMapper applicationUsersControllerMapper) {
        this.applicationUserService = applicationUserService;
        this.applicationUsersControllerMapper = applicationUsersControllerMapper;
    }

    @GetMapping(value = "/get-application-users")
    public ResponseEntity<ApplicationUsersDto> getApplicationUsers() {
        logger.info("Get application users api has been called");
        ApiResultHandler<ApplicationUsersDto> apiResultHandler = new ApiResultHandlerImpl<>();
        ResponseEntity<ApplicationUsersDto> response;
        try {
            ApplicationUsers applicationUsers = applicationUserService.getApplicationUsers();
            ApplicationUsersDto mappedSavedMember = applicationUsersControllerMapper.map(applicationUsers);
            response = apiResultHandler.handle(mappedSavedMember);
        } catch (Exception e) {
            logger.error("Get application users api call has been done with error");
            e.printStackTrace();
            response = apiResultHandler.handle(e);
        }
        return response;
    }

    public void initiateUsers() throws Exception {
        applicationUserService.initiateUsers();
    }

}
