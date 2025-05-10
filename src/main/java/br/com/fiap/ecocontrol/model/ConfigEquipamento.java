package br.com.fiap.ecocontrol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "DB_CONFIG_EQUIPAMENTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ConfigEquipamento {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_DB_CONFIG_EQUIPAMENTO"
    )
    @SequenceGenerator(
            name = "SEQ_DB_CONFIG_EQUIPAMENTO",
            sequenceName = "SEQ_DB_CONFIG_EQUIPAMENTO",
            allocationSize = 1
    )
    @Column(name = "id_config")
    private Long idConfig;

    @Column(name = "limite_kwh")
    private Double limiteKwh;

    @ManyToOne
    @JoinColumn(name = "id_equipamento", referencedColumnName = "id_equipamento")
    private Equipamento equipamento;

}