package com.example.demo;

import javax.servlet.Filter;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PostCommentRateLimit implements Filter
{
    RateLimiter rateLimiter;

    public PostCommentRateLimit(RateLimiter rateLimiter) {
        this.rateLimiter = rateLimiter;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (!req.getMethod().equals("POST")) {
            chain.doFilter(request, response);
            return;
        }

        int userId = 1; // Get User
        boolean valid = rateLimiter.validate(String.format("ratelimit.user.comments:%d", userId), 2);
        if(!valid) {
            ((HttpServletResponse) response).setStatus(429);
            response.getOutputStream().write("Too Many Requests".getBytes());
            return;
        }
        

        chain.doFilter(request, response);
    }
}
