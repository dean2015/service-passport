package cn.devcenter.framework.passport.login.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Data
public class LoginInfoDTO implements Serializable {

    @NotBlank
    private String authenticationId;

    private String secret;

}
