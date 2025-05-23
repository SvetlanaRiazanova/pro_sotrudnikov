package di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions;

public class OschibkaPodtverzcdeniyaCodaExeption extends RuntimeException{

    public OschibkaPodtverzcdeniyaCodaExeption() {
        super("Ошибка подтверждения регистрации. Зарегистрируйтесь снова");
    }
}
