package cn.devcenter.framework.authentication.config;

import cn.devcenter.framework.authentication.service.impl.AppDAOImpl;
import cn.devcenter.framework.authentication.service.impl.AuthenticationDAOImpl;
import cn.devcenter.model.authentication.dao.AppDAO;
import cn.devcenter.model.authentication.dao.AuthenticationDAO;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {

    @Bean
    public AppDAO getAppDAO() {
        return new AppDAOImpl();
    }

    @Bean
    public AuthenticationDAO getAuthenticationDAO() {
        return new AuthenticationDAOImpl();
    }

}
