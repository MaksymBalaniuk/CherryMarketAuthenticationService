package com.cherrymarket.service.api;

import com.cherrymarket.dto.authentication.AuthenticationRequestDto;
import com.cherrymarket.dto.authentication.AuthenticationResponseDto;

public interface AuthenticationService {

    AuthenticationResponseDto authentication(AuthenticationRequestDto authenticationDto);

}
