package com.devproject.homegrownmarket;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.Valid;

@Component
@ConfigurationProperties("server")
@Data
public class GlobalProperties {

    @Value("${server.app.name}")
    private String name;

}
