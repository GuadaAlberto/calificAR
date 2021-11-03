package com.CalificAR.demo.Servicios;

import com.CalificAR.demo.Entidades.Asistencia;
import com.CalificAR.demo.Entidades.Materia;
import com.CalificAR.demo.Repositorio.AsistenciaRepositorio;
import java.time.LocalDate;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsistenciaServicio {

    @Autowired
    private AsistenciaRepositorio asistenciaRepositorio;

    @Transactional
    public void crearAsistencia(Materia materia) {
        Asistencia asistencia = new Asistencia();
        //      Primero anio con 4 digitos, 2do mes con dos digitos // 3ero dia con 2 digitos
//        LocalDate fecha = new LocalDate(leer.nextInt(),leer.nextInt(),leer.nextInt());

//       LocalLocalDate fechaI= LocalLocalDate.of(anioI, mesI, diaI);

        asistencia.setFecha(LocalDate.now());
        asistencia.setEstado(true);
        asistencia.setMateria(materia);
        asistenciaRepositorio.save(asistencia);
    }

    @Transactional
    public Asistencia consultarAsistencia(LocalDate fechaDate){
         Asistencia consulta = asistenciaRepositorio.buscarAsistenciaFecha(fechaDate);
            return consulta;
        }
    
public void crearAsistencia(AsistenciaRepositorio asistenciaRepositorio,Materia materia) {
        this.asistenciaRepositorio = asistenciaRepositorio;
        crearAsistencia(materia);
    }

    // Método para testeos con Postman
    public Asistencia fechaAsistencia(AsistenciaRepositorio asistenciaRepositorio, LocalDate fecha) {
        this.asistenciaRepositorio = asistenciaRepositorio;
        return consultarAsistencia(fecha);

    }
}
