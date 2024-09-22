package com.example.demo.config;

import com.example.demo.user.entity.User;
import com.example.demo.user.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
@Component
@Order(2)
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final HttpServletResponse httpServletResponse;

    public AuthFilter(UserRepository userRepository, JwtUtil jwtUtil, HttpServletResponse httpServletResponse) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.httpServletResponse = httpServletResponse;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) &&
                (url.startsWith("/users/login") || url.startsWith("/users/member"))
        ) {
            // 회원가입, 로그인 관련 API 는 인증 필요없이 요청 진행
            chain.doFilter(request, response); // 다음 Filter 로 이동
        } else {
            // 나머지 API 요청은 인증 처리 진행
            // 토큰 확인
            String tokenValue = httpServletRequest.getHeader("Authorization");

            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Token Error");
                }

                // 토큰에서 사용자 정보 가져오기
                Claims info = jwtUtil.getUserInfoFromToken(token);

                UserRoleEnum role = UserRoleEnum.valueOf(info.get("role", String.class));
                log.info(role.getAuthority());
                if (url.startsWith("/schedules/") &&
                        (httpServletRequest.getMethod().equals("PUT") || httpServletRequest.getMethod().equals("DELETE"))) {
                    // 일정 수정/삭제 요청에 대해서는 관리자 권한만 허용
                    if (!role.equals(UserRoleEnum.ADMIN)) {
                        httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN,"관리자가 아니라 접근할 수 없습니다.");
                        return;
                    }
                }


                User user = userRepository.findById(Long.valueOf(info.getSubject())).orElseThrow(() ->
                        new NullPointerException("Not Found User")
                );

                request.setAttribute("user", user);
                chain.doFilter(request, response); // 다음 Filter 로 이동
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }

}