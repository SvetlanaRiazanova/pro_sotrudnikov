package di.aittr.pro_sotrudnikov.domen.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(description = "Класс, который описывает ДТО сотрудника")
public class SotrudnikDto {

    @Schema(description = "Уникальный идентификатор сотрудника",
            example = "777",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Имя сотрудника",
            example = "Вася")
    private String imya;

    @Schema(description = "Логин сотрудника",
            example = "Вася")
    private String username;

    @Schema(description = "Пароль сотрудника",
            example = "12345")
    private String password;

    @Schema(description = "Электронная почта сотрудника",
            example = "abc@mail.ru")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImya() {
        return imya;
    }

    public void setImya(String imya) {
        this.imya = imya;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SotrudnikDto that = (SotrudnikDto) o;
        return Objects.equals(id, that.id) && Objects.equals(imya, that.imya) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imya, username, password, email);
    }

    @Override
    public String toString() {
        return String.format("Сотрудник ДТО:  ИД - %d, имя - %s, логин - %s",
                id, imya, username);
    }
}
