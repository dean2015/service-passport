package cn.devcenter.framework.passport.oauth2.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class LoginInfoDTO implements Serializable {

    @NotBlank
    private String appId;

    @NotBlank
    private String redirectUri;

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

}
