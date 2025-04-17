package di.aittr.pro_sotrudnikov.security.sec_service;

import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.security.sec_dto.TokenResponseDto;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    private final SotrudnikServise sotrudnikServise;
    private final TokenService tokenService;
    private final Map<String, String> refreshStorage;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(SotrudnikServise sotrudnikServise, TokenService tokenService, BCryptPasswordEncoder passwordEncoder) {
        this.sotrudnikServise = sotrudnikServise;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
        this.refreshStorage = new HashMap<>();
    }

    public TokenResponseDto login(Sotrudnik inboundUser) throws AuthException {
        String usernaim = inboundUser.getUsername();
        UserDetails foundUser = sotrudnikServise.loadUserByUsername(usernaim);

        if (passwordEncoder.matches(inboundUser.getPassword(), foundUser.getPassword())) {
            String accessToken = tokenService.generateAccessToken(foundUser);
            String refreshToken = tokenService.generateRefreshToken(foundUser);
            refreshStorage.put(usernaim, refreshToken);
            return new TokenResponseDto(accessToken, refreshToken);

        } else {
            throw new AuthException("Password is incorrect");
        }
    }

    public TokenResponseDto getNewAccessToken(String inboundRefreshToken) {
        Claims refreshCleims = tokenService.getRefreshClaims(inboundRefreshToken);
        String usernaim = refreshCleims.getSubject();
        String faundRefreshTokin = refreshStorage.get(usernaim);

        if (faundRefreshTokin != null && faundRefreshTokin.equals(inboundRefreshToken)) {
            UserDetails faundUser = sotrudnikServise.loadUserByUsername(usernaim);
            String accessToken = tokenService.generateAccessToken(faundUser);
            return new TokenResponseDto(accessToken);

        } else {
            return new TokenResponseDto(null);
        }
    }

}
