package di.aittr.pro_sotrudnikov.domen.dto;

import java.util.List;
import java.util.Objects;

public class ZadacaDto {

    private Long id;
    private String nazvanie;
    private String opisanie;
    private List<SotrudnikDto> spisokSotrudnikov;
    //private ProektDto proekt;

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

    public List<SotrudnikDto> getSpisokSotrudnikov() {
        return spisokSotrudnikov;
    }

    public void setSpisokSotrudnikov(List<SotrudnikDto> spisokSotrudnikov) {
        this.spisokSotrudnikov = spisokSotrudnikov;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ZadacaDto zadacaDto = (ZadacaDto) o;
        return Objects.equals(id, zadacaDto.id) && Objects.equals(nazvanie, zadacaDto.nazvanie) && Objects.equals(opisanie, zadacaDto.opisanie) && Objects.equals(spisokSotrudnikov, zadacaDto.spisokSotrudnikov);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nazvanie, opisanie, spisokSotrudnikov);
    }

    @Override
    public String toString() {
        return String.format("Задача ДТО: ИД - %d, название - $s, описание - %s, список сотрудников - %s",
                id, nazvanie, opisanie, spisokSotrudnikov);
    }
}
