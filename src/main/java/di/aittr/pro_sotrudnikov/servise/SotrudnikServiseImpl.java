package di.aittr.pro_sotrudnikov.servise;

import di.aittr.pro_sotrudnikov.domen.dto.SotrudnikDto;
import di.aittr.pro_sotrudnikov.domen.dto.ZadacaDto;
import di.aittr.pro_sotrudnikov.domen.entity.Role;
import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.domen.entity.Zadaca;
import di.aittr.pro_sotrudnikov.repozitory.SotrudnikRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import di.aittr.pro_sotrudnikov.servise.mapping.SotrudnikMappingSernise;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SotrudnikServiseImpl implements SotrudnikServise {

    private final SotrudnikRepozitory repozitory;
    private final BCryptPasswordEncoder encoder;
    private final RoleServiseImpl roleServise;
    private final SotrudnikMappingSernise mappingSernise;


    public SotrudnikServiseImpl(SotrudnikRepozitory repozitory, BCryptPasswordEncoder encoder, RoleServiseImpl roleServise, SotrudnikMappingSernise mappingSernise) {
        this.repozitory = repozitory;
        this.encoder = encoder;
        this.roleServise = roleServise;
        this.mappingSernise = mappingSernise;
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
        Sotrudnik sotrudnik = repozitory.findById(id).orElse(null);
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

}
