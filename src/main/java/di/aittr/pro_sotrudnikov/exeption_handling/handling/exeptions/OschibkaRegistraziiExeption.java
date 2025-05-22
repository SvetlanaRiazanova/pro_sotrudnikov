package di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions;

public class OschibkaRegistraziiExeption extends RuntimeException{

    public OschibkaRegistraziiExeption() {
        super("Вы зарегистрированы ранее");

    }
}
