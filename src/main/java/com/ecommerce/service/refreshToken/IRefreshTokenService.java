package com.ecommerce.service.refreshToken;

import com.ecommerce.jwt.AuthResponse;
import com.ecommerce.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {

	public AuthResponse refreshToken(RefreshTokenRequest request);
}
