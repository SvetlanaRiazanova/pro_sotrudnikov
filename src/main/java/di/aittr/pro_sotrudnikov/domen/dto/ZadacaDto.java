package di.aittr.pro_sotrudnikov.domen.dto;

import di.aittr.pro_sotrudnikov.domen.entity.Proekt;
import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

public class ZadacaDto {

    private Long id;
    private String nazvanie;
    private String opisanie;
    private List<Sotrudnik> spisokSotrudnikov;
    private Proekt proekt;

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
        ZadacaDto zadacaDto = (ZadacaDto) o;
        return Objects.equals(id, zadacaDto.id) && Objects.equals(nazvanie, zadacaDto.nazvanie) && Objects.equals(opisanie, zadacaDto.opisanie) && Objects.equals(spisokSotrudnikov, zadacaDto.spisokSotrudnikov) && Objects.equals(proekt, zadacaDto.proekt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazvanie, opisanie, spisokSotrudnikov, proekt);
    }
    @Override
    public String toString() {
        return String.format("Задача ДТО: ИД - %d, название - $s, описание - %s, список сотрудников - %s, проект - %s",
                id, nazvanie, opisanie, spisokSotrudnikov, proekt);
    }
}
