package com.simon.java.token;

import com.auth0.jwk.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;

public class JwtToken {
    static final String TOKEN = "";

    public static void main(String[] args) {
        DecodedJWT jwt = JWT.decode(TOKEN);
        //use the JWKS library to get a JwkProvider from the JWKS URL
        try {
            JwkProvider domainprovider = new UrlJwkProvider("http://localhost:4444");
            JwkProvider uriprovider = new UrlJwkProvider(new URI("url").normalize().toURL(), 10000, 10000);
            Jwk jwk = domainprovider.get(jwt.getKeyId());
            Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
            algorithm.verify(jwt);
            System.out.println("verify sign pass");
            if (jwt.getExpiresAt().before(Calendar.getInstance().getTime())) {
                System.out.println("verify exp exired");
            } else {
                System.out.println("verify exp pass");
            }
        } catch (JwkException e) {
            System.out.println(e.getMessage());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
