package di.aittr.pro_sotrudnikov.servise;

import di.aittr.pro_sotrudnikov.domen.entity.ConfirmationCode;
import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions.OschibkaPodtverzcdeniyaCodaExeption;
import di.aittr.pro_sotrudnikov.repozitory.ConfirmationCodeRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.ConfirmationServise;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationServiceImpl implements ConfirmationServise {

    private final ConfirmationCodeRepozitory repozitory;

    public ConfirmationServiceImpl(ConfirmationCodeRepozitory repozitory) {
        this.repozitory = repozitory;
    }

    @Override
    public String generateConfirmationCode(Sotrudnik sotrudnik) {
        String code = UUID.randomUUID().toString();
        LocalDateTime expired = LocalDateTime.now().plusMinutes(5);
        ConfirmationCode codeEntity = new ConfirmationCode(code, expired, sotrudnik);
        repozitory.save(codeEntity);
        return code;
    }

    @Override
    public ConfirmationCode procitatPoCodu(String code) {
        return repozitory.findByCode(code).orElseThrow(
                () -> new OschibkaPodtverzcdeniyaCodaExeption());
    }


}
