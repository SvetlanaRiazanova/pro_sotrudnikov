package di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions;

public class FileNeNaidenExeption extends RuntimeException{

    public FileNeNaidenExeption() {
        super("Файл не найден");
    }
}
