package cn.devcenter.framework.passport.login.controller;

import cn.devcenter.framework.passport.login.authentication.sdk.LoginAuthenticationService;
import cn.devcenter.framework.passport.login.authority.sdk.LoginAuthorityService;
import cn.devcenter.framework.passport.login.model.LoginInfoDTO;
import cn.devcenter.framework.passport.login.token.sdk.LoginTokenService;
import cn.devcenter.framework.passport.login.user.sdk.LoginUserService;
import cn.devcenter.framework.session.token.filter.SessionTokenHolder;
import cn.devcenter.model.authentication.Authentication;
import cn.devcenter.model.authority.Role;
import cn.devcenter.model.token.AccessToken;
import cn.devcenter.model.token.UserIdentity;
import cn.housecenter.dlfc.framework.starter.web.annotation.RestController;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginAuthenticationService authenticationService;

    private final LoginUserService loginUserService;

    private final LoginAuthorityService loginAuthorityService;

    private final LoginTokenService loginTokenService;

    @Autowired
    public LoginController(LoginAuthenticationService authenticationService, LoginUserService loginUserService, LoginAuthorityService loginAuthorityService, LoginTokenService loginTokenService) {
        this.authenticationService = authenticationService;
        this.loginUserService = loginUserService;
        this.loginAuthorityService = loginAuthorityService;
        this.loginTokenService = loginTokenService;
    }

    @RequestMapping(value = "/do", method = RequestMethod.POST)
    public AjaxResult<String> doLogin(@RequestBody @Valid LoginInfoDTO loginInfoDTO) {
        AjaxResult<String> resultOfUserId = loginUserService.getId(loginInfoDTO.getUsername());
        if (!resultOfUserId.successful()) {
            return AjaxResult.newInstance(String.class).fail("用户不存在");
        }
        AjaxResult<Authentication> resultAuthentication = authenticationService.authenticate(resultOfUserId.getData(), loginInfoDTO.getPassword());
        if (!resultAuthentication.successful()) {
            return AjaxResult.newInstance(String.class).fail("密码错误");
        }
        AjaxResult<AccessToken> accessTokenResult = loginTokenService.getToken(SessionTokenHolder.getToken().getAccessToken());
        if (!accessTokenResult.successful()) {
            return AjaxResult.newInstance(String.class).fail("访问客户端变动");
        }
        AccessToken accessToken = accessTokenResult.getData();
        if (accessToken.getUserIdentity() == null) {
            accessToken.setUserIdentity(new UserIdentity());
        }
        accessToken.getUserIdentity().setAuthenticationId(resultOfUserId.getData());
        AjaxResult<List<Role>> resultRoles = loginAuthorityService.getRoleIds(resultOfUserId.getData());
        if (resultRoles.successful()) {
            final List<String> roles = new ArrayList<>();
            resultRoles.getData().forEach(role -> roles.add(role.getId()));
            accessToken.getUserIdentity().setRoles(roles);
        }
        return loginTokenService.registerUserIdentity(accessToken);
    }

    @RequestMapping(value = "/quick", method = RequestMethod.POST)
    public AjaxResult<String> doQuickLogin(@RequestBody @Valid LoginInfoDTO loginInfoDTO) {
        AjaxResult<String> resultOfUserId = loginUserService.getId(loginInfoDTO.getUsername());
        if (!resultOfUserId.successful()) {
            return AjaxResult.newInstance(String.class).fail("用户不存在");
        }
        AjaxResult<Authentication> resultAuthentication = authenticationService.authenticate(resultOfUserId.getData());
        if (!resultAuthentication.successful()) {
            return AjaxResult.newInstance(String.class).fail("密码错误");
        }
        AjaxResult<AccessToken> accessTokenResult = loginTokenService.getToken(SessionTokenHolder.getToken().getAccessToken());
        if (!accessTokenResult.successful()) {
            return AjaxResult.newInstance(String.class).fail("访问客户端变动");
        }
        AccessToken accessToken = accessTokenResult.getData();
        if (accessToken.getUserIdentity() == null) {
            accessToken.setUserIdentity(new UserIdentity());
        }
        accessToken.getUserIdentity().setAuthenticationId(resultOfUserId.getData());
        AjaxResult<List<Role>> resultRoles = loginAuthorityService.getRoleIds(resultOfUserId.getData());
        if (resultRoles.successful()) {
            final List<String> roles = new ArrayList<>();
            resultRoles.getData().forEach(role -> roles.add(role.getId()));
            accessToken.getUserIdentity().setRoles(roles);
        }
        return loginTokenService.registerUserIdentity(accessToken);
    }


}
