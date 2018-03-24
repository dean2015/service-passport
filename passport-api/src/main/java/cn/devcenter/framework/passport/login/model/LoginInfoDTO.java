package cn.devcenter.framework.passport.login.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class LoginInfoDTO {

    @NotBlank
    private String username;

    private String password;

}
