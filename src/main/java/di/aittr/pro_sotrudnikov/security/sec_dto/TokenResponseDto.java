package di.aittr.pro_sotrudnikov.security.sec_dto;

import java.util.Objects;

public class TokenResponseDto {
    private String accessToken;
    private String refreshToken;

    public TokenResponseDto() {
    }

    public TokenResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }

    public TokenResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TokenResponseDto that = (TokenResponseDto) o;
        return Objects.equals(accessToken, that.accessToken) && Objects.equals(refreshToken, that.refreshToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accessToken, refreshToken);
    }

    @Override
    public String toString() {
        return String.format("Token Response Dto: access token - %s, refresh token - %s.", accessToken, refreshToken);
    }
}
