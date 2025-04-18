package di.aittr.pro_sotrudnikov.domen.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "naimenovanie")
    private String naimenovanie;

    public Role() {
    }

    public Role(String id, String naimenovanie) {
        this.id = id;
        this.naimenovanie = naimenovanie;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNaimenovanie() {
        return naimenovanie;
    }

    public void setNaimenovanie(String naimenovanie) {
        this.naimenovanie = naimenovanie;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(naimenovanie, role.naimenovanie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naimenovanie);
    }

    @Override
    public String toString() {
        return String.format("Роль: ИД - %d, наименование - %s ", id, naimenovanie);
    }

    @Override
    public String getAuthority() {
        return naimenovanie;
    }
}
