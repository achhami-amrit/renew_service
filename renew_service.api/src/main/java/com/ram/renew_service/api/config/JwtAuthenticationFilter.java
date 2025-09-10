package com.ram.renew_service.api.config;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ram.renew_service.entity.user.Users;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider tokenProvider;

 
  
   
    
   

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    	HeaderMapRequestWrapper wrappedRequest = new HeaderMapRequestWrapper((HttpServletRequest)request);
        try {
            String jwt = getJwtFromRequest(request);
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                Long userId = tokenProvider.getUserIdFromJWT(jwt);

                Users userDetails = new Users();
                userDetails.setUserId(userId.intValue());
                //Users userDetails = userRepo.findOne(userId.intValue());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                

                SecurityContextHolder.getContext().setAuthentication(authentication);
                
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(wrappedRequest, response);
    }
    
  
  


    private String getJwtFromRequest(HttpServletRequest request) {
    	
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer:")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
 
    
    


class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
    public HeaderMapRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    private Map<String, String> headerMap = new HashMap<String, String>();

    public void addHeader(String name, String value) {
        headerMap.put(name, value);
    }

    public void removeteHeader(String name) {
        headerMap.remove(name);
    }

    @Override
    public String getHeader(String name) {
        String headerValue = super.getHeader(name);
        if (headerMap.containsKey(name)) {
            headerValue = headerMap.get(name);
        }
        return headerValue;
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        for (String name : headerMap.keySet()) {
            names.add(name);
        }
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values = Collections.list(super.getHeaders(name));
        if (headerMap.containsKey(name)) {
            values.add(headerMap.get(name));
        }
        return Collections.enumeration(values);
    }
}
    
}
