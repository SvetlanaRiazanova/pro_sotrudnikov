package di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions;

public class ProektNeNaidenExeption extends RuntimeException{

    public ProektNeNaidenExeption(Long id) {
        super(String.format("Проект с ИД %d не найден", id));
    }
}
