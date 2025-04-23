package di.aittr.pro_sotrudnikov.domen.dto;

import java.util.Objects;

public class SotrudnikDto {
    private Long id;
    private String imya;
    private String username;

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
        return Objects.equals(id, that.id) && Objects.equals(imya, that.imya) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imya, username);
    }

    @Override
    public String toString() {
        return String.format("Сотрудник ДТО:  ИД - %d, имя - %s, логин - %s",
                id, imya, username);
    }
}
