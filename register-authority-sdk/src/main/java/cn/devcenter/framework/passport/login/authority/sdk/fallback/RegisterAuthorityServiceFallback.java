package cn.devcenter.framework.passport.login.authority.sdk.fallback;

import cn.devcenter.framework.authority.model.AuthenticationRoleDTO;
import cn.devcenter.framework.passport.login.authority.sdk.RegisterAuthorityService;
import cn.housecenter.dlfc.framework.boot.stereotype.Service;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Service
@Slf4j
public class RegisterAuthorityServiceFallback implements RegisterAuthorityService {

    @RequestMapping(value = "/bind/fallback", method = RequestMethod.POST)
    @Override
    public AjaxResult<Void> bind(AuthenticationRoleDTO authenticationRoleDTO) {
        log.warn("method [bind(authenticationRoleDTO)] is not available.");
        return AjaxResult.newInstance(Void.class).fail("授权服务不可用");
    }
}
