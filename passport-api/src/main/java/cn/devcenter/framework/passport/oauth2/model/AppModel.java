package cn.devcenter.framework.passport.oauth2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppModel {

    private String appId;

    private String appSecret;

    /**
     * 绑定的指定设备
     */
    private String clientId;

    /**
     * http://www.domain.xxx/oauth
     */
    private String redirectUri;

    /**
     * 可用范围（角色）
     */
    private String scope;
}
