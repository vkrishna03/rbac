package com.example.rbac.security.filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.rbac.security.jwt.JwtUtil;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String token = request.getHeader("Authorization");
		
		if (token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
			try {
				Claims claims = jwtUtil.getClaimsFromToken(token);
				request.setAttribute("claims", claims);
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				e.printStackTrace();
				return;
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
