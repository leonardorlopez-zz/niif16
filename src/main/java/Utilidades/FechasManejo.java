
package Utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FechasManejo {
    
    public static int restaDias(String fechHasta, String fechCorte) throws ParseException {
        //Definimos el formato de fecha
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        //fechCorte
        Date fechCorteDate = date.parse(fechCorte);
        //fechHasta
        Date fechHastaDate = date.parse(fechHasta);
        //diasFuturos = fechHasta - fechCorte;
        int difference = (int)((fechHastaDate.getTime() - fechCorteDate.getTime())/ 86400000);
        
        return difference;   
    }


}
