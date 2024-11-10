package org.example.client;


import org.example.domain.pojo.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("userControllerImpl")
public class UserControllerImpl implements UserController {

    @Override
    public User findById(Integer id) {

        System.out.println("执行了熔断器类...");
        return null;
    }
}
