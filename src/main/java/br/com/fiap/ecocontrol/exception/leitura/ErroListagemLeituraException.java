package br.com.fiap.ecocontrol.exception.leitura;

public class ErroListagemLeituraException extends RuntimeException {
    public ErroListagemLeituraException(String message) {
        super(message);
    }
}