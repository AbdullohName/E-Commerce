package Infinity.Intex.security.jwt;

import Infinity.Intex.redis.AdminSession;
import Infinity.Intex.redis.AdminSessionRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final AdminSessionRedisRepository adminRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)){
                String id = String.valueOf(jwtUtil.getClaim(token, "sub"));
                if (id != null){
                    Optional<AdminSession> adminSessionOptional = adminRepository.findById(id);
                    adminSessionOptional.ifPresent(adminSession -> {

                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(
                                        adminSession.getAdmin(), token, (Collection<? extends GrantedAuthority>) adminSession.getAdmin().getAuthorities());

                        // This object has requestAddress and sessionId
                        WebAuthenticationDetails details = new WebAuthenticationDetails(request);
                        authentication.setDetails(details);

                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    });
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
