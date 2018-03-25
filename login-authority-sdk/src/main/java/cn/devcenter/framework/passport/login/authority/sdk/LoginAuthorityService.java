package cn.devcenter.framework.passport.login.authority.sdk;

import cn.devcenter.framework.passport.login.authority.sdk.fallback.LoginAuthorityServiceFallback;
import cn.devcenter.model.authority.Role;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "authority-service", fallback = LoginAuthorityServiceFallback.class)
@RequestMapping(value = "/authentication-role")
public interface LoginAuthorityService {

    @RequestMapping(value = "/find-role-by-authencationid", method = RequestMethod.GET)
    public AjaxResult<List<Role>> findRoleByAuthenticationId(@RequestParam("authenticationId") String authenticationId, @RequestParam("page") int page, @RequestParam("size") int size);

}
