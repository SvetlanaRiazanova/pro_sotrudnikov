package di.aittr.pro_sotrudnikov.servise.interfaces;

import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;

public interface EmailServise {

    void sendConfirmationEmail(Sotrudnik sotrudnik);
}
