package cn.devcenter.framework.passport.login.user.sdk.fallback;

import cn.devcenter.framework.passport.login.user.sdk.LoginUserService;
import cn.housecenter.dlfc.framework.boot.stereotype.Service;
import cn.housecenter.dlfc.framework.web.core.AjaxResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.Serializable;

@Service
@Slf4j
public class LoginUserServiceFallback implements LoginUserService {

    @RequestMapping(value = "/get/id/fallback", method = RequestMethod.GET)
    @Override
    public AjaxResult<String> getId(Serializable username) {
        log.warn("method [getId(username)] is not available.");
        return AjaxResult.newInstance(String.class).success("使用测试id", "NNNMMM");
    }

}
