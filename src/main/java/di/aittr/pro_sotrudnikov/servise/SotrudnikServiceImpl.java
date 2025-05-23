package di.aittr.pro_sotrudnikov.servise;

import di.aittr.pro_sotrudnikov.domen.dto.SotrudnikDto;
import di.aittr.pro_sotrudnikov.domen.entity.ConfirmationCode;
import di.aittr.pro_sotrudnikov.domen.entity.Role;
import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions.IstekSrokDeistviyaCodaExeption;
import di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions.OschibkaRegistraziiExeption;
import di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions.SotrudnikNeNaidenExeption;
import di.aittr.pro_sotrudnikov.exeption_handling.handling.exeptions.SotrudnikValidacionExeption;
import di.aittr.pro_sotrudnikov.repozitory.SotrudnikRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.ConfirmationServise;
import di.aittr.pro_sotrudnikov.servise.interfaces.EmailServise;
import di.aittr.pro_sotrudnikov.servise.interfaces.RoleServise;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import di.aittr.pro_sotrudnikov.servise.mapping.SotrudnikMappingServise;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SotrudnikServiceImpl implements SotrudnikServise {

    private final SotrudnikRepozitory repozitory;
    private final BCryptPasswordEncoder encoder;
    private final RoleServise roleServise;
    private final SotrudnikMappingServise mappingSernise;
    private final EmailServise emailServise;
    private final ConfirmationServise confirmationServise;


    public SotrudnikServiceImpl(SotrudnikRepozitory repozitory, BCryptPasswordEncoder encoder, RoleServise roleServise, SotrudnikMappingServise mappingSernise, EmailServise emailServise, ConfirmationServise confirmationServise) {
        this.repozitory = repozitory;
        this.encoder = encoder;
        this.roleServise = roleServise;
        this.mappingSernise = mappingSernise;
        this.emailServise = emailServise;
        this.confirmationServise = confirmationServise;
    }

    @Override
    @Transactional
    public SotrudnikDto sozdat(SotrudnikDto dto) {

        try {
            Sotrudnik entity = mappingSernise.mahDtoToEntity(dto);
            entity.setPassword(encoder.encode(entity.getPassword()));
            List<Role> list = new ArrayList<>();
            list.add(roleServise.procitatPoNaimenovanie("ROLE_USER"));
            entity.setRoles(list);
            entity = repozitory.save(entity);
            return mappingSernise.mapEntityToDto(entity);
        } catch (Exception e) {
            throw new SotrudnikValidacionExeption(e);
        }
    }

    @Override
    public List<SotrudnikDto> procitatVseh() {
        return repozitory.findAll()
                .stream()
                .map(mappingSernise::mapEntityToDto)
                .toList();
    }

    @Override
    public SotrudnikDto procitatPoId(Long id) {
        return mappingSernise.mapEntityToDto(procitatEntityPoId(id));

    }

    @Override
    public SotrudnikDto procitatPoImeni(String imya) {
        return mappingSernise.mapEntityToDto(procitatEntityPoImeni(imya));
    }


    @Transactional
    @Override
    public void obnovitPoId(SotrudnikDto sotrudnik) {

        try {
            Long id = sotrudnik.getId();
            SotrudnikDto sushestvSotrudnik = procitatPoId(id);
            sushestvSotrudnik.setImya(sotrudnik.getImya());

        } catch (Exception e) {
            throw new SotrudnikValidacionExeption(e);
        }

    }

    @Override
    public void udalitPoId(Long id) {
        repozitory.deleteById(id);

    }

    @Transactional
    @Override
    public void udalitPoImeni(String imya) {
        repozitory.deleteByImya(imya);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repozitory.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Сотрудник с " + username + " не найден.")
        );
    }

    @Override
    public Sotrudnik procitatEntityPoId(Long sotrudnikId) {
        return repozitory.findById(sotrudnikId).orElseThrow(
                () -> new SotrudnikNeNaidenExeption(sotrudnikId));

    }

    public Sotrudnik procitatEntityPoImeni(String sotrudnikImya) {
        return repozitory.findByImya(sotrudnikImya).orElseThrow(
                () -> new SotrudnikNeNaidenExeption(sotrudnikImya));

    }

    @Transactional
    @Override
    public void register(SotrudnikDto dto) {

        try {
            Sotrudnik entiti = (Sotrudnik) loadUserByUsername(dto.getUsername());

            if (!entiti.isActive()) {
                entiti.setPassword(encoder.encode(dto.getPassword()));
                entiti.setEmail(dto.getEmail());
                emailServise.sendConfirmationEmail(entiti);
            }
            if (entiti.isActive()) {
                throw new OschibkaRegistraziiExeption();
            }
        } catch (UsernameNotFoundException e) {
            Sotrudnik sotrudnik = mappingSernise.mahDtoToEntity(dto);
            sotrudnik.setId(null);
            sotrudnik.setPassword(encoder.encode(sotrudnik.getPassword()));
            sotrudnik.setActive(false);
            List<Role> list = new ArrayList<>();
            list.add(roleServise.procitatPoNaimenovanie("ROLE_USER"));
            sotrudnik.setRoles(list);

            repozitory.save(sotrudnik);
            emailServise.sendConfirmationEmail(sotrudnik);
        }

    }

    @Transactional
    @Override
    public void confirmation(String code) {

        ConfirmationCode confirmationCode = confirmationServise.procitatPoCodu(code);
        LocalDateTime tecVremya = LocalDateTime.now();
        LocalDateTime expired = confirmationCode.getExpired();

        if (tecVremya.isAfter(expired)) {
            throw new IstekSrokDeistviyaCodaExeption();
        }

        confirmationCode.getSotrudnik().setActive(true);

    }
}
