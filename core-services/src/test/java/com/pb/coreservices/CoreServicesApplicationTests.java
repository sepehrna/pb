package com.pb.coreservices;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreServicesApplicationTests {

    /*@Autowired
    private MemberRepository memberRepository;*/
//    @Autowired
//    private MemberBlockingRepository memberBlockingRepository;


    @Test
    void contextLoads() {
    }

    /*@Test
    void insertTestMember() {
        memberRepository.save(MemberDao.builder()
                        .id(1L)
                        .name("sepehr")
                        .build())
                .doOnError(Throwable::printStackTrace)
                .subscribe();
    }*/

//    @Test
//    void findMemberBlocking() {
//        memberBlockingRepository.findAll();
//    }

}
