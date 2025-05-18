package di.aittr.pro_sotrudnikov.domen.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
import java.util.Objects;

@Schema(description = "Класс, который описывает ДТО задачи")
public class ZadacaDto {

    @Schema(description = "Уникальный идентификатор задачи",
            example = "777",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Название задачи",
            example = "Разработка проектной документации")
    private String nazvanie;

    @Schema(description = "Описание задачи",
            example = "Разработать проектную документацию для строительства жилого дома")
    private String opisanie;

    @Schema(description = "Список сотрудников, работающих над проектом",
            example = "Вася, Петя, и др.")
    private List<SotrudnikDto> spisokSotrudnikov;


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
        return String.format("Задача ДТО: ИД - %d, название - %s, описание - %s, список сотрудников - %s",
                id, nazvanie, opisanie, spisokSotrudnikov);
    }
}
