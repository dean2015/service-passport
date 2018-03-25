package cn.devcenter.framework.passport.login.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class LoginResponseDTO implements Serializable {

    private String userIdentifier;

    private Object[] roles;

}
