package br.com.fiap.ecocontrol.dto;

import br.com.fiap.ecocontrol.model.Alerta;
import br.com.fiap.ecocontrol.model.Equipamento;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record AlertaExibicaoDto(
        Long idAlerta,
        String tipoAlerta,
        String mensagem,
        LocalDateTime dataHoraAlerta,
        EquipamentoExibicaoDto equipamento
) {

    public AlertaExibicaoDto(Alerta alerta) {
        this(
                alerta.getIdAlerta(),
                alerta.getTipoAlerta(),
                alerta.getMensagem(),
                alerta.getDataHoraAlerta(),
                new EquipamentoExibicaoDto(alerta.getEquipamento())
        );
    }

}
