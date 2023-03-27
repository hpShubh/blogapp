package com.blog.Security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenHealper JwtTokenHealper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestToken = request.getHeader("Authorization");
		System.out.println(requestToken);

		String username = null;
		String token = null;

		if (request != null && requestToken.startsWith("Bearer")) {

			token = requestToken.substring(7);
			try {
				username = this.JwtTokenHealper.getUsernameFromToken(token);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get Jwt Token ");
			} catch (ExpiredJwtException e) {
				System.out.println("JWt token is Expired !!");

			} catch (MalformedJwtException e) {
				System.out.println("invalid Expired !!");
			}

			
			
		} else

		{
			System.out.println("jwt token does  not begin with the beare ");
		}
		
		// valided
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
			if(this.JwtTokenHealper.validateToken(token, userDetails){
				
				UsernamePasswordAuthenticationToken usernamepasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(token, userDetails);
				
			}else {
				System.out.println("invailid token");
			}
			
		}
		

	}

}
