package di.aittr.pro_sotrudnikov.domen.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "sotrudnik")
public class Sotrudnik implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "imia")
    @Pattern(
            regexp = "[A-Z][a-z ]{1,}",
            message = "Имя сотрудника должно быть как минимум 2 символа в длину и начинаться с большой буквы")
    private String imya;

    @Column(name = "username")
    @Pattern(
            regexp = "[A-Z][a-z]{4,}",
            message = "Username сотрудника должен быть как минимум 5 символов в длину и начинаться с большой буквы")
    private String username;

    @Column(name = "password")
    @Pattern(
            regexp = "[A-Za-z0-9]{8,20}",
            message = "Пароль сотрудника должен быть как минимум 9 и максимум 20 символов в длину")
    private String password;

    @Column(name = "email")
    @Pattern(
            regexp = "^(.+)@(\\ S\\.+)$",
            message = "email сотрудника должен быть без пробелов и содержать символ @")
    private String email;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_sotrudnik",
            joinColumns = @JoinColumn(name = "sotrudnik_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")

    )
    private List<Role> roles;

    public Sotrudnik() {
    }

    public Sotrudnik(Long id, String imya, String username, String password, List<Role> roles) {
        this.id = id;
        this.imya = imya;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sotrudnik sotrudnik = (Sotrudnik) o;
        return active == sotrudnik.active && Objects.equals(id, sotrudnik.id) && Objects.equals(imya, sotrudnik.imya) && Objects.equals(username, sotrudnik.username) && Objects.equals(password, sotrudnik.password) && Objects.equals(email, sotrudnik.email) && Objects.equals(roles, sotrudnik.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imya, username, password, email, active, roles);
    }

    @Override
    public String toString() {
        return String.format("Сотрудник:  ИД - %d, имя - %s, логин - %s, почта - %s, активен - %s,  роли - %s",
                id, imya, username, email, active ? "да" : "нет", roles);
    }

}
