package di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions;

public class IstekSrokDeistviyaCoda extends RuntimeException{

    public IstekSrokDeistviyaCoda() {
    }

    public IstekSrokDeistviyaCoda(String code){
        super(String.format("Истек срок действия кода %s", code));

    }

}
