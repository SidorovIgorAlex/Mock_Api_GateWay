package ru.vtb.mobilebanking.is.nt.coreapigatewaymock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoginRequestFilter {

    @Bean
    public CommonsRequestLoggingFilter loggingFilter()
    {
        CommonsRequestLoggingFilter commonsRequestLoggingFilter
                                = new CommonsRequestLoggingFilter();
        commonsRequestLoggingFilter.setIncludeQueryString(true);
        commonsRequestLoggingFilter.setIncludePayload(true);
        commonsRequestLoggingFilter.setMaxPayloadLength(10000);
        commonsRequestLoggingFilter.setIncludeHeaders(true);
        commonsRequestLoggingFilter.setAfterMessagePrefix("REQUEST DATA : ");
        return commonsRequestLoggingFilter;
    }

}
