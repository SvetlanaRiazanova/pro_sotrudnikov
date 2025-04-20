package di.aittr.pro_sotrudnikov.servise.mapping;

import di.aittr.pro_sotrudnikov.domen.entity.Role;
import di.aittr.pro_sotrudnikov.domen.entity.Sotrudnik;
import di.aittr.pro_sotrudnikov.repozitory.SotrudnikRepozitory;
import di.aittr.pro_sotrudnikov.servise.interfaces.SotrudnikServise;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SotrudnikServiseImpl implements SotrudnikServise {

    private final SotrudnikRepozitory repozitory;
    private final BCryptPasswordEncoder encoder;
    private final RoleServiseImpl roleServise;


    public SotrudnikServiseImpl(SotrudnikRepozitory repozitory, BCryptPasswordEncoder encoder, RoleServiseImpl roleServise) {
        this.repozitory = repozitory;
        this.encoder = encoder;
        this.roleServise = roleServise;
    }

    @Override
    @Transactional
    public Sotrudnik sozdat(Sotrudnik sotrudnik) {
        sotrudnik.setPassword(encoder.encode(sotrudnik.getPassword()));
        List<Role> list = new ArrayList<>();
        list.add(roleServise.procitatPoNaimenovanie("ROLE_USER"));
        sotrudnik.setRoles(list);

        return repozitory.save(sotrudnik);
    }

    @Override
    public List<Sotrudnik> procitatVseh() {
        return repozitory.findAll();
    }

    @Override
    public Sotrudnik procitatPoId(Long id) {
        return repozitory.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void obnovitPoId(Sotrudnik sotrudnik) {
        Long id = sotrudnik.getId();
        Sotrudnik sushestvSotrudnik = procitatPoId(id);
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

}
