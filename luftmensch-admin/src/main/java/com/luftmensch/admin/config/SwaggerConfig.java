package com.luftmensch.admin.config;


import com.luftmensch.admin.pojo.SwaggerInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@Configuration
public class SwaggerConfig {

    private final SwaggerInfo swaggerInfo;

    @Autowired
    public SwaggerConfig(SwaggerInfo swaggerInfo) {
        this.swaggerInfo = swaggerInfo;
    }

    @Bean
    public Docket docket() {

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("qinc")
                .enable(swaggerInfo.getEnable())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                .apis(RequestHandlerSelectors.basePackage("com.xiaotools.**.controller"))
                .build()
                .protocols(newHashSet("https", "http"));
    }

    //基本信息，页面展示
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerInfo.getApplicationTitle())
                .description(swaggerInfo.getApplicationDescription())
                .version(swaggerInfo.getApplicationVersion())
                .license(swaggerInfo.getLicense())
                .licenseUrl(swaggerInfo.getLicenseUrl())
                .contact(swaggerInfo.getContact())
                .build();
    }

    @SafeVarargs
    private final <T> Set<T> newHashSet(T... ts) {
        if (ts.length > 0) {
            return new LinkedHashSet<>(Arrays.asList(ts));
        }
        return null;
    }
}
