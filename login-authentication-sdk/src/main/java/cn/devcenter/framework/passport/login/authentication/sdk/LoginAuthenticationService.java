package cn.devcenter.framework.passport.login.authentication.sdk;

import cn.devcenter.framework.passport.login.authentication.sdk.fallback.LoginAuthenticationServiceFallback;
import cn.devcenter.model.authentication.Authentication;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "authentication-service", fallback = LoginAuthenticationServiceFallback.class)
@RequestMapping(value = "/authentication")
public interface LoginAuthenticationService {

    @RequestMapping(value = "/authenticate")
    AjaxResult<Authentication> authenticate(@RequestParam("id") String id, @RequestParam("secret") String secret);

    @RequestMapping(value = "/authenticate-quick")
    AjaxResult<Authentication> authenticate(@RequestParam("id") String id);
}
