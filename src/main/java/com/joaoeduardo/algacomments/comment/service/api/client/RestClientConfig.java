package com.joaoeduardo.algacomments.comment.service.api.client;

import org.springframework.context.annotation.*;
import org.springframework.web.client.*;
import org.springframework.web.client.support.*;
import org.springframework.web.service.invoker.*;

@Configuration
public class RestClientConfig {

    @Bean
    public ModerationCommentClient moderationCommentClient(RestClientFactory factory) {
        RestClient restClient = factory.commentClient();

        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builderFor(adapter).build();

        return proxyFactory.createClient(ModerationCommentClient.class);
    }

}
