package di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions;

public class ZadacaNeNaidenExeption extends RuntimeException{

    public ZadacaNeNaidenExeption(Long id) {
        super(String.format("Задача с ИД %d не найден", id));
    }
}
