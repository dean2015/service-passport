package cn.devcenter.framework.passport.oauth2.config;

import cn.devcenter.model.authentication.api.AppApi;
import cn.devcenter.model.authentication.api.AuthenticationApi;
import cn.devcenter.model.authentication.api.impl.DefaultAppApi;
import cn.devcenter.model.authentication.api.impl.DefaultAuthenticationApi;
import cn.devcenter.model.authentication.dao.AppDAO;
import cn.devcenter.model.authentication.dao.AuthenticationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticationConfig {

    @Bean
    @ConditionalOnMissingBean(AuthenticationApi.class)
    public AuthenticationApi getAuthenticationApi(@Autowired AuthenticationDAO authenticationDAO) {
        return new DefaultAuthenticationApi(authenticationDAO);
    }

    @Bean
    @ConditionalOnMissingBean(AppApi.class)
    public AppApi getAppApi(@Autowired AppDAO appDAO) {
        return new DefaultAppApi(appDAO);
    }

}
