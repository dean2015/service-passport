package cn.devcenter.framework.passport.oauth2.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Data
public class PreCheckInfoDTO implements Serializable {

    @NotBlank
    private String appId;

    @NotBlank
    private String redirectUri;

}
