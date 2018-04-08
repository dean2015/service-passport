package cn.devcenter.framework.passport.login.controller;

import cn.devcenter.framework.passport.login.authentication.sdk.LoginAuthenticationService;
import cn.devcenter.framework.passport.login.authority.sdk.LoginAuthorityService;
import cn.devcenter.framework.passport.login.model.LoginInfoDTO;
import cn.devcenter.framework.passport.login.model.LoginResponseDTO;
import cn.devcenter.framework.passport.login.token.sdk.LoginTokenService;
import cn.devcenter.framework.session.token.filter.SessionTokenHolder;
import cn.devcenter.model.result.AjaxResult;
import cn.devcenter.model.token.AccessToken;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jetty.server.UserIdentity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final LoginAuthenticationService authenticationService;

    private final LoginAuthorityService loginAuthorityService;

    private final LoginTokenService loginTokenService;

    @Autowired
    public LoginController(LoginAuthenticationService authenticationService, LoginAuthorityService loginAuthorityService, LoginTokenService loginTokenService) {
        this.authenticationService = authenticationService;
        this.loginAuthorityService = loginAuthorityService;
        this.loginTokenService = loginTokenService;
    }

    private AjaxResult<LoginResponseDTO> afterAuthentication(LoginInfoDTO loginInfoDTO) {
        AjaxResult<AccessToken> accessTokenResult = loginTokenService.getToken(SessionTokenHolder.getToken().getAccessToken());
        if (!accessTokenResult.successful()) {
            return AjaxResult.newInstance(LoginResponseDTO.class).fail("认证请求错误");
        }
        AccessToken accessToken = accessTokenResult.getData();
        if (accessToken.getUserIdentity() == null) {
            accessToken.setUserIdentity(new UserIdentity());
        }
        accessToken.getUserIdentity().setAuthenticationId(loginInfoDTO.getAuthenticationId());
        AjaxResult<Page<Role>> resultRoles = loginAuthorityService.findRoleByAuthenticationId(loginInfoDTO.getAuthenticationId(), 1, Integer.MAX_VALUE);
        if (resultRoles.successful()) {
            final List<String> roles = new ArrayList<>();
            resultRoles.getData().getContent().forEach(role -> roles.add(role.getId()));
            accessToken.getUserIdentity().setRoles(roles);
        }
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        if (null != accessToken.getUserIdentity().getRoles()) {
            loginResponseDTO.setRoles(accessToken.getUserIdentity().getRoles().toArray());
        }
        if (StringUtils.isBlank(accessToken.getUserIdentity().getUserIdentifier())) {
            AjaxResult<String> userIdentifierResult = loginTokenService.registerUserIdentity(accessToken);
            if (!userIdentifierResult.successful()) {
                return AjaxResult.newInstance(LoginResponseDTO.class).fail("生成用户标识错误");
            }
            loginResponseDTO.setUserIdentifier(userIdentifierResult.getData());
        } else {
            loginResponseDTO.setUserIdentifier(accessToken.getUserIdentity().getUserIdentifier());
        }
        return AjaxResult.newInstance(LoginResponseDTO.class).success("登录成功", loginResponseDTO);
    }

    @RequestMapping(value = "/do", method = RequestMethod.POST)
    public AjaxResult<LoginResponseDTO> doLogin(@RequestBody @Valid LoginInfoDTO loginInfoDTO) {
        AjaxResult<Authentication> resultAuthentication = authenticationService.authenticate(loginInfoDTO.getAuthenticationId(), loginInfoDTO.getSecret());
        if (!resultAuthentication.successful()) {
            return AjaxResult.newInstance(LoginResponseDTO.class).fail("认证失败");
        }
        return afterAuthentication(loginInfoDTO);
    }

    @RequestMapping(value = "/quick", method = RequestMethod.POST)
    public AjaxResult<LoginResponseDTO> doQuickLogin(@RequestBody @Valid LoginInfoDTO loginInfoDTO) {
        AjaxResult<Authentication> resultAuthentication = authenticationService.authenticate(loginInfoDTO.getAuthenticationId());
        if (!resultAuthentication.successful()) {
            return AjaxResult.newInstance(LoginResponseDTO.class).fail("认证失败");
        }
        return afterAuthentication(loginInfoDTO);
    }


}
