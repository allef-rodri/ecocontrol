package br.com.fiap.ecocontrol.service;
import br.com.fiap.ecocontrol.exception.usuario.UsuarioNaoEncontradoException;
import br.com.fiap.ecocontrol.model.Usuario;
import br.com.fiap.ecocontrol.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Usuario usuario = repository.findByLogin(username)
                    .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário com login '" + username + "' não encontrado"));

            return User.builder()
                    .username(usuario.getLogin())
                    .password(usuario.getSenha())
                    .roles(usuario.getRole())
                    .build();

        } catch (UsuarioNaoEncontradoException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Erro inesperado ao buscar usuário", e);
        }
    }
}
