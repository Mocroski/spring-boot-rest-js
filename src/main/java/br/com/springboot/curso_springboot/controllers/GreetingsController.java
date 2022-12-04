package br.com.springboot.curso_springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
}
