package com.cherrymarket.service.api;

import com.cherrymarket.dto.entity.UserDto;
import com.cherrymarket.entity.UserEntity;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserEntity create(UserDto userDto);

    UserEntity getById(UUID id);

    UserEntity getByMarketIdAndEmail(UUID marketId, String email);

    List<UserEntity> getAllByMarketId(UUID marketId);

}
