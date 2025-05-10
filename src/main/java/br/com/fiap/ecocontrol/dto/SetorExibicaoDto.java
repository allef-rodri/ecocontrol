package br.com.fiap.ecocontrol.dto;

import br.com.fiap.ecocontrol.model.Setor;
import jakarta.persistence.Column;

public record SetorExibicaoDto(
        Long idSetor,
        String deSetor,
        String localizacao
) {

    public SetorExibicaoDto(Setor setor) {
        this(
                setor.getIdSetor(),
                setor.getDeSetor(),
                setor.getLocalizacao()
        );
    }

}
