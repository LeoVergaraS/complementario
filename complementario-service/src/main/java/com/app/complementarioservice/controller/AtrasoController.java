package com.app.complementarioservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.complementarioservice.entity.Atraso;
import com.app.complementarioservice.service.AtrasoService;

@RestController
@RequestMapping("/atrasos")
public class AtrasoController {

    @Autowired
    AtrasoService atrasoService;

    @GetMapping
    public ResponseEntity<List<Atraso>> getAll(){
        List<Atraso> atrasos = atrasoService.obtenerAtrasos();
        if(atrasos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(atrasos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Atraso> getOne(@PathVariable("id") Long id){
        Atraso atraso = atrasoService.obtenerAtraso(id);
        if(atraso == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(atraso);
    }

    @GetMapping("/verificar/{mes}/{anio}/{rut}")
    public ResponseEntity<List<Integer>> verificarAtrasos(@PathVariable("mes") int mes, @PathVariable("anio") int anio, @PathVariable("rut") String rut){
        List<Integer> tiempos = atrasoService.verificarSiTieneAtrasos(mes, anio, rut);
        if(tiempos.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tiempos);
    }

    @PostMapping("/file")
    public ResponseEntity<String> guardarDelArchivo(){
        boolean guardado = atrasoService.guardarDelArchivo();
        if(!guardado){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok("Se guardaron los atrasos");
    }
}
