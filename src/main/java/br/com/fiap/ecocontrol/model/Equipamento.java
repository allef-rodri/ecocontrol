package br.com.fiap.ecocontrol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "DB_EQUIPAMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Equipamento {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_DB_EQUIPAMENTO"
    )
    @SequenceGenerator(
            name = "SEQ_DB_EQUIPAMENTO",
            sequenceName = "SEQ_DB_EQUIPAMENTO",
            allocationSize = 1
    )
    @Column(name = "id_equipamento")
    private Long idEquipamento;

    @Column(name = "de_equipamento")
    private String deEquipamento;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "consumo_maximo")
    private Double consumoMaximo;

    @Column(name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_setor", referencedColumnName = "id_setor")
    private Setor setor;

    @OneToMany(mappedBy = "equipamento", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<LeituraConsumo> consumos;

    public Long getIdEquipamento() {
        return idEquipamento;
    }

    public String getDeEquipamento() {
        return deEquipamento;
    }

    public String getTipo() {
        return tipo;
    }

    public Double getConsumoMaximo() {
        return consumoMaximo;
    }

    public String getStatus() {
        return status;
    }

    public Setor getSetor() {
        return setor;
    }

    public List<LeituraConsumo> getConsumos() {
        return consumos;
    }

    public void setDeEquipamento(String deEquipamento) {
        this.deEquipamento = deEquipamento;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setConsumoMaximo(Double consumoMaximo) {
        this.consumoMaximo = consumoMaximo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public void setConsumos(List<LeituraConsumo> consumos) {
        this.consumos = consumos;
    }
}
