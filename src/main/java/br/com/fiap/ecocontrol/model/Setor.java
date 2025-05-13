package br.com.fiap.ecocontrol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "DB_SETOR")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Setor {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_DB_SETOR"
    )
    @SequenceGenerator(
            name = "SEQ_DB_SETOR",
            sequenceName = "SEQ_DB_SETOR",
            allocationSize = 1
    )
    @Column(name = "id_setor")
    private Long idSetor;

    @Column(name = "de_setor")
    private String deSetor;
    @Column(name = "localizacao")
    private String localizacao;

    @OneToMany(mappedBy = "setor", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Equipamento> equipamentos;

    public Long getIdSetor() {
        return idSetor;
    }

    public String getDeSetor() {
        return deSetor;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public List<Equipamento> getEquipamentos() {
        return equipamentos;
    }

    public void setDeSetor(String deSetor) {
        this.deSetor = deSetor;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setEquipamentos(List<Equipamento> equipamentos) {
        this.equipamentos = equipamentos;
    }
}