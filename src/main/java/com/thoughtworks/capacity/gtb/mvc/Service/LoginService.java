package com.thoughtworks.capacity.gtb.mvc.Service;

import com.thoughtworks.capacity.gtb.mvc.Dto.User;
import com.thoughtworks.capacity.gtb.mvc.ErrorException.UserIsExistException;
import com.thoughtworks.capacity.gtb.mvc.ErrorException.WrongUsernameOrPasswordException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class LoginService {
    static public List<User> UserList = new ArrayList<>();

    public void registerUser(User user) throws Exception {
        if (UserList.stream().anyMatch(x -> {
            return x.getUsername().equals(user.getUsername());
        })) {
            throw new UserIsExistException("用户已存在");
        }
        user.setId(new AtomicInteger(UserList.size()+1));
        UserList.add(user);
    }

    public User loginUser(String username, String password) throws WrongUsernameOrPasswordException {
        List<User> userList = UserList.stream().filter(u -> u.getUsername().equals(username)).collect(Collectors.toList());
        if((userList.size()==0)||(!userList.get(0).getPassword().equals(password))){
            throw new WrongUsernameOrPasswordException("用户名或密码错误");
        }
        return userList.get(0);
    }
}
