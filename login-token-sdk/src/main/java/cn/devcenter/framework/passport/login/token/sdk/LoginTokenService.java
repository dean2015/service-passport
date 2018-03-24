package cn.devcenter.framework.passport.login.token.sdk;

import cn.devcenter.framework.passport.login.token.sdk.fallback.LoginTokenServiceFallback;
import cn.devcenter.model.token.AccessToken;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "token-service", fallback = LoginTokenServiceFallback.class)
@RequestMapping("/token")
public interface LoginTokenService {

    @RequestMapping(value = "/register/user", method = RequestMethod.POST)
    AjaxResult<String> registerUserIdentity(@RequestBody AccessToken accessToken);

    @RequestMapping(value = "/get/token", method = RequestMethod.GET)
    AjaxResult<AccessToken> getToken(@RequestParam("token") String token);

}
