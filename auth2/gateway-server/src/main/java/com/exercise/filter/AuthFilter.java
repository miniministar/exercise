package com.exercise.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collection;

@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Object principal = authentication.getPrincipal();

        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
        log.info("details:{}", details);

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
//        if (StrUtil.isEmpty(token)) {
//            return chain.filter(exchange);
//        }
//        try {
//            //从token中解析用户信息并设置到Header中去
//            String realToken = token.replace("Bearer ", "");
//            JWSObject jwsObject = JWSObject.parse(realToken);
//            String userStr = jwsObject.getPayload().toString();
//            LOGGER.info("AuthGlobalFilter.filter() user:{}",userStr);
//            ServerHttpRequest request = exchange.getRequest().mutate().header("user", userStr).build();
//            exchange = exchange.mutate().request(request).build();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return -500;
    }
}
