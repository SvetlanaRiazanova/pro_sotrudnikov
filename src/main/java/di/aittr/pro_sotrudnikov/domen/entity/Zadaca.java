package di.aittr.pro_sotrudnikov.domen.entity;

import java.util.List;
import java.util.Objects;

public class Zadaca {

    private Long id;
    private String nazvanie;
    private String opisanie;
    private List<Sotrudnik> spisokSotrudnikov;
    private Proekt proekt;

    public Zadaca() {
    }

    public Zadaca(Long id, String nazvanie, String opisanie, List<Sotrudnik> spisokSotrudnikov, Proekt proekt) {
        this.id = id;
        this.nazvanie = nazvanie;
        this.opisanie = opisanie;
        this.spisokSotrudnikov = spisokSotrudnikov;
        this.proekt = proekt;
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

    public List<Sotrudnik> getSpisokSotrudnikov() {
        return spisokSotrudnikov;
    }

    public void setSpisokSotrudnikov(List<Sotrudnik> spisokSotrudnikov) {
        this.spisokSotrudnikov = spisokSotrudnikov;
    }

    public Proekt getProekt() {
        return proekt;
    }

    public void setProekt(Proekt proekt) {
        this.proekt = proekt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Zadaca zadaca = (Zadaca) o;
        return Objects.equals(id, zadaca.id) && Objects.equals(nazvanie, zadaca.nazvanie) && Objects.equals(opisanie, zadaca.opisanie) && Objects.equals(spisokSotrudnikov, zadaca.spisokSotrudnikov) && Objects.equals(proekt, zadaca.proekt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazvanie, opisanie, spisokSotrudnikov, proekt);
    }

    @Override
    public String toString() {
        return String.format("Проект: ИД - %d, название - $s, описание - %s, список сотрудников - %s, проект - %s",
                id, nazvanie, opisanie, spisokSotrudnikov, proekt);
    }
}
