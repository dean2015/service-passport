package cn.devcenter.framework.passport.login.user.sdk;

import cn.devcenter.framework.passport.login.user.sdk.fallback.LoginUserServiceFallback;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

@FeignClient(value = "login-user-service", fallback = LoginUserServiceFallback.class)
@RequestMapping(value = "/user")
public interface LoginUserService {

    @RequestMapping(value = "/get/id", method = RequestMethod.GET)
    AjaxResult<String> getId(@RequestParam("username") Serializable username);


}
