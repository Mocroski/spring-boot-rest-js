package br.com.springboot.curso_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import br.com.springboot.curso_springboot.model.Usuario;
import br.com.springboot.curso_springboot.repisitory.UsuarioRepository;

/**
 *
 *O controller serve para interceptar os dados de uma aplication, primeiro passa pelo controler pra depois usar o methodo
 *O context é uma url especifica
 */
@RestController  //intercepta todas as requisicoes que forem feito o mapeamento
public class GreetingsController {
	
	@Autowired //injecao de dependencia ou CD/CDI
	private UsuarioRepository usuarioRepository;
    
   
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello SpringBoot " + name + "!";
    }
    
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario usuario = new Usuario();
    	usuario.setNome(nome);
    	usuarioRepository.save(usuario); //grava no banco de dados
    	
    	return "Ola Mundo " + nome;
    	
    }
    
    @GetMapping(value = "listatodos")//primeiro metodo de API
    @ResponseBody /* Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario() {
    	
    	List<Usuario> usuarios =  usuarioRepository.findAll();//executa a consulta no banco de dados
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); //retorna a lista em json
    }
    
    @PostMapping(value = "salvar") //mapea a url
    @ResponseBody
    public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { //recebe os dados para salvar
    	
    	Usuario user = usuarioRepository.save(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    @DeleteMapping(value = "delete") //mapea a url
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idUser) { //recebe os dados para salvar
    	
    	usuarioRepository.deleteById(idUser);//funcao do spring data para deletar do banco faz tudo sozinho
    	
    	return new ResponseEntity<String>("User deletado com sucesso", HttpStatus.OK); //retorna a reposta 
    	
    }
    
    @GetMapping(value = "buscaruserid") //mapea a url
    @ResponseBody
    public ResponseEntity<Usuario> buscarUserId(@RequestParam(name="idUser") Long idUser) { //recebe os dados
    	
    	Usuario usuario = usuarioRepository.findById(idUser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
    
    @PutMapping(value = "atualizar") //mapea a url
    @ResponseBody
    public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) { //recebe os dados para salvar
    	
    	if(usuario.getId() == 0) {
    		return new ResponseEntity<String>("Id nao informado", HttpStatus.OK);
    	}
    	
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
}
