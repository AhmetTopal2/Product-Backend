package com.ecommerce.controller.restAuth;

import com.ecommerce.dto.DtoUser;
import com.ecommerce.jwt.AuthRequest;
import com.ecommerce.jwt.AuthResponse;
import com.ecommerce.jwt.RefreshTokenRequest;

public interface IRestAuthController {

	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
	
	public AuthResponse refreshToken(RefreshTokenRequest request);
}
