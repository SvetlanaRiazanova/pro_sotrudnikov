package di.aittr.pro_sotrudnikov.domen.entity;

import jakarta.persistence.*;
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
    private String imya;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role-sotrudnik",
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
        return Objects.equals(id, sotrudnik.id) && Objects.equals(imya, sotrudnik.imya) && Objects.equals(username, sotrudnik.username) && Objects.equals(password, sotrudnik.password) && Objects.equals(roles, sotrudnik.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imya, username, password, roles);
    }

    @Override
    public String toString() {
        return String.format("Сотрудник:  ИД - %d, имя - %s, логин - %s, роли - %s",
                id, imya, username, roles);
    }

//    public static void main(String[] args){
//        System.out.println(new BCryptPasswordEncoder().encode("111"));

}
