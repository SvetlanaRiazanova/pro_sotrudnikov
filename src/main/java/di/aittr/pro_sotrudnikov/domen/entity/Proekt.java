package di.aittr.pro_sotrudnikov.domen.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.*;

@Entity
@Table(name = "proekt")
public class Proekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nazvanie")
    @Pattern(
            regexp = "[A-Z][a-z ]{2,}",
            message = "Название проекта должно быть как минимум 3 символа в длину и начинаться с большой буквы")
    private String nazvanie;

    @Column(name = "opisanie")
    @Pattern(
            regexp = "[A-Z][a-z ]{2,}",
            message = "Описание проекта должно быть как минимум 3 символа в длину и начинаться с большой буквы")
    private String opisanie;

    @OneToMany(mappedBy = "proekt", cascade = CascadeType.ALL)
    private List<Zadaca> spisokZadac = new ArrayList<>();

    @ManyToOne
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
        return String.format("Проект: ИД - %d, название - %s, описание - %s, список задач - %s, автор проекта - %s",
                id, nazvanie, opisanie, spisokZadac, avtorProekta);
    }
}
