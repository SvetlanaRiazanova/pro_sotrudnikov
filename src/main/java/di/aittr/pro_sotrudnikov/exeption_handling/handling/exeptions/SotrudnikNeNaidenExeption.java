package di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions;


public class SotrudnikNeNaidenExeption extends RuntimeException{

    public SotrudnikNeNaidenExeption(Long id) {
        super(String.format("Сотрудник с ИД %d не найден", id));
    }

    public SotrudnikNeNaidenExeption(String imya) {
        super(String.format("Сотрудник с именем %s не найден", imya));
    }

}
