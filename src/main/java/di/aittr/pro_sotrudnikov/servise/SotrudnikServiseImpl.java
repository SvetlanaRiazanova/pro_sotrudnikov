package di.aittr.pro_sotrudnikov.servise;

import di.aittr.pro_sotrudnikov.domen.dto.SotrudnikDto;
import di.aittr.pro_sotrudnikov.domen.entity.Role;
import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.repozitory.SotrudnikRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.EmailServise;
import di.aittr.pro_sotrudnikov.servise.interfaces.RoleServise;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import di.aittr.pro_sotrudnikov.servise.mapping.SotrudnikMappingSernise;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SotrudnikServiseImpl implements SotrudnikServise {

    private final SotrudnikRepozitory repozitory;
    private final BCryptPasswordEncoder encoder;
    private final RoleServise roleServise;
    private final SotrudnikMappingSernise mappingSernise;
    private final EmailServise emailServise;


    public SotrudnikServiseImpl(SotrudnikRepozitory repozitory, BCryptPasswordEncoder encoder, RoleServise roleServise, SotrudnikMappingSernise mappingSernise, EmailServise emailServise) {
        this.repozitory = repozitory;
        this.encoder = encoder;
        this.roleServise = roleServise;
        this.mappingSernise = mappingSernise;
        this.emailServise = emailServise;
    }

    @Override
    @Transactional
    public SotrudnikDto sozdat(SotrudnikDto dto) {
        Sotrudnik entity = mappingSernise.mahDtoToEntity(dto);
        entity.setPassword(encoder.encode(entity.getPassword()));
        List<Role> list = new ArrayList<>();
        list.add(roleServise.procitatPoNaimenovanie("ROLE_USER"));
        entity.setRoles(list);
        entity = repozitory.save(entity);
        return mappingSernise.mapEntityToDto(entity);

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
        Sotrudnik sotrudnik = procitatEntityPoId(id);
        return mappingSernise.mapEntityToDto(sotrudnik);
    }

    @Transactional
    @Override
    public void obnovitPoId(SotrudnikDto sotrudnik) {
        Long id = sotrudnik.getId();
        SotrudnikDto sushestvSotrudnik = procitatPoId(id);
        sushestvSotrudnik.setImya(sotrudnik.getImya());

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
        Sotrudnik sotrudnik = repozitory.findById(sotrudnikId).orElse(null);
        return sotrudnik;
    }

    @Override
    public void register(Sotrudnik sotrudnik) {
        sotrudnik.setId(null);
        sotrudnik.setPassword(encoder.encode(sotrudnik.getPassword()));
        List<Role> list = new ArrayList<>();
        list.add(roleServise.procitatPoNaimenovanie("ROLE_USER"));
        sotrudnik.setRoles(list);

        repozitory.save(sotrudnik);
        emailServise.sendConfirmationEmail(sotrudnik);

    }

}
