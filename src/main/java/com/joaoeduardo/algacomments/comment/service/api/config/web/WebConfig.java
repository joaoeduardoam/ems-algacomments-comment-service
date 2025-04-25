package com.joaoeduardo.algacomments.comment.service.api.config.web;

import org.springframework.context.annotation.*;
import org.springframework.format.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {

    }
}
