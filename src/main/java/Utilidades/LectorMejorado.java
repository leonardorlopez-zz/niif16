package Utilidades;

import Entidades.BCNIIFLUJ;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectorMejorado {

    public static ArrayList<BCNIIFLUJ> lector() throws FileNotFoundException, IOException {

        // Instancia un objeto File de entrada
        File inputFile = new File("C:\\Users\\lrlopez\\Documents\\NetBeansProjects\\NIIF16\\src\\main\\webapp\\TXT\\TXT_Flujo_NIIF16.txt");

        // Construye un BufferedReader
        BufferedReader readerMejorado = new BufferedReader(new FileReader(inputFile));

        // Define variables
        boolean eof = false;
        String lineaLeida = "";

        //declaramos una variable auxiliar para saltear la 1er linea del txt
        //la cual no precisamos
        boolean aux = false;
        //Creamos un ArrayList que tome los datos del txt. a este 
        //ArrayList le vamos a ir sumando objetos BCNIIFLUJ
        ArrayList<BCNIIFLUJ> arr = new ArrayList<BCNIIFLUJ>();

        // Lee el archivo "in" de forma eficiente e imprime los datos en pantalla
        while (!eof) {

            //Inteligencia de Lectura
            if (aux == false) {
                aux = true;
                lineaLeida = readerMejorado.readLine();
                //Saltea la 1er linea
                continue;
            } else {
                //Creamos un objeto de tipo BCNIIFLUJ para cargar los datos
                BCNIIFLUJ lineaObj = new BCNIIFLUJ();
                //Lee desde la segunda linea hasta el final del txt
                lineaLeida = readerMejorado.readLine();
                String[] auxS = new String[3];
                if(lineaLeida==null){
                    break;
                }else{
                auxS = lineaLeida.split(";"); //toma los 3 datos en un vector
                for (int i = 0; i < auxS.length; i++) { //recorre el vector y carga los datos al objeto
                    if (i == 0) {
                        lineaObj.setFechDesde(auxS[i]);
                    }
                    if (i == 1) {
                        lineaObj.setFechHasta(auxS[i]);
                    }
                    if (i == 2) {
                        //Le sacamos el punto al String 77.000
                        auxS[i] = auxS[i].replace(".", "");
                        auxS[i] = auxS[i].replace(",", ".");
                        lineaObj.setImpMonOrig(Double.valueOf(auxS[i]));
                    }

                }
             arr.add(lineaObj);   
            }
            }
        }

        // Cierra el FileReader
        readerMejorado.close();

        return arr;
    }
}
