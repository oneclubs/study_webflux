package com.oneclubs.study.jwt;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Collections;
import java.util.Map;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtUserAuthenticationToken extends AbstractAuthenticationToken {

    private final String type;
    private final String userId;
    private final DecodedJWT decodedJWT;

    public JwtUserAuthenticationToken(DecodedJWT decodedJWT) {
        super(Collections.emptyList());
        Map<String, Claim> claims = decodedJWT.getClaims();

        this.type = claims.get("type").asString();
        this.userId = claims.get("userId").asString();
        this.decodedJWT = decodedJWT;
    }

    @Override
    public Object getCredentials() {
        return this.decodedJWT;
    }

    @Override
    public Object getPrincipal() {
        return this.userId;
    }
}
