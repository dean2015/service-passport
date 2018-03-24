package cn.devcenter.framework.passport.login.token.sdk.fallback;

import cn.devcenter.framework.passport.login.token.sdk.LoginTokenService;
import cn.devcenter.model.token.AccessToken;
import cn.housecenter.dlfc.framework.boot.stereotype.Service;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Slf4j
public class LoginTokenServiceFallback implements LoginTokenService {

    @RequestMapping(value = "/register/user/fallback", method = RequestMethod.POST)
    @Override
    public AjaxResult<String> registerUserIdentity(@RequestBody AccessToken accessToken) {
        log.warn("method [registerUserIdentity(userIdentityDTO)] is not available.");
        return AjaxResult.newInstance(String.class).fail("Token服务不可用");
    }

    @RequestMapping(value = "/get/token/fallback", method = RequestMethod.GET)
    @Override
    public AjaxResult<AccessToken> getToken(@RequestParam("token") String token) {
        log.warn("method [getToken(accessToken)] is not available.");
        return AjaxResult.newInstance(AccessToken.class).fail("Token服务不可用");
    }
}
