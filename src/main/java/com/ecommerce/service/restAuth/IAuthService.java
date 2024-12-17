package com.ecommerce.service.restAuth;

import com.ecommerce.dto.DtoUser;
import com.ecommerce.jwt.AuthRequest;
import com.ecommerce.jwt.AuthResponse;

public interface IAuthService {

	public DtoUser register(AuthRequest request);
	
	public AuthResponse authenticate(AuthRequest request);
}
