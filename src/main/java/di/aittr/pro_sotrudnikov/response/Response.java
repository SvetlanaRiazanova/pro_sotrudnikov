package di.aittr.pro_sotrudnikov.response;

import org.springframework.web.multipart.MultipartFile;

public class Response {

    private String message;
    private MultipartFile file;
    private String sotrudnikImya;


    public Response(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Response(MultipartFile file, String sotrudnikImya) {
        this.file = file;
        this.sotrudnikImya = sotrudnikImya;
    }
}
