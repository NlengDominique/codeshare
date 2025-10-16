package com.lndev.codeshut;

import com.lndev.codeshut.entities.UserEntity;
import com.lndev.codeshut.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@Slf4j
class CodeshutApplicationTests {

    @Autowired
    private JwtService jwtService;


	@Test
	void contextLoads() {
        UserEntity user = UserEntity.builder()
                .id(1L)
                .email("test@fr.com")
                .name("devin")
                .password("123")
                .build();
        String token = jwtService.createToken(user);
        System.out.println("Create Token: "+token);
        log.info("Token: {}", token);
        Long id = jwtService.generateUserIdFromToken(token);
        System.out.println("Generate Id from Token: "+id);
        log.info("Generate Id from Token: {}", id);

	}

}
