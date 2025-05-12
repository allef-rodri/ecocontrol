package br.com.fiap.ecocontrol.dto;

import java.time.LocalDateTime;

public record LeituraConsumoCadastroDto(
        Double kwhConsumido,
        LocalDateTime dataHoraLeitura,
        Long idEquipamento
) {}
