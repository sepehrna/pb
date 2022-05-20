package com.pb.coreservices.service;

import com.pb.coreservices.domain.service.ApplicationUserService;
import com.pb.coreservices.domain.entity.ApplicationUsers;
import com.pb.coreservices.service.jaxb.dto.JaxbApplicationUsersDto;
import com.pb.coreservices.service.jaxb.mapper.JaxbApplicationUsersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.FileReader;
import java.io.IOException;

@Service
public class JaxbInitiatorApplicationUserServiceImpl implements ApplicationUserService {

    private final Logger logger = LoggerFactory.getLogger(JaxbInitiatorApplicationUserServiceImpl.class);
    private ApplicationUsers applicationUsers;

    private final JaxbApplicationUsersMapper jaxbApplicationUsersMapper;
    private final Environment environment;

    @Autowired
    public JaxbInitiatorApplicationUserServiceImpl(JaxbApplicationUsersMapper jaxbApplicationUsersMapper, Environment environment) {
        this.jaxbApplicationUsersMapper = jaxbApplicationUsersMapper;
        this.environment = environment;
    }

    @Override
    public ApplicationUsers getApplicationUsers() {
        return applicationUsers;
    }

    @Override
    public void initiateUsers() throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(JaxbApplicationUsersDto.class);
        String applicationUserFilePath = environment.getProperty("application.user.xml.path");
        if (applicationUserFilePath == null) {
            logger.error("Application user file path not found please check application.properties file");
            throw new IOException("Application user file path not found");
        }
        JaxbApplicationUsersDto jaxbApplicationUsersDto = (JaxbApplicationUsersDto) context.createUnmarshaller()
                .unmarshal(new FileReader(applicationUserFilePath));
        applicationUsers = jaxbApplicationUsersMapper.map(jaxbApplicationUsersDto);
        logger.info("Application users have been loaded successfully!!");
    }
}
