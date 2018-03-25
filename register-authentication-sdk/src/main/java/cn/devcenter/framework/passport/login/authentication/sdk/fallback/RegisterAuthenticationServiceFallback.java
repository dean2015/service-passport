package cn.devcenter.framework.passport.login.authentication.sdk.fallback;

import cn.devcenter.framework.passport.login.authentication.sdk.RegisterAuthenticationService;
import cn.devcenter.model.authentication.Authentication;
import cn.housecenter.dlfc.framework.boot.stereotype.Service;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@Slf4j
public class RegisterAuthenticationServiceFallback implements RegisterAuthenticationService {

    @RequestMapping(value = "/register/fallback", method = RequestMethod.POST)
    @Override
    public AjaxResult<Authentication> register(@RequestBody Authentication authentication) {
        log.warn("method [authenticate(id, secret)] is not available.");
        return AjaxResult.newInstance(Authentication.class).fail("认证服务不可用");
    }
}
