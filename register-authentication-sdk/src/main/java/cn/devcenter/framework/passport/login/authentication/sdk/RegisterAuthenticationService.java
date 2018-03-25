package cn.devcenter.framework.passport.login.authentication.sdk;

import cn.devcenter.framework.passport.login.authentication.sdk.fallback.RegisterAuthenticationServiceFallback;
import cn.devcenter.model.authentication.Authentication;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "authentication-service", fallback = RegisterAuthenticationServiceFallback.class)
@RequestMapping(value = "/authentication")
public interface RegisterAuthenticationService {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    AjaxResult<Authentication> register(@RequestBody Authentication authentication);

}
