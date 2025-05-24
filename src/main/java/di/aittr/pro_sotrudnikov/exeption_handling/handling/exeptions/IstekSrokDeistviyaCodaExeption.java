package di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions;

public class IstekSrokDeistviyaCodaExeption extends RuntimeException{


    public IstekSrokDeistviyaCodaExeption(){
        super("Истек срок подтверждения регистрации, зарегистрируйтесь снова");

    }

}
