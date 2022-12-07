package com.luftmensch.project.auto;

import com.luftmensch.project.bean.TestServiceProperties;
import com.luftmensch.project.service.TestService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(TestServiceProperties.class)
public class ProjectAutoConfiguration {

    @ConditionalOnMissingBean(TestService.class)
    @Bean
    public TestService testService() {
        return new TestService();
    }
}
