package br.com.fiap.ecocontrol.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EquipamentoCadastroDto(
        @NotBlank(message = "A descrição do equipamento é obrigatória")
        @Size(max = 100, message = "A descrição deve ter no máximo 100 caracteres")
        String deEquipamento,

        @NotBlank(message = "O tipo do equipamento é obrigatório")
        @Size(max = 100, message = "O tipo deve ter no máximo 100 caracteres")
        String tipo,

        @NotNull(message = "O consumo máximo é obrigatório")
        @Positive(message = "O consumo máximo deve ser maior que zero")
        Double consumoMaximo,

        @NotBlank(message = "O status é obrigatório")
        @Size(max = 20, message = "O status deve ter no máximo 20 caracteres")
        String status,

        @NotNull(message = "O setor é obrigatório")
        Long idSetor
) {}
