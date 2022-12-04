package br.com.springboot.curso_springboot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *O controller serve para interceptar os dados de uma aplication, primeiro passa pelo controler pra depois usar o methodo
 *O context é uma url especifica
 */
@RestController  //intercepta todas as requisicoes que forem feito o mapeamento
public class GreetingsController {
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello SpringBoot " + name + "!";
    }
}
