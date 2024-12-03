package com.idat.edu.pe.EvaluacionFinal.service;

import com.idat.edu.pe.EvaluacionFinal.model.Descuento;
import com.idat.edu.pe.EvaluacionFinal.repository.DescuentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DescuentoServicio {
    @Autowired
    DescuentoRepository descuentoRepository;

    public ArrayList<Descuento> obtenerDescuentos(){
        return (ArrayList<Descuento>) descuentoRepository.findAll();
    }

    public Descuento guardarDescuento(Descuento descuento){return descuentoRepository.save(descuento);}

    public boolean eliminarDescuento(Long id){
        try{
            descuentoRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }
    }

    public Optional<Descuento> obtenerPorCodigo(String codigo){return descuentoRepository.findByCodigoDescuento(codigo);}

    public ArrayList<Descuento> obtenerUsosDescuento(Long idDescuento){
        ArrayList<Descuento> descuentoPorID= new ArrayList<>();
        ArrayList<Descuento> allDescuentos =(ArrayList<Descuento>) descuentoRepository.findAll();
        for (Descuento descuento : allDescuentos){
            if (descuento.getId().equals(idDescuento)){
                descuentoPorID.add(descuento);
            }
        }
        return descuentoPorID;
    }



}
