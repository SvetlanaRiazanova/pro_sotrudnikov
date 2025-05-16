package di.aittr.pro_sotrudnikov.servise.interfaces;

import di.aittr.pro_sotrudnikov.domen.entity.ConfirmationCode;
import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;

public interface ConfirmationServise {

    String generateConfirmationCode(Sotrudnik sotrudnik);

    ConfirmationCode procitatPoCodu(String code);



}
