package di.aittr.pro_sotrudnikov.servise.interfaces;

import jakarta.mail.Multipart;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String upload(MultipartFile file, String sotrudnikImya);


}
