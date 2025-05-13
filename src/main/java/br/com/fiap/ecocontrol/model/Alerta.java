package br.com.fiap.ecocontrol.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "DB_ALERTA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Alerta {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_DB_ALERTA"
    )
    @SequenceGenerator(
            name = "SEQ_DB_ALERTA",
            sequenceName = "SEQ_DB_ALERTA",
            allocationSize = 1
    )
    @Column(name = "id_alerta")
    private Long idAlerta;

    @Column(name = "tipo_alerta")
    private String tipoAlerta;

    @Column(name = "mensagem")
    private String mensagem;
    @Column(name = "dh_alerta")
    private LocalDateTime dataHoraAlerta;

    @ManyToOne
    @JoinColumn(name = "id_equipamento", referencedColumnName = "id_equipamento")
    private Equipamento equipamento;

    public Long getIdAlerta() {
        return idAlerta;
    }

    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataHoraAlerta() {
        return dataHoraAlerta;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setDataHoraAlerta(LocalDateTime dataHoraAlerta) {
        this.dataHoraAlerta = dataHoraAlerta;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
}
