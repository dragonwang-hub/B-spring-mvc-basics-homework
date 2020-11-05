package com.thoughtworks.capacity.gtb.mvc.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.concurrent.atomic.AtomicInteger;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private AtomicInteger id;
    @NotEmpty(message = "用户名不为空")
    @Pattern(regexp = "\\w{3,10}$", message = "用户名不合法")
    private String username;
    @NotEmpty(message = "密码不为空")
    @Size(min = 5, max = 12, message = "密码不合法")
    private String password;
    @Email(message = "邮箱地址不合法")
    private String email;
}
