package com.newgen.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:ecm_next.api.properties"})
public class ECMNextAPIConfig {

}
