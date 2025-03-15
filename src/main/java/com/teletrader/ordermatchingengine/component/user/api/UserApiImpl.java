package com.teletrader.ordermatchingengine.component.user.api;

import com.teletrader.ordermatchingengine.component.user.command.UserGetOneByIdCommand;
import com.teletrader.ordermatchingengine.component.user.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserApiImpl implements UserApi{

    private final UserGetOneByIdCommand userGetOneByIdCommand;

    @Override
    public Optional<User> getById(Long userId) {
        return userGetOneByIdCommand.execute(userId);
    }
}
