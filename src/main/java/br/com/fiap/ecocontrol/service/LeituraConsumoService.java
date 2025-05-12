package br.com.fiap.ecocontrol.service;

import br.com.fiap.ecocontrol.dto.LeituraConsumoCadastroDto;
import br.com.fiap.ecocontrol.dto.LeituraConsumoExibicaoDto;
import br.com.fiap.ecocontrol.model.Equipamento;
import br.com.fiap.ecocontrol.model.LeituraConsumo;
import br.com.fiap.ecocontrol.repository.EquipamentoRepository;
import br.com.fiap.ecocontrol.repository.LeituraConsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeituraConsumoService {

    @Autowired
    private LeituraConsumoRepository leituraConsumoRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

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

    public LeituraConsumoExibicaoDto cadastrar(LeituraConsumoCadastroDto dto) {
        Equipamento equipamento = equipamentoRepository.findById(dto.idEquipamento())
                .orElseThrow(() -> new IllegalArgumentException("Equipamento não encontrado com ID: " + dto.idEquipamento()));

        LeituraConsumo leitura = new LeituraConsumo();
        leitura.setKwhConsumido(dto.kwhConsumido());
        leitura.setDataHoraLeitura(dto.dataHoraLeitura());
        leitura.setEquipamento(equipamento);

        LeituraConsumo salva = leituraConsumoRepository.save(leitura);

        return new LeituraConsumoExibicaoDto(
                salva.getIdLeitura(),
                salva.getKwhConsumido(),
                salva.getDataHoraLeitura(),
                salva.getEquipamento().getDeEquipamento(),
                salva.getEquipamento().getSetor().getDeSetor()
        );
    }
}
