package com.teletrader.ordermatchingengine.component.user.command;

import com.teletrader.ordermatchingengine.component.user.model.User;
import com.teletrader.ordermatchingengine.component.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserGetOneByIdCommand {

    private final UserRepository userRepository;

    public Optional<User> execute(Long userId) {
        return userRepository.findById(userId);
    }
}
