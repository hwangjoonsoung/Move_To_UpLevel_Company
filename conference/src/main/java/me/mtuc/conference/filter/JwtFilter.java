package me.mtuc.conference.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.mtuc.conference.util.JwtProvider;
import org.springframework.stereotype.Component;
import org.springframework.transaction.reactive.TransactionalOperatorExtensionsKt;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        String uri = request.getRequestURI();
        System.out.println("uri = " + uri);

        // todo: filter check 없이 통과
        if(uri.startsWith("/auth")){
            filterChain.doFilter(request, response);
        }

        if(token != null && jwtProvider.validateToken(token)){
            String subject = jwtProvider.getSubjetFromToken(token);
        }

    }

    private String resolveToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        String result = "";
        if (authorization != null){
            result = authorization.startsWith("Bearer ") ? authorization.substring(7) : "";
        }
        return result;
    }
}
