package br.com.fiap.ecocontrol.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "DB_LEITURA_CONSUMO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class LeituraConsumo {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_DB_LEITURA_CONSUMO"
    )
    @SequenceGenerator(
            name = "SEQ_DB_LEITURA_CONSUMO",
            sequenceName = "SEQ_DB_LEITURA_CONSUMO",
            allocationSize = 1
    )
    @Column(name = "id_leitura")
    private Long idLeitura;

    @Column(name = "dh_leitura")
    private LocalDateTime dataHoraLeitura;

    @Column(name = "kwh_consumido")
    private Double kwhConsumido;

    @ManyToOne
    @JoinColumn(name = "id_equipamento", referencedColumnName = "id_equipamento")
    private Equipamento equipamento;

    public Long getIdLeitura() {
        return idLeitura;
    }

    public LocalDateTime getDataHoraLeitura() {
        return dataHoraLeitura;
    }

    public Double getKwhConsumido() {
        return kwhConsumido;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setDataHoraLeitura(LocalDateTime dataHoraLeitura) {
        this.dataHoraLeitura = dataHoraLeitura;
    }

    public void setKwhConsumido(Double kwhConsumido) {
        this.kwhConsumido = kwhConsumido;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
}