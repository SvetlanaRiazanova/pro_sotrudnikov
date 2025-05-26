package di.aittr.pro_sotrudnikov.controller;

import di.aittr.pro_sotrudnikov.exeption_handling.Response;
import di.aittr.pro_sotrudnikov.servise.interfaces.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public Response upload(
            @RequestParam MultipartFile file,
            @RequestParam String sotrudnikImya
            ) {

        String url = fileService.upload(file, sotrudnikImya);
        return new Response("Сохраненное изображение URL - " + url);

    }
}
