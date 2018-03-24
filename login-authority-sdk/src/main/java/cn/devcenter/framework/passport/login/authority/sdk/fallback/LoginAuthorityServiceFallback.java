package cn.devcenter.framework.passport.login.authority.sdk.fallback;

import cn.devcenter.framework.passport.login.authority.sdk.LoginAuthorityService;
import cn.devcenter.model.authority.Role;
import cn.housecenter.dlfc.framework.boot.stereotype.Service;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LoginAuthorityServiceFallback implements LoginAuthorityService {

    @RequestMapping(value = "/find-by-authencationid/fallback")
    @Override
    public AjaxResult<List<Role>> getRoleIds(@RequestParam("authenticationId") String authenticationId) {
        log.warn("method [getRoleIds(authenticationId)] is not available.");
        AjaxResult<List<Role>> result = new AjaxResult<>();
        List<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setId("1");
        role.setName("ADMIN");
        roles.add(role);
        return result.success("授权服务不可用", roles);
    }

}
