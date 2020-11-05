package com.thoughtworks.capacity.gtb.mvc.Controller;

import com.thoughtworks.capacity.gtb.mvc.Dto.User;
import com.thoughtworks.capacity.gtb.mvc.ErrorException.WrongUsernameOrPasswordException;
import com.thoughtworks.capacity.gtb.mvc.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/")
@Validated
@CrossOrigin
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody @Valid User user) throws Exception {
        loginService.registerUser(user);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public User loginUser(
            @RequestParam(value = "username") @Pattern(regexp = "\\w{3,10}$", message = "用户名不合法") String username,
            @RequestParam(value = "password") @Size(min = 5, max = 12, message = "密码不合法") String password)
            throws WrongUsernameOrPasswordException {
        return loginService.loginUser(username, password);
    }
}
