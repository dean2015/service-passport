package cn.devcenter.framework.passport.oauth2.controller;

import cn.devcenter.framework.passport.oauth2.model.AppModel;
import cn.devcenter.framework.passport.oauth2.model.LoginInfoDTO;
import cn.devcenter.framework.passport.oauth2.model.PreCheckInfoDTO;
import cn.devcenter.model.result.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/oauth2")
@Slf4j
public class Oauth2Controller {

    private static final AppModel sample = new AppModel("whosyourdaddy", "123456", null, "http://localhost:40000/authc.html", "all|user");

    /**
     * 调用授权登录页面时，校验页面传递的参数
     *
     * @return
     */
    @RequestMapping(value = "/pre-check", method = RequestMethod.POST)
    public AjaxResult<Void> preCheck(@RequestBody @Valid PreCheckInfoDTO preCheckInfoDTO) {
        // 1. 根据appid, timestamp, callbackUrl 加密对比签名appSign
        // 2. redirectUri和appId对应的回调url对比
        // 3. 获取app授权
        // 4. 缓存结果
        return AjaxResult.newInstance(Void.class).success("");
    }

    /**
     * 认证
     *
     * @param loginInfoDTO
     * @return 授权细节
     */
    @RequestMapping(value = "/authorize", method = RequestMethod.POST)
    public AjaxResult<String> doLogin(@RequestBody @Valid LoginInfoDTO loginInfoDTO) {
        // 1. 根据appid, timestamp, callbackUrl 加密对比签名appSign
        // 2. redirectUri和appId对应的回调url对比
        // 3. 获取app授权
        // 4. 缓存结果
        // 4. loginId，timestamp 加密对比 loginSign
        if (sample.getAppId().equals(loginInfoDTO.getAppId()) && sample.getRedirectUri().equals(loginInfoDTO.getRedirectUri())) {
            // 5. 获取loginid授权
            // 6. 生成 授权码， 绑定到app授权和loginid授权
            // 7. 返回 授权码
            return AjaxResult.newInstance(String.class).success("", sample.getRedirectUri() + "?code=" + UUID.randomUUID().toString());
        }
        return AjaxResult.newInstance(String.class).fail("");
    }

    /**
     * 调用授权登录页面时，校验页面传递的参数
     *
     * @return
     */
    @RequestMapping(value = "/access-token", method = RequestMethod.POST)
    public AjaxResult<Void> exchangeAccessToken(@RequestBody @Valid PreCheckInfoDTO preCheckInfoDTO) {

        return AjaxResult.newInstance(Void.class).success("");
    }


}
