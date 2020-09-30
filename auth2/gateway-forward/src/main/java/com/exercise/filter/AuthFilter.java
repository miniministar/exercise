package com.exercise.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.exercise.model.UserDTO;
import com.exercise.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class AuthFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        Object principal = authentication.getPrincipal();
//
//        Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
//        log.info("details:{}", details);
        String token = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (!StringUtils.isEmpty(token)) {
            //从token中解析用户信息并设置到Header中去
            String realToken = token.replace("Bearer ", "");
            org.springframework.security.jwt.Jwt jwt = JwtHelper.decode(realToken);
            String claims = jwt.getClaims();
            Map<String, Object> map = (Map<String, Object>) JSON.parse(claims);
            //取出用户身份信息
            String userStr = (String) map.get("user_name");
            JSONArray authoritiesArray = (JSONArray) map.get("authorities");

            log.info("AuthFilter.filter() user:{}",userStr);
            String[] authorities = authoritiesArray.toArray(new String[authoritiesArray.size()]);
            Map<String,Object> jsonToken = new HashMap<>();
            jsonToken.put("principal",userStr);
            jsonToken.put("authorities",authorities);

            //把身份信息和权限信息放在json中，加入http的header中,转发给微服务
            ServerHttpRequest host = exchange.getRequest().mutate().header("json-token", EncryptUtil.encodeUTF8StringBase64(JSON.toJSONString(jsonToken))).build();
            ServerWebExchange build = exchange.mutate().request(host).build();
            return chain.filter(build);
        }


        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return -500;
    }

    protected class JwtUtil implements JwtDecoder {
        @Override
        public Jwt decode(String s) throws JwtException {
            return null;
        }
    }
}
