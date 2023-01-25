package com.blog.blogapplication.Security;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blog.blogapplication.services.UserService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTTokenHelper jwtTokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        String requestToken = request.getHeader("Authorization");
        System.out.println("REQEUST TOKEN " + requestToken);

        String username = null;
        String token = null;

        if (requestToken != null && requestToken.startsWith("Bearer")) {
            System.out.println("IN IF");
            token = requestToken.substring(7);
            System.out.println("TOKEN " + token);
            try {
                username = this.jwtTokenHelper.getUsernameFromToken(token);
                System.out.println("DEBUG USERNAME " + username);

            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT token");

            } catch (ExpiredJwtException e) {
                System.out.println("Token Expired ");
            }
            // catch(MalformedInputException e){
            // System.out.println("invalid jwt");
            // }
        } else {
            System.out.println("TOken not begin with Bearer");
        }

        // ValidateToken

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (this.jwtTokenHelper.validToken(token, userDetails)) {
                // Authentication part

                UsernamePasswordAuthenticationToken UsernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                UsernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationToken);
            } else {
                System.out.println("Invalid JWT Token");
            }
        } else {
            System.out.println("Invalid user");
        }

        filterChain.doFilter(request, response);
    }

}
