package br.com.uniamerica.estacionamento.controller;

import br.com.uniamerica.estacionamento.controller.exeption.NotFoundException;
import br.com.uniamerica.estacionamento.dtos.MarcaDTOS;
import br.com.uniamerica.estacionamento.entity.Marca;
import br.com.uniamerica.estacionamento.service.MarcaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/marca")
public class MarcaController {

    @Autowired
    private MarcaService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )

    public ResponseEntity<?> findById(
            @PathVariable(value = "id") Long id
    ){
        return service.findById(id);
    };

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }




    @RequestMapping(value = "/all", method = RequestMethod.GET)

    public ResponseEntity<?> findAll( ){
        return service.findAll();
    };
    @RequestMapping(value = "/ativos", method = RequestMethod.GET)
    public ResponseEntity<?> findAtivo( ){
        return service.findAtivo();
    };

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid MarcaDTOS marcaDTOS){

        return service.create(marcaDTOS);
    }





    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateEntity(@PathVariable Long id, @RequestBody  @Valid MarcaDTOS marcaDTOS) {

        return service.update(id, marcaDTOS);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        return  service.delete(id);
    }
//    }



}
