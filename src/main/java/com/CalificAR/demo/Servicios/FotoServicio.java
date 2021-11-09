package com.CalificAR.demo.Servicios;

import java.io.IOException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.CalificAR.demo.Entidades.Foto;
import com.CalificAR.demo.Errores.ErrorServicio;
import com.CalificAR.demo.Repositorio.FotoRepositorio;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FotoServicio {

    @Autowired
    private FotoRepositorio fotoRepositorio;

    @Transactional
    public Foto guardar(String idFoto, MultipartFile archivo) throws ErrorServicio {
        if (archivo != null && !archivo.isEmpty()) {
            try {
                Foto foto = new Foto();
                if (idFoto != null) {
                    Optional<Foto> respuesta = fotoRepositorio.findById(idFoto);
                    if (respuesta.isPresent()) {
                        foto = respuesta.get();
                    }
                }
                foto.setMime(archivo.getContentType());
                foto.setNombre(archivo.getName());
                foto.setArchivo(archivo.getBytes());
                return fotoRepositorio.save(foto);
            } catch (IOException e) {
                throw new ErrorServicio(e.getMessage());
            }
        }
        return null;
    }
    
    // Método para testeo con Postman
    public Foto guardar(FotoRepositorio fotoRepositorio, String idFoto, MultipartFile archivo) throws ErrorServicio {
    	this.fotoRepositorio = fotoRepositorio;
        return guardar(idFoto, archivo);
    }
}
