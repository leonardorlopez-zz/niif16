
package Utilidades;

import Entidades.BCNIIFALT;
import Entidades.BCNIIFLUJ;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.fileupload.FileItem;

public class Utilidades {
    
    public static String transformarFecha(String origen){
        String anio = origen.substring(0, 4);
        String mes = origen.substring(5, 7);
        String dia = origen.substring(8, 10);
        String destino = dia + "/" + mes + "/" + anio;
        return destino;
    }
   
     public static void extractorWords(ArrayList<String> a){
         for (int i = 1; i < a.size(); i++) {
             String[] extraccion = a.get(i).split(";");
             a.set(i, extraccion.toString());
         }
     }
    
     public static double reemplComaPorPunto(String aux){
        aux = aux.replace(",", ".");
        double auxD = Double.valueOf(aux);
        return auxD;
     }
    
      public static void asignarTIR(String fechCorte) {
        String anio = fechCorte.substring(6, 10);
        String mes = fechCorte.substring(3, 5);
        String dia = fechCorte.substring(0, 2);
        String destino = anio +"-" + mes + "-" + dia ;
        
        BCNIIFLUJ.FECHACORTE = fechCorte;
    }

      
      public static String cambiarFormatoFechaTIR(String fecha){
        String anio = fecha.substring(6, 10);
        String mes = fecha.substring(3, 5);
        String dia = fecha.substring(0, 2);
        String destino = anio +"-" + mes + "-" + dia ;
        return destino;
      }

    public static ArrayList<BCNIIFLUJ> copiaAltaAFlujo(BCNIIFALT b1, ArrayList<BCNIIFLUJ> flujo) {
        for (int i = 0; i < flujo.size(); i++) {
            BCNIIFLUJ flujoAux = flujo.get(i);
            flujoAux.setId(b1.getId());
            flujoAux.setIdAgrupador(b1.getIdAgrupador());
            flujoAux.setDescriptn(b1.getDescriptn());
            flujoAux.setProveedor(b1.getProveedor());
            flujoAux.setId(b1.getId());
            flujoAux.setMoneda(b1.getMoneda());
            flujoAux.setFechCorte(b1.getFechCorte());
            flujo.set(i, flujoAux);
        }
        
        return flujo;
    }

    public static BCNIIFALT cargaAlta(Iterator<FileItem> iter) throws Exception {
        BCNIIFALT b1 = new BCNIIFALT();
        while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
                    DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    switch (item.getFieldName()) {

                        case "idAgrupador":
                            b1.setIdAgrupador(Integer.valueOf(item.getString()));
                            break;
                        case "tipoContr":
                            b1.setTipoContr(item.getString());
                            break;
                        case "descrip":
                            b1.setDescriptn(item.getString());
                            break;
                        case "proveed":
                            b1.setProveedor(item.getString());
                            break;
                        case "moned":
                            b1.setMoneda(item.getString());
                            break;
                        case "tipCamb":
                            //Reemplazamos las comas por el punto
                            String aux = item.getString();
                            double auxD = reemplComaPorPunto(aux);
                            //Le asignamos el tipo de cambio
                            b1.setTipCamb(auxD);
                            break;
                        case "tasaDesc":
                            String aux1 = item.getString();
                            double auxD1 = reemplComaPorPunto(aux1);
                            b1.setTasDescPes(auxD1);
                            break;
                        case "tasaDescDol":
                            String aux2 = item.getString();
                            double auxD2 = reemplComaPorPunto(aux2);
                            b1.setTasDescDol(auxD2);
                            break;
                        case "fechAlta": {
                            String auxS = item.getString();
                            String fechaDef = Utilidades.transformarFecha(auxS);
                            b1.setFechAlta(fechaDef);
                            
                        }
                        break;

                        case "fechCort":
                            String aux3 = item.getString();
                            String fechaDef = Utilidades.transformarFecha(aux3);
                            b1.setFechCorte(fechaDef);
                            System.out.println("Fech Corte: " + fechaDef);
                            break;
                    }

                } else {
                    item.write(new File("C:\\Users\\lrlopez\\Documents\\NetBeansProjects\\NIIF16\\src\\main\\webapp\\TXT\\" + item.getName()));
                }
            }
            //Le agrega NV a estado
            b1.setEstado("NV");
            //Guarda en BBDD lo cargado a b1, objeto bcniifalt
//          ProductoDAO.inserta(b1);
            //Seteamos el ID de cada flujo
            BCNIIFALT.ID++;
            b1.setId(BCNIIFALT.ID);
            
      return b1;
    }
      
}
