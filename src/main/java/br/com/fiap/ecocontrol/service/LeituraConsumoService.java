package br.com.fiap.ecocontrol.service;

import br.com.fiap.ecocontrol.dto.LeituraConsumoExibicaoDto;
import br.com.fiap.ecocontrol.model.LeituraConsumo;
import br.com.fiap.ecocontrol.repository.LeituraConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeituraConsumoService {

    @Autowired
    private LeituraConsumoRepository leituraConsumoRepository;

    public List<LeituraConsumoExibicaoDto> listar() {
        return leituraConsumoRepository.findAll().stream().map(leitura ->
                new LeituraConsumoExibicaoDto(
                        leitura.getIdLeitura(),
                        leitura.getKwhConsumido(),
                        leitura.getDataHoraLeitura(),
                        leitura.getEquipamento().getDeEquipamento(),
                        leitura.getEquipamento().getSetor().getDeSetor()
                )
        ).toList();
    }



}
