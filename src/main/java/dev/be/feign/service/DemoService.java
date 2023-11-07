package dev.be.feign.service;

import dev.be.feign.config.FeignConfig;
import dev.be.feign.feign.client.DemoFeignClient;
import dev.be.feign.feign.common.dto.BaseRequestInfo;
import dev.be.feign.feign.common.dto.BaseResponseInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DemoService {
    private final DemoFeignClient demoFeignClient;
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(FeignConfig.class);
    public String get() {
        ResponseEntity<BaseResponseInfo> response = demoFeignClient.callGet("CustomHeader", "CustomName", 1L);
        log.info("Name : {}",response.getBody().getName());
        log.info("Age : {}",response.getBody().getAge());
        log.info("Header : {}",response.getBody().getHeader());
        return "get";
    }

    public String post() {
        BaseRequestInfo baseRequestInfo = BaseRequestInfo.builder()
                                                        .name("customName")
                                                        .age(2L)
                                                        .build();

        ResponseEntity<BaseResponseInfo> response = demoFeignClient.callPost("CustomHeader",baseRequestInfo);
        log.info("Name : {}",response.getBody().getName());
        log.info("Age : {}",response.getBody().getAge());
        log.info("Header : {}",response.getBody().getHeader());
        log.info("-------------------------------------------------------------");


        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // getBeanDefinition(): Bean 이름으로 Bean의 메타데이터 조회
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // ROLE.APPLICATION: 개발자가 애플리케이션 개발을 위해 등록한 bean 또는 외부 라이브러리
            // ROLE.INFRASTRUCTURE: 스프링 내부에서 사용하는 bean
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName); // getBean(): Bean 이름으로 Bean 객체(인스턴스)를 조회
                System.out.println("name = " + beanDefinitionName + " object: " + bean);
            }
        }
        return "post";
    }
}
