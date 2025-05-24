package di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions;

public class RoleNeNaidenExeption extends RuntimeException{

    public RoleNeNaidenExeption(String naimenovanie) {
        super(String.format("Роль с наименованием %s не найдена", naimenovanie));
    }
}
