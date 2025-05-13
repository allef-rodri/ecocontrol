package br.com.fiap.ecocontrol.exception.usuario;

public class UsuarioInvalidoException extends RuntimeException {

    public UsuarioInvalidoException(String mensagem) {
        super(mensagem);
    }
}