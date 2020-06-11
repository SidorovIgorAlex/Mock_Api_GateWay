package ru.vtb.mobilebanking.is.nt.coreapigatewaymock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@Component
public class CustomRequestLogginFilter extends GenericFilterBean {

    Logger logger = LoggerFactory.getLogger(CustomRequestLogginFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest currentRequest = (HttpServletRequest) servletRequest;
        final HttpServletResponse currentResponse = (HttpServletResponse) servletResponse;
        StringBuffer requestURL = currentRequest.getRequestURL();
        logger.info("Request URL: {}", requestURL);
        try {
            chain.doFilter(currentRequest, servletResponse);
        } finally {
            int status = currentResponse.getStatus();
            logger.info("Response status: {}", status);
        }
    }
}
