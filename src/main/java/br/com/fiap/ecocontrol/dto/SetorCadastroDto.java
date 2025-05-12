package br.com.fiap.ecocontrol.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SetorCadastroDto(
        @NotBlank(message = "A descrição do setor é obrigatória")
        @Size(max = 100, message = "A descrição do setor deve ter no máximo 100 caracteres")
        String deSetor,

        @NotBlank(message = "A localização é obrigatória")
        @Size(max = 200, message = "A localização deve ter no máximo 200 caracteres")
        String localizacao
) {}

