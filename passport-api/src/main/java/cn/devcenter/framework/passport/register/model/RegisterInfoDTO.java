package cn.devcenter.framework.passport.register.model;

import cn.devcenter.model.authentication.Authentication;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class RegisterInfoDTO {

    @NotNull
    private Authentication authentication;

    @NotNull
    private List<String> roles;

}
