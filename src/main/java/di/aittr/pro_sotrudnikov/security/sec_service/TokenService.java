package di.aittr.pro_sotrudnikov.security.sec_service;

import di.aittr.pro_sotrudnikov.domen.entity.Role;
import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.repozitory.RoleRepozitory;
import di.aittr.pro_sotrudnikov.security.AuthInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class TokenService {

    private SecretKey accessKey;
    private SecretKey refreshKey;
    private RoleRepozitory roleRepositopy;

    public TokenService(
            @Value("${key.access}") String accessPhrase,
            @Value("${key.refresh}") String refreshPhrase,
            RoleRepozitory roleRepositopy) {
        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessPhrase));
        this.refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(refreshPhrase));
        this.roleRepositopy = roleRepositopy;
    }

    public String generateAccessToken(Sotrudnik sotrudnik) {
        LocalDateTime currentData = LocalDateTime.now();
        Instant expiretion = currentData.plusDays(1).atZone(ZoneId.systemDefault()).toInstant();
        Date expiretionData = Date.from(expiretion);

        return Jwts.builder()
                .subject(sotrudnik.getUsername())
                .expiration(expiretionData)
                .signWith(accessKey)
                .claim("roles", sotrudnik.getAuthorities())
                .claim("name", sotrudnik.getImya())
                .compact();
    }

    public String generateRefreshToken(Sotrudnik sotrudnik) {
        LocalDateTime currentData = LocalDateTime.now();
        Instant expiretion = currentData.plusDays(7).atZone(ZoneId.systemDefault()).toInstant();
        Date expiretionData = Date.from(expiretion);

        return Jwts.builder()
                .subject(sotrudnik.getUsername())
                .expiration(expiretionData)
                .signWith(refreshKey)
                .compact();
    }

    public boolean validateAccessToken(String accessToken) {
        return validateToken(accessToken, accessKey);
    }

    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, refreshKey);
    }

    private boolean validateToken(String token, SecretKey key) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getAccessClaims(String accessToken) {
        return getClaims(accessToken, accessKey);
    }

    public Claims getRefreshClaims(String refreshToken) {
        return getClaims(refreshToken, refreshKey);
    }

    private Claims getClaims(String token, SecretKey key) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public AuthInfo mapClaimsToAuthInfo(Claims claims) {
        String usernaim = claims.getSubject();

        List<LinkedHashMap<String, String>> rolesList =
                (List<LinkedHashMap<String, String>>) claims.get("roles");
        List<Role> roles = new LinkedList<>();

        for (LinkedHashMap<String, String> roleEntry : rolesList) {
            String roleTitle = roleEntry.get("authority");
            roleRepositopy.findByNaimenovanie(roleTitle).ifPresent(roles::add);
        }
        return new AuthInfo(usernaim, roles);
    }
}
