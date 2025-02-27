package br.com.economigos.service.controler;

import br.com.economigos.service.controler.form.UsuarioLoginForm;
import br.com.economigos.service.model.Sessao;
import br.com.economigos.service.model.Usuario;
import br.com.economigos.service.controler.dto.UsuarioDto;
import br.com.economigos.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/economigos/sessao")
public class SessaoControler {

    private Sessao sessao;
    private List<Usuario> usuarios;
    @Autowired
    private UsuarioRepository usuarioRepository;

/////CRIAR ENDPOINT PARA RETORNAR TODOS OS LOGADOS ATRUALMENTE

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> logar(@RequestBody @Valid UsuarioLoginForm form){
        usuarios = usuarioRepository.findByLogin(form.getEmail(), form.getSenha());

        if(!usuarios.isEmpty()){
            sessao = new Sessao((UsuarioDto.converter(usuarios)).get(0));
            return ResponseEntity.ok(sessao);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/logout")
    @Transactional
    public ResponseEntity<?> logout() {

        if(sessao.getStatus()){
            sessao.logout();
            return ResponseEntity.ok(sessao);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

}
