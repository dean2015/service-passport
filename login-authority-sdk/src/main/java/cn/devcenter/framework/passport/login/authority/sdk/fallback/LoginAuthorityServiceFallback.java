package cn.devcenter.framework.passport.login.authority.sdk.fallback;

import cn.devcenter.framework.passport.login.authority.sdk.LoginAuthorityService;
import cn.devcenter.model.authority.Role;
import cn.housecenter.dlfc.framework.boot.stereotype.Service;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@Slf4j
public class LoginAuthorityServiceFallback implements LoginAuthorityService {

    @RequestMapping(value = "/find-by-authencationid/fallback")
    @Override
    public AjaxResult<List<Role>> findRoleByAuthenticationId(String authenticationId, int page, int size) {
        log.warn("method [getRoleIds(authenticationId)] is not available.");
        AjaxResult<List<Role>> result = new AjaxResult<>();
        return result.fail("授权服务不可用");
    }

}
