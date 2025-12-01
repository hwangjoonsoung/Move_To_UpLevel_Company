package me.mtuc.conference.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import me.mtuc.conference.config.CustomUserDetailService;
import me.mtuc.conference.util.JwtProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = resolveToken(request);
        String uri = request.getRequestURI();

        // todo: filter check 없이 통과
        if(uri.startsWith("/auth") || uri.startsWith("/user")){
            filterChain.doFilter(request, response);
            return;
        }

        if(token != null && jwtProvider.validateToken(token)){
            String userId = jwtProvider.getSubjetFromToken(token);// user pk
            UserDetails userDetails = customUserDetailService.loadUserById(Long.parseLong(userId));
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request){
        String refreshToken = request.getHeader("Cookie");
        String result = "";
        if (refreshToken != null){
            result = refreshToken.startsWith("Bearer ") ? refreshToken.substring(7) : refreshToken;
        }
        if(refreshToken != null && refreshToken.startsWith("refresh")){
            result = result.replace("refreshToken=","");
        }
        return result;
    }
}
