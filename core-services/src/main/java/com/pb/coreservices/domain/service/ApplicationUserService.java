package com.pb.coreservices.domain.service;

import com.pb.coreservices.domain.entity.ApplicationUsers;

public interface ApplicationUserService {

    ApplicationUsers getApplicationUsers();

    void initiateUsers() throws Exception;

}
