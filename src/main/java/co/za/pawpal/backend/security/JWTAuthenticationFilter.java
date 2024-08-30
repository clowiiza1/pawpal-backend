package co.za.pawpal.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
   private JWTGenerator jwtGenerator;

    @Autowired
   private CustomUserDetailsService customUserDetailsService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.startsWith("/api/auth/register") || path.startsWith("/api/auth/login")) {
            filterChain.doFilter(request, response);
            return; // Exit the method to avoid further processing for these paths
        }

       String token = getJWTFromRequest(request);
       if(StringUtils.hasText(token) && jwtGenerator.validateToken(token)) {
           String username = jwtGenerator.getUsernameFromJWT(token);

           UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
           UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
           authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
           SecurityContextHolder.getContext().setAuthentication(authentication);

       }
       filterChain.doFilter(request, response);

    }

    private String getJWTFromRequest(HttpServletRequest request) {
       String bearerToken = request.getHeader("Authorization");
       if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
           return bearerToken.substring(7, bearerToken.length());
       }
       return null;
    }
}
