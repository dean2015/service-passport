package cn.devcenter.framework.passport.login.authority.sdk;

import cn.devcenter.framework.passport.login.authority.sdk.fallback.LoginAuthorityServiceFallback;
import cn.devcenter.model.authority.Role;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "authority-service", fallback = LoginAuthorityServiceFallback.class)
@RequestMapping(value = "/role")
public interface LoginAuthorityService {

    @RequestMapping(value = "/find-by-authencationid")
    AjaxResult<List<Role>> getRoleIds(@RequestParam("authenticationId") String authenticationId);


}
