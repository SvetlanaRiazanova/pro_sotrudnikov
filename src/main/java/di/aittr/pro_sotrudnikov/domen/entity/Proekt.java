package di.aittr.pro_sotrudnikov.domen.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "proekt")
public class Proekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nazvanie")
    private String nazvanie;

    @Column(name = "opisanie")
    private String opisanie;

    @ManyToMany
    @JoinTable(
            name = "zadaca_sotrudnik",
            joinColumns = @JoinColumn(name = "zadaca_id"),
            inverseJoinColumns = @JoinColumn(name = "sotrudnik_id")
    )
    private List<Zadaca> spisokZadac;

    @OneToOne
    @JoinColumn(name = "sotrudnik_id")
    private Sotrudnik avtorProekta;

    public Proekt() {
    }

    public Proekt(Long id, String nazvanie, String opisanie, List<Zadaca> spisokZadac, Sotrudnik avtorProekta) {
        this.id = id;
        this.nazvanie = nazvanie;
        this.opisanie = opisanie;
        this.spisokZadac = spisokZadac;
        this.avtorProekta = avtorProekta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNazvanie() {
        return nazvanie;
    }

    public void setNazvanie(String nazvanie) {
        this.nazvanie = nazvanie;
    }

    public String getOpisanie() {
        return opisanie;
    }

    public void setOpisanie(String opisanie) {
        this.opisanie = opisanie;
    }

    public List<Zadaca> getSpisokZadac() {
        return spisokZadac;
    }

    public void setSpisokZadac(List<Zadaca> spisokZadac) {
        this.spisokZadac = spisokZadac;
    }

    public Sotrudnik getAvtorProekta() {
        return avtorProekta;
    }

    public void setAvtorProekta(Sotrudnik avtorProekta) {
        this.avtorProekta = avtorProekta;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Proekt proekt = (Proekt) o;
        return Objects.equals(id, proekt.id) && Objects.equals(nazvanie, proekt.nazvanie) && Objects.equals(opisanie, proekt.opisanie) && Objects.equals(spisokZadac, proekt.spisokZadac) && Objects.equals(avtorProekta, proekt.avtorProekta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazvanie, opisanie, spisokZadac, avtorProekta);
    }

    @Override
    public String toString() {
        return String.format("Проект: ИД - %d, название - $s, описание - %s, список задач - %s, автор проекта - %s",
                id, nazvanie, opisanie, spisokZadac, avtorProekta);
    }
}
