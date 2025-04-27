package di.aittr.pro_sotrudnikov.domen.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "confirm_code")
public class ConfirmationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "expired")
    private LocalDateTime expired;

    @ManyToOne
    @JoinColumn(name = "sotrudnik_id")
    private Sotrudnik sotrudnik;

    public ConfirmationCode() {
    }

    public ConfirmationCode(String code, LocalDateTime expired, Sotrudnik sotrudnik) {
        this.code = code;
        this.expired = expired;
        this.sotrudnik = sotrudnik;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpired() {
        return expired;
    }

    public void setExpired(LocalDateTime expired) {
        this.expired = expired;
    }

    public Sotrudnik getSotrudnik() {
        return sotrudnik;
    }

    public void setSotrudnik(Sotrudnik sotrudnik) {
        this.sotrudnik = sotrudnik;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmationCode that = (ConfirmationCode) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(expired, that.expired) && Objects.equals(sotrudnik, that.sotrudnik);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, expired, sotrudnik);
    }
    @Override
    public String toString() {
        return String.format("Код подтверждения: ИД - %d, код - %s, expired -%s, username - %s.",
                id, code, expired, sotrudnik.getUsername());
    }

}
