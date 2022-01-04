package com.dwibagus.auths.util;


import com.dwibagus.auths.exception.JwtTokenMalformedException;
import com.dwibagus.auths.exception.JwtTokenMissingException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;


    @Value("${jwt.token.validity}")
    private long tokenValidity;

    public Claims getClaims(final String token){
        try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return body;
        }catch (Exception e){
            System.out.println(e.getMessage() + " up " + e);
        }
        return null;
    }

    public String generateToken(String id){
        Claims claims = Jwts.claims().setSubject(id);
        long nowMillis = System.currentTimeMillis();
        long expMillis = nowMillis + tokenValidity;
        Date exp = new Date(expMillis);
        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(nowMillis)).setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public void ValiditeToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException{
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid Jwt Signature");
//            log.error("Invalid Jwt Signature: {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid Jwt Signature");
//            log.error("Invalid Jwt Token: {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Invalid Jwt Signature");
//            log.error("Expired Jwt Token: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid Jwt Signature");
//            log.error("Unsupported Jwt token: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException(("jwt claim "));
//            log.error("Jwt claim string is empty: {}", ex.getMessage());
        }
    }
}
