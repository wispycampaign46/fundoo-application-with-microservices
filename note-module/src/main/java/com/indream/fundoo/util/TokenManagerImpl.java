package com.indream.fundoo.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.indream.fundoo.exceptionhandler.TokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class TokenManagerImpl implements TokenManager {
    final Logger LOG = Logger.getLogger(TokenManagerImpl.class);

    @Autowired
    Environment env;

   
    /*
     * @PURPOSE VALIDATE THE TOKENS
     *
     * @author akshay
     * 
     * @com.indream.fundoo.util
     * 
     * @since Jul 24, 2018
     *
     */
    @Override
    public Claims validateToken(String token) {
	LOG.info("Enter [TokenManagerImpl][validateToken]");
	LOG.info("method param : " + token);

	try {
	    Jws<Claims> jwtClaims = Jwts.parser().setSigningKey(env.getProperty("secretkey")).parseClaimsJws(token);
	    Claims claims = jwtClaims.getBody();// GET THE CLAIMS FROM THE TOEK WHICH IS PARSED
	    return claims;
	} catch (TokenException e) {
	    e.printStackTrace();
	    throw e;
	}

    }

}
