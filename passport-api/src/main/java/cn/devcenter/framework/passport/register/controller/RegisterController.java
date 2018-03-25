package cn.devcenter.framework.passport.register.controller;


import cn.devcenter.framework.authority.model.AuthenticationRoleDTO;
import cn.devcenter.framework.passport.login.authentication.sdk.RegisterAuthenticationService;
import cn.devcenter.framework.passport.login.authority.sdk.LoginAuthorityService;
import cn.devcenter.framework.passport.login.authority.sdk.RegisterAuthorityService;
import cn.devcenter.framework.passport.login.model.LoginResponseDTO;
import cn.devcenter.framework.passport.login.token.sdk.LoginTokenService;
import cn.devcenter.framework.passport.register.model.RegisterInfoDTO;
import cn.devcenter.framework.session.token.filter.SessionTokenHolder;
import cn.devcenter.model.authentication.Authentication;
import cn.devcenter.model.token.AccessToken;
import cn.devcenter.model.token.UserIdentity;
import cn.housecenter.dlfc.framework.starter.web.annotation.RestController;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/register")
@Slf4j
public class RegisterController {

    private final RegisterAuthenticationService registerAuthenticationService;

    private final RegisterAuthorityService registerAuthorityService;

    private final LoginTokenService loginTokenService;

    private final LoginAuthorityService loginAuthorityService;

    @Autowired
    public RegisterController(RegisterAuthenticationService registerAuthenticationService, RegisterAuthorityService registerAuthorityService, LoginTokenService loginTokenService, LoginAuthorityService loginAuthorityService) {
        this.registerAuthenticationService = registerAuthenticationService;
        this.registerAuthorityService = registerAuthorityService;
        this.loginTokenService = loginTokenService;
        this.loginAuthorityService = loginAuthorityService;
    }

    @RequestMapping(value = "/do", method = RequestMethod.POST)
    public AjaxResult<LoginResponseDTO> register(@RequestBody RegisterInfoDTO registerInfoDTO) {
        Authentication authentication = registerInfoDTO.getAuthentication();
        AjaxResult<Authentication> registerResult = registerAuthenticationService.register(authentication);
        if (!registerResult.successful()) {
            log.error("Authentication information register failed.");
            return AjaxResult.newInstance(LoginResponseDTO.class).fail("注册认证信息错误：" + registerResult.getMessage());
        }

        AuthenticationRoleDTO authenticationRoleDTO = new AuthenticationRoleDTO();
        authenticationRoleDTO.setAuthenticationId(registerResult.getData().getId());
        authenticationRoleDTO.setRoleIds(registerInfoDTO.getRoles());
        AjaxResult<Void> bindResult = registerAuthorityService.bind(authenticationRoleDTO);
        if (!bindResult.successful()) {
            log.error("Add role failed. Authentication [" + registerResult.getData().getId() + "] will be wasted, please delete it manually.");
            return AjaxResult.newInstance(LoginResponseDTO.class).fail("赋予角色错误：" + bindResult.getMessage());
        }
        // 注册后，开始登录
        AjaxResult<AccessToken> accessTokenResult = loginTokenService.getToken(SessionTokenHolder.getToken().getAccessToken());
        if (!accessTokenResult.successful()) {
            return AjaxResult.newInstance(LoginResponseDTO.class).fail("认证请求错误");
        }
        AccessToken accessToken = accessTokenResult.getData();
        if (accessToken.getUserIdentity() == null) {
            accessToken.setUserIdentity(new UserIdentity());
        }
        accessToken.getUserIdentity().setAuthenticationId(registerResult.getData().getId());
        accessToken.getUserIdentity().setRoles(registerInfoDTO.getRoles());

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        if (null != accessToken.getUserIdentity().getRoles()) {
            loginResponseDTO.setRoles(accessToken.getUserIdentity().getRoles().toArray());
        }
        AjaxResult<String> userIdentifierResult = loginTokenService.registerUserIdentity(accessToken);
        if (!userIdentifierResult.successful()) {
            return AjaxResult.newInstance(LoginResponseDTO.class).fail("注册成功, 生成用户标识错误");
        }
        loginResponseDTO.setUserIdentifier(userIdentifierResult.getData());
        return AjaxResult.newInstance(LoginResponseDTO.class).success("注册成功", loginResponseDTO);
    }

}
