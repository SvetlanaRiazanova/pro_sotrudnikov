package di.aittr.pro_sotrudnikov.domen.dto;

import java.util.Objects;

public class SotrudnikDto {
    private Long id;
    private String imya;
    private String username;
    private String password;

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
        SotrudnikDto dto = (SotrudnikDto) o;
        return Objects.equals(id, dto.id) && Objects.equals(imya, dto.imya) && Objects.equals(username, dto.username) && Objects.equals(password, dto.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imya, username, password);
    }

    @Override
    public String toString() {
        return String.format("Сотрудник ДТО:  ИД - %d, имя - %s, логин - %s",
                id, imya, username);
    }
}
