//package com.green.onezo.conf;
//
//import com.green.onezo.enum_column.Role;
//import com.green.onezo.jwt.JwtTokenManager;
//import com.green.onezo.member.Member;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jws;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.util.List;
//import java.util.stream.Stream;
//
//@Component
//@RequiredArgsConstructor
////JwtInterceptor-특정 리소스에 액세스 권한 확인
//public class JWTInterceptor implements HandlerInterceptor {
//    private final JwtTokenManager jwtTokenManager;
//
//    @Override
//    //컨트롤러가 요청 처리 전에 수행, false반환시 진행 중단
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws Exception{
//                String token=request.getHeader("Authorization");
//        System.out.println(request.getRequestURI());
//            //이 요청에 대해서 검증 생략하고 바로 허용
//        if (
//                request.getRequestURI().contains("swagger")
////                || request.getRequestURI().contains("file")
////                || request.getRequestURI().contains("error")
//                || request.getRequestURI().contains("common")
//                || request.getRequestURI().contains("main")
//        ){
//            return true;
//        }
//
//        //Token이 없거나 Bearer로 시작하지 않는다면 "token이 없습니다." 출력
//        if (token==null || !token.startsWith("Bearer ")){
//            System.out.println("token이 없습니다.");
//            throw new RuntimeException("TOKEN 없다");
//        }
//        //토큰이 존재하는 경우
//        else {
//            try{
//                //jws객체 이용해서 사용자 인증 수행
//                Jws<Claims> jws=jwtTokenManager.validateAccesstoken(token.substring("Bearer ".length()));
//                List<SimpleGrantedAuthority> roles=
//                        Stream.of(jws.getBody().get("role").toString())
//                                .map(SimpleGrantedAuthority::new)
//                                .toList();
//                System.out.println(roles);
//
//                //로그인한 사람 정보를 Authentication에 저장해라
//                Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(
//                        Member.builder()
//                                .userId(jws.getPayload().get("userId").toString())
//                                .nickname(jws.getPayload().get("nickname").toString())
//                                .phone(jws.getPayload().get("phone").toString())
//                                .role(Role.fromString("USER"))
//                                .build(),
//                        null,
//                        roles
//                );
//                //로그인한 사람 정보 저장
//
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            } catch (ExpiredJwtException e) {
//                System.out.println("토큰 만료");
//                throw new RuntimeException("JWT 토큰 만료");
//            } catch (Exception e) {
//                System.out.println("토큰 검증 실패");
//                throw new RuntimeException("JWT 토큰 검증 실패");
//            }
//            return true;
//        }
//    }
//}
