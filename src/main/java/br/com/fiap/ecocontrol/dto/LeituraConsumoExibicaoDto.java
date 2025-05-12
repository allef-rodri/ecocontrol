package br.com.fiap.ecocontrol.dto;

import java.time.LocalDateTime;

public record LeituraConsumoExibicaoDto(
        Long idLeitura,
        Double kwhConsumido,
        LocalDateTime dataHoraLeitura,
        String nomeEquipamento,
        String nomeSetor
) {}
