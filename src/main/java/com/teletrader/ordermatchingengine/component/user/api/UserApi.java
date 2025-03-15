package com.teletrader.ordermatchingengine.component.user.api;

import com.teletrader.ordermatchingengine.component.user.model.User;

import java.util.Optional;

public interface UserApi {

    Optional<User> getById(Long userId);
}
