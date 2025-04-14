package di.aittr.pro_sotrudnikov.domen.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "sotrudnik")
public class Sotrudnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "imia")
    private String imya;

    public Sotrudnik() {
    }

    public Sotrudnik(Long id, String imya) {
        this.id = id;
        this.imya = imya;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Sotrudnik sotrudnik = (Sotrudnik) o;
        return Objects.equals(id, sotrudnik.id) && Objects.equals(imya, sotrudnik.imya);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imya);
    }

    @Override
    public String toString() {
        return String.format("Сотрудник:  ИД - %d, имя - %s", id, imya);
    }
}
