package com.dao;

import com.entidad.Ejemplar;
import com.entidad.Marc001;
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
    
    String insertarDocumental(Marc001 marc001, Marc017 marc017, Marc100 marc100, Marc245 marc245, Marc250 marc250, Marc260 marc260, Marc300 marc300, Marc504 marc504, List<Marc500> listaMarc500, List<Ejemplar> listaEjemplar);

    
}
