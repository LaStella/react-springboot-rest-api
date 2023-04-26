package com.cnu.coffee.security;

import com.cnu.coffee.user.RequestLoginDto;
import com.cnu.coffee.user.UserDto;
import com.cnu.coffee.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private UserService userService;
    private Environment environment;

    public AuthenticationFilter(UserService userService, Environment environment) {
        this.userService = userService;
        this.environment = environment;
    }

    // 요청 정보를 보냈을 때 처리하는 메소드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            // 전달된 입력값을 원하는 자바 클래스 타입으로 변환합니다.
            // 전달한 값이 POST방식으로 전달되므로 request parameter를 사용할 수 없습니다.
            // InputStream으로 받아 이를 수작업으로 처리할 수 있습니다.
            RequestLoginDto creds = new ObjectMapper().readValue(request.getInputStream(), RequestLoginDto.class);

            // 이메일, 패스워드, 어떤 권한을 가질 것인지에 대한 정보를 인자로 전달합니다.
            // spring security에서 사용하기위한 UsernamePasswordAuthenticationToken으로 변환합니다.
            // 변환한 토큰을 인증을 처리해주는 매니저에게 전달하여 아이디와 패스워드를 비교합니다.
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 로그인이 성공했을 때 원하는 작업을 하는 메소드입니다.
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        String username = ((User) authResult.getPrincipal()).getUsername();
        UserDto userDetails = userService.getUserDetailsByEmail(username);

        String token = Jwts.builder().setSubject(userDetails.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS256, environment.getProperty("token.secret"))
                .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDetails.getUserId());
    }
}
