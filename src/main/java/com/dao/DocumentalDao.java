package com.dao;

import com.entidad.Documental;
import com.entidad.Ejemplar;
import com.entidad.Marc017;
import com.entidad.Marc100;
import com.entidad.Marc245;
import com.entidad.Marc250;
import com.entidad.Marc260;
import com.entidad.Marc300;
import com.entidad.Marc500;
import com.entidad.Marc504;
import java.util.List;

public interface DocumentalDao {
    
   int insertarDocumental(Documental documental, List<Marc017> marc017, List<Marc100> listaMarc100, List<Marc245> listaMarc245, List<Marc250> listaMarc250, List<Marc260> listaMarc260, List<Marc300> listaMarc300, List<Marc504> listaMarc504, List<Marc500> listaMarc500, List<Ejemplar> listaEjemplar);

    
}
