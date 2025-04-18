package di.aittr.pro_sotrudnikov.security.sec_controller;

import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.security.sec_dto.RefreshRequestDto;
import di.aittr.pro_sotrudnikov.security.sec_dto.TokenResponseDto;
import di.aittr.pro_sotrudnikov.security.sec_service.AuthService;
import jakarta.security.auth.message.AuthException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    private TokenResponseDto login(@RequestBody Sotrudnik sotrudnik) {
        try {
            return service.login(sotrudnik);
        } catch (AuthException e) {
            return new TokenResponseDto(null);
        }
    }

    @PostMapping("/refresh")
    public TokenResponseDto getNewAccessToken(@RequestBody RefreshRequestDto refreshRequestDto) {
        return service.getNewAccessToken(refreshRequestDto.getRefreshToken());

    }
}
