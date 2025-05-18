package di.aittr.pro_sotrudnikov.domen.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Schema(description = "Класс, который описывает ДТО проекта")
public class ProektDto {

    @Schema(description = "Уникальный идентификатор проекта",
            example = "777",
            accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Название проекта",
            example = "Умный дом")
    private String nazvanie;

    @Schema(description = "Описание проекта",
            example = "Строительство многоквартирного жилого дома")
    private String opisanie;

    @Schema(description = "Список задач, необходимых для выполнения проекта",
            example = "Разработка проектной документации, подготовка стоительной площадки, и др.")
    private List<ZadacaDto> spisokZadac = new ArrayList<>();

    @Schema(description = "Автор проекта - имя сотрудника",
            example = "Вася")
    private SotrudnikDto avtorProekta;

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

    public List<ZadacaDto> getSpisokZadac() {
        return spisokZadac;
    }

    public void setSpisokZadac(List<ZadacaDto> spisokZadac) {
        this.spisokZadac = spisokZadac;
    }

    public SotrudnikDto getAvtorProekta() {
        return avtorProekta;
    }

    public void setAvtorProekta(SotrudnikDto avtorProekta) {
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
        return String.format("Проект ДТО: ИД - %d, название - %s, описание - %s, список задач - %s, автор проекта - %s",
                id, nazvanie, opisanie, spisokZadac, avtorProekta);
    }
}
