package com.app.complementarioservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.complementarioservice.entity.Atraso;
import com.app.complementarioservice.repository.AtrasoRepository;

@Service
public class AtrasoService {
    
    @Autowired
    AtrasoRepository atrasoRepository;

    RestTemplate restTemplate = new RestTemplate();

    public List<Atraso> obtenerAtrasos(){
        return atrasoRepository.findAll();
    }

    public Atraso obtenerAtraso(Long id){
        return atrasoRepository.findById(id).orElse(null);
    }

    public List<Integer> verificarSiTieneAtrasos(int mes, int anio, String rut){
        List<Integer> tiempos = new ArrayList<>();
        Atraso atraso = atrasoRepository.findAtrasoEmpleadoByFecha(rut, mes, anio);
        if(atraso == null){
            tiempos.add(0);
            tiempos.add(0);
            tiempos.add(0);
            return tiempos;
        }
        tiempos.add(atraso.getAtraso10min());
        tiempos.add(atraso.getAtraso25min());
        tiempos.add(atraso.getAtraso45min());
        return tiempos;
    }

    public boolean guardarDelArchivo(){
        String[] ruts = restTemplate.getForObject("http://localhost:8001/ingresosSalidas/atrasos/rut", String[].class);
        String fecha = restTemplate.getForObject("http://localhost:8001/ingresosSalidas/fecha", String.class);
        if(ruts == null || fecha == null){
            return false;
        }
        String[] fechaSeparada = fecha.split("-");
        int anio = Integer.valueOf(fechaSeparada[0]);
        int mes = Integer.valueOf(fechaSeparada[1]);
        for(String r:ruts){
            Atraso atraso = atrasoRepository.findAtrasoEmpleadoByFecha(r, mes, anio);
            if(atraso == null){
                Integer[] cantidad = restTemplate.getForObject("http://localhost:8001/ingresosSalidas/atrasos/"+r, Integer[].class);
                if(cantidad == null){
                    return false;
                }
                Atraso a = new Atraso(null, mes, anio, cantidad[0], cantidad[1], cantidad[2], r);
                atrasoRepository.save(a);
            }
        }
        return true;
    }
}
