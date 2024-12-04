package com.idat.edu.pe.EvaluacionFinal.service;

import com.idat.edu.pe.EvaluacionFinal.model.Rol;
import com.idat.edu.pe.EvaluacionFinal.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RolServicio {
    @Autowired
    RolRepository rolRepository;

    public ArrayList<Rol> obtenerRol(){
        return (ArrayList<Rol>) rolRepository.findAll();
    }

    public Rol guardarRol(Rol rol){
        return rolRepository.save(rol);
    }

    public Optional<Rol> obtenerRolPorId(Long id){
        return rolRepository.findById(id);
    }

    public boolean eliminarRol(Long id){
        try{
            rolRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }
}
