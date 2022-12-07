package com.luftmensch.admin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import springfox.documentation.service.Contact;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component("SwaggerInfo")
@ConfigurationProperties(prefix = "swagger")
public class SwaggerInfo {

    /**
     * 是否开启swagger，生产环境一般关闭，所以这里定义一个变量
     */
    private Boolean enable;

    /**
     * 项目应用名
     */
    private String applicationTitle;

    /**
     * 项目版本信息
     */
    private String applicationVersion;

    /**
     * 项目描述信息
     */
    private String applicationDescription;

    /**
     * 接口调试地址
     */
    private String tryHost;

    /**
     * 开源版本号
     */
    private String license;

    /**
     * 开源版本地址
     */
    private String licenseUrl;

    /**
     * 作者名称
     */
    private String name;

    /**
     * 作者博客地址
     */
    private String url;

    /**
     * 作者邮箱
     */
    private  String email;

    /**
     * Contact对象封装
     */
    public Contact getContact() {
        if (this.name == null) {
            this.name = "";
        }
        if(this.url == null) {
            this.url = "";
        }
        if(this.email == null) {
            this.email = "";
        }
        return new Contact(this.name, this.url, this.email);
    }

}
