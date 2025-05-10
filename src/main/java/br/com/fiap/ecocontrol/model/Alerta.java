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

}