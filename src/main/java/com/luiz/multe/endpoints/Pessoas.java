package com.luiz.multe.endpoints;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.luiz.multe.dtos.PessoaDTO;
import com.luiz.multe.models.Pessoa;
import com.luiz.multe.repositories.EstadoCivilDAO;
import com.luiz.multe.repositories.PessoaDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/Pessoa")
public class Pessoas {
    public Pessoas() {
		super();
	}

    @Autowired
	PessoaDAO pessoaDAO;

    @Autowired
	EstadoCivilDAO estadoCivilDAO;

    private Gson gson = new Gson();

    @RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, value="save")
    public @ResponseBody String send(RequestEntity<Pessoa> pessoa, HttpServletRequest request, HttpServletResponse response) {
		try {
            Pessoa p = new Pessoa();
            if(pessoa.getBody().getNome().isEmpty()){
                return "O Nome é um campo obrigatório";
            }else{
                p.setNome(pessoa.getBody().getNome());
            }

            if(pessoa.getBody().getDataNascimento().isEmpty()){
                return "A Data de Nascimento é um campo obrigatório";
            }else{
                p.setDataNascimento(pessoa.getBody().getDataNascimento());
            }
            if(pessoa.getBody().getEstadoCivil() == 0){
                return "Informe o estado civil " + gson.toJson(estadoCivilDAO.findAll());
            }else{
                p.setEstadoCivil(pessoa.getBody().getEstadoCivil());
            }

            pessoaDAO.save(p);

            return "Registro Inserido com sucesso";
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping(method=RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, value="update/{id}")
    public @ResponseBody String update(RequestEntity<Pessoa> pessoa, @PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		try {
            Pessoa p = new Pessoa();
            p.setId(id);
            if(!pessoa.getBody().getNome().isEmpty()){
                p.setNome(pessoa.getBody().getNome());
            }

            if(!pessoa.getBody().getDataNascimento().isEmpty()){
                p.setDataNascimento(pessoa.getBody().getDataNascimento());
            }
            
            if(pessoa.getBody().getEstadoCivil() != 0){
                p.setEstadoCivil(pessoa.getBody().getEstadoCivil());
            }

            if(pessoa.getBody().getDependente() != null){
                p.setDependente(pessoa.getBody().getDependente());
            }

            pessoaDAO.save(p);

            return "Registro atualizado com sucesso";
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping(method=RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, value="delete/{id}")
    public @ResponseBody String delete(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) {
		try {
            Pessoa p = new Pessoa();
            p.setId(id);

            pessoaDAO.delete(p);

            return "Registro deletado com sucesso";
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping(method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, value="find")
    public @ResponseBody String find(RequestEntity<Pessoa> pessoa, HttpServletRequest request, HttpServletResponse response) {
		try {
            if(pessoa.getBody().getNome() != null){
                return gson.toJson(pessoaDAO.findByNome(pessoa.getBody().getNome()));    
            }

            if(pessoa.getBody().getDataNascimento() != null){
                return gson.toJson(pessoaDAO.findByData(pessoa.getBody().getDataNascimento()));    
            }

            return gson.toJson(pessoaDAO.findAll());
        }catch(Exception e){
            return e.getMessage();
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="findDTO")
    public ResponseEntity<List<PessoaDTO>> findAll(){
        List<Pessoa> list = pessoaDAO.findAll();
        List<PessoaDTO> dtos = list.stream().map(p -> new PessoaDTO(p)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtos);
    }

}
