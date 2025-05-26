package di.aittr.pro_sotrudnikov.servise;

import di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions.FileNeNaidenExeption;
import di.aittr.pro_sotrudnikov.servise.interfaces.FileService;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final SotrudnikServise sotrudnikServise;

    public FileServiceImpl(SotrudnikServise sotrudnikServise) {
        this.sotrudnikServise = sotrudnikServise;
    }

    @Override
    public String upload(MultipartFile vhFile, String sotrudnikImya) {
        try {
            String uniqueName = generateUniqueFileName(vhFile);
            String uploadDirName = "C:/images/";
            File dir = new File(uploadDirName);
            dir.mkdir();

            File file = new File(uploadDirName + uniqueName);
            file.createNewFile();
            vhFile.transferTo(file);

            String url = file.getAbsolutePath();
            sotrudnikServise.attachImage(url, sotrudnikImya);

            return url;

        } catch (Exception e) {
            throw new FileNeNaidenExeption();
        }
    }

    public String generateUniqueFileName(MultipartFile file){
        String originalImyaFile = file.getOriginalFilename();
        int indexTocki = originalImyaFile.lastIndexOf(".");
        String imyaRasshireniya = originalImyaFile.substring(indexTocki);

        return String.format("%s%s", UUID.randomUUID(), imyaRasshireniya);
    }
}
