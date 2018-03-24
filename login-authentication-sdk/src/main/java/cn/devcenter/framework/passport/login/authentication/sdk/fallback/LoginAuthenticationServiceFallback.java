package cn.devcenter.framework.passport.login.authentication.sdk.fallback;

import cn.devcenter.framework.passport.login.authentication.sdk.LoginAuthenticationService;
import cn.devcenter.model.authentication.Authentication;
import cn.housecenter.dlfc.framework.boot.stereotype.Service;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@Slf4j
public class LoginAuthenticationServiceFallback implements LoginAuthenticationService {

    @RequestMapping(value = "/authenticate/fallback")
    @Override
    public AjaxResult<Authentication> authenticate(String id, String secret) {
        log.warn("method [authenticate(id, secret)] is not available.");
        Authentication authentication = new Authentication();
        authentication.setId(id);
        authentication.setSecret(secret);
        return AjaxResult.newInstance(Authentication.class).success("认证服务不可用", authentication);
    }

    @RequestMapping(value = "/authenticate-quick/fallback")
    @Override
    public AjaxResult<Authentication> authenticate(String id) {
        log.warn("method [authenticate(id)] is not available.");
        Authentication authentication = new Authentication();
        authentication.setId(id);
        return AjaxResult.newInstance(Authentication.class).success("认证服务不可用", authentication);
    }
}
