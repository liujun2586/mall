package cn.wolfcode.shop.controller;

import cn.wolfcode.shop.domain.UserLogin;
import cn.wolfcode.shop.service.IUserService;
import cn.wolfcode.shop.vo.WSResponseVo;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户相关接口
 */
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    @Reference
    IUserService userService;

    @PostMapping("register")
    public WSResponseVo register(@Valid UserLogin login,String phone){
        userService.register(login,phone);
        return new WSResponseVo();
    }
    @PostMapping("login")
    public WSResponseVo login( UserLogin login){
        String token = userService.login(login);
        return new WSResponseVo(WSResponseVo.CODE_IN_SUCCESS,token);
    }


    @DeleteMapping("/logout")
    public WSResponseVo logout(@RequestHeader(value = "token",required = false)String token ){
        userService.logout(token);
        return new WSResponseVo();
    }
}
