package com.idat.edu.pe.EvaluacionFinal.service;

import com.idat.edu.pe.EvaluacionFinal.model.Equipo;
import com.idat.edu.pe.EvaluacionFinal.model.Partido;
import com.idat.edu.pe.EvaluacionFinal.repository.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class EquipoServicio {
    @Autowired
    EquipoRepository equipoRepository;

    public ArrayList<Equipo> obtenerEquipo(){ return (ArrayList<Equipo>) equipoRepository.findAll();}

    public Equipo guardarEquipo(Equipo equipo){
        return equipoRepository.save(equipo);
    }

    public Optional<Equipo> obtenerEquipoPorId(Long id){
        return equipoRepository.findById(id);
    }

    public boolean eliminarEquipo(Long id){
        try{
            equipoRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

    public Equipo actualizarEquipo (Long id, Equipo equipo){
        Optional<Equipo> equipoExistenteOpcional = this.obtenerEquipoPorId(id);
        if(equipoExistenteOpcional.isPresent()){
            Equipo equipoActualizado = equipoExistenteOpcional.get();
            if(equipo.getNombre()!=null){
                equipoActualizado.setNombre(equipo.getNombre());
            }
            if(equipo.getPais()!=null){
                equipoActualizado.setPais(equipo.getPais());
            }
            if(equipo.getFechaFundacion()!=null){
                equipoActualizado.setFechaFundacion(equipo.getFechaFundacion());
            }
            if(equipo.getPresidente()!=null){
                equipoActualizado.setPresidente(equipo.getPresidente());
            }
            return equipoRepository.save(equipoActualizado);
        }else{
            return null;
        }
    }

}
