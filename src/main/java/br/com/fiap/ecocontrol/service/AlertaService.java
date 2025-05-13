package br.com.fiap.ecocontrol.service;

import br.com.fiap.ecocontrol.dto.AlertaExibicaoDto;
import br.com.fiap.ecocontrol.exception.alerta.ErroListagemAlertasException;
import br.com.fiap.ecocontrol.repository.AlertaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    public Page<AlertaExibicaoDto> listarTodosOsAlertas(Pageable paginacao) {
        try {
            return alertaRepository
                    .findAll(paginacao)
                    .map(AlertaExibicaoDto::new);
        } catch (Exception e) {
            throw new ErroListagemAlertasException("Erro ao listar os alertas: " + e.getMessage());
        }
    }

}
