package cn.devcenter.framework.passport.login.authority.sdk;

import cn.devcenter.framework.authority.model.AuthenticationRoleDTO;
import cn.devcenter.framework.passport.login.authority.sdk.fallback.RegisterAuthorityServiceFallback;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@FeignClient(value = "authority-service", fallback = RegisterAuthorityServiceFallback.class)
@RequestMapping("/authentication-role")
public interface RegisterAuthorityService {

    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    AjaxResult<Void> bind(@RequestBody @Valid AuthenticationRoleDTO authenticationRoleDTO);
}
