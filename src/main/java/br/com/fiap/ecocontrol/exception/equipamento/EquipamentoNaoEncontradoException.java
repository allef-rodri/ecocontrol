package br.com.fiap.ecocontrol.exception.equipamento;

public class EquipamentoNaoEncontradoException extends RuntimeException {
    public EquipamentoNaoEncontradoException(String message) {
        super(message);
    }
}
