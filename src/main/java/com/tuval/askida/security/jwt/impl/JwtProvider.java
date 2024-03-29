package com.tuval.askida.security.jwt.impl;

import com.tuval.askida.dto.JwtToken;
import com.tuval.askida.security.UserPrincipal;
import com.tuval.askida.security.jwt.IJwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider implements IJwtProvider {
    @Value("${authentication.jwt.expiration-in-ms}")
    private Long JWT_EXPIRATION_IN_MS;
    private static final String JWT_TOKEN_PREFIX = "Bearer";
    private static final String JWT_HEADER_STRING = "Authorization";
    private static final String USER_ID_CLAIM = "userId";
    private static final String ROLES_CLAIM = "roles";

    private final PrivateKey jwtPrivateKey;
    private final PublicKey jwtPublicKey;

    public JwtProvider(@Value("${authentication.jwt.private-key}") String jwtPrivateKeyStr,
                       @Value("${authentication.jwt.public-key}") String jwtPublicKeyStr){
        KeyFactory keyFactory = getKeyFactory();
        try{
            Base64.Decoder decoder = Base64.getDecoder();
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decoder.decode(jwtPrivateKeyStr.getBytes()));
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decoder.decode(jwtPublicKeyStr.getBytes()));

            jwtPrivateKey = keyFactory.generatePrivate(privateKeySpec);
            jwtPublicKey = keyFactory.generatePublic(publicKeySpec);
        }catch (Exception e){
            throw new RuntimeException("Invalid key specification");
        }
    }

    @Override
    public JwtToken generateToken(UserPrincipal authentication){
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());
        Date expiration = new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS);
        String token = Jwts.builder().subject(authentication.getEmail())
                .claim(USER_ID_CLAIM, authentication.getId())
                .claim(ROLES_CLAIM, authorities)
                .expiration(expiration)
                .signWith(jwtPrivateKey)
                .compact();

        return JwtToken.builder()
                .token(token)
                .expiration(expiration)
                .build();
    }

    @Override
    public JwtToken generateToken(Long id, String email){
        Date expiration = new Date(System.currentTimeMillis() + JWT_EXPIRATION_IN_MS);
        String token = Jwts.builder()
                .subject(email)
                .claim(USER_ID_CLAIM, id)
                .claim(ROLES_CLAIM, "USER")
                .expiration(expiration)
                .signWith(jwtPrivateKey)
                .compact();
        return JwtToken.builder()
                .token(token)
                .expiration(expiration)
                .build();
    }

    @Override
    public Authentication getAuthentication(HttpServletRequest request){
        String token = resolveToken(request);
        if(token == null){
            return null;
        }
        Claims claims = Jwts.parser()
                .verifyWith(jwtPublicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String email = claims.getSubject();
        Long userId = claims.get(USER_ID_CLAIM, Long.class);
        List<GrantedAuthority> authorities = Arrays.stream(claims.get(ROLES_CLAIM).toString().split(","))
                .map(role -> role.startsWith("ROLE_") ? role : "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        UserDetails userDetails = new UserPrincipal(userId, email, null);
        return email != null ? new UsernamePasswordAuthenticationToken(userDetails, null, authorities) : null;
    }

    @Override
    public boolean isTokenValid(HttpServletRequest request){
        String token = resolveToken(request);
        if(token == null){
            return false;
        }
        Claims claims = Jwts.parser()
                .verifyWith(jwtPublicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        if(claims.getExpiration().before(new Date())){
            return false;
        }
        return true;
    }

    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader(JWT_HEADER_STRING);
        if (bearerToken != null && bearerToken.startsWith(JWT_TOKEN_PREFIX)) {

            return bearerToken.substring(JWT_TOKEN_PREFIX.length() + 1);//"Bearer" and space "Bearer ey..."
        }
        return null;
    }

    private KeyFactory getKeyFactory(){
        try{
            return KeyFactory.getInstance("RSA");
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Unknown key generation algorithm", e);
        }
    }
}
