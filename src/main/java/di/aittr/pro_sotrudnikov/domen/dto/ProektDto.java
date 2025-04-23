package di.aittr.pro_sotrudnikov.domen.dto;

import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.domen.entity.Zadaca;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProektDto {

    private Long id;
    private String nazvanie;
    private String opisanie;
    private List<Zadaca> spisokZadac = new ArrayList<>();
    private Sotrudnik avtorProekta;

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
        ProektDto proektDto = (ProektDto) o;
        return Objects.equals(id, proektDto.id) && Objects.equals(nazvanie, proektDto.nazvanie) && Objects.equals(opisanie, proektDto.opisanie) && Objects.equals(spisokZadac, proektDto.spisokZadac) && Objects.equals(avtorProekta, proektDto.avtorProekta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazvanie, opisanie, spisokZadac, avtorProekta);
    }
    @Override
    public String toString() {
        return String.format("Проект ДТО: ИД - %d, название - $s, описание - %s, список задач - %s, автор проекта - %s",
                id, nazvanie, opisanie, spisokZadac, avtorProekta);
    }
}
