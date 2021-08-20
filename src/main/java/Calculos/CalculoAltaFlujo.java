package Calculos;

import Entidades.BCNIIFLUJ;
import Entidades.BCNIIFALT;
import Utilidades.FechasManejo;
import Utilidades.Utilidades;

import java.text.ParseException;
import java.util.ArrayList;

public class CalculoAltaFlujo {

    public static String FECHAHASTAN; //para calc intereses
    
    public static ArrayList<BCNIIFLUJ> calculoAltaFlujo(ArrayList<BCNIIFLUJ> arrListFlujo, BCNIIFALT datosAltaObj) throws ParseException {

        //FOR - CALCULOS DIAS FUTUROS, IMPORTE DESCONTADO, TIR CUOTA
        for (int i = 0; i < arrListFlujo.size(); i++) {
            BCNIIFLUJ flujoAuxiliar = arrListFlujo.get(i);

            //1. DIAS FUTUROS
            //Calculos diasFuturos = fechHasta - fechCorte; 
            //Fecha Hasta BCNIIFLUJ
            String fechaHasta = flujoAuxiliar.getFechHasta();
            //Fecha de Corte BCNIIFALT
            String fechCorte = datosAltaObj.getFechCorte();
            Utilidades.asignarTIR(fechCorte);

            //Obtenemos los dias Futuros
            int aux = FechasManejo.restaDias(fechaHasta, fechCorte);
            //se los cargamos al ArrayList
            flujoAuxiliar.setDiasFuturos(aux);

            //2.IMPORTE DESCONTADO
            if (arrListFlujo.get(i).getDiasFuturos() > 0) {
                if (datosAltaObj.getMoneda().equals("Dolares")) {
                    //Calculo IF de Importe Descontado - Dolares
                    double numerador = flujoAuxiliar.getImpMonOrig();
                    double exponente = flujoAuxiliar.getDiasFuturos() / 30;
                    double base = 1 + datosAltaObj.getTasDescDol() * 30 / 365;
                    double denominador = Math.pow(base, exponente);
                    flujoAuxiliar.setImporDesc(numerador / denominador);
                } else {
                    //Calculo IF de Importe Descontado - Pesos
                    double numerador = flujoAuxiliar.getImpMonOrig();
                    double exponente = (double) flujoAuxiliar.getDiasFuturos() / 30;
                    double base = 1 + datosAltaObj.getTasDescPes() * 30 / 365;
                    double denominador = Math.pow(base, exponente);
                    flujoAuxiliar.setImporDesc(numerador / denominador);
                }
            }
            //Va sumando para obtener el 1er valor de la columna SALDO 
            BCNIIFLUJ.SALDO = flujoAuxiliar.getImporDesc() + BCNIIFLUJ.SALDO;

            if (flujoAuxiliar.getImporDesc() > 0) {
                BCNIIFLUJ.PLAZO++;
            }

            //3. CALCULO TIR CUOTA (valor negativo):
            flujoAuxiliar.setTirCuota(-flujoAuxiliar.getImpMonOrig());
            //CALCULO TIR SUMATORIA (esta variable sera calculada y guardada en el objeto.
            double auxD4 = flujoAuxiliar.getImporDesc() + flujoAuxiliar.getSumatoriaTIR();
            flujoAuxiliar.setSumatoriaTIR(auxD4); //Para calcular % TIR

            //Asignamos nuevamente el objeto auxiliar al ArrayList
            arrListFlujo.set(i, flujoAuxiliar);

        }//Fin del FOR

        return arrListFlujo;
    }

    //Calculo Saldo, Intereses, Amortizacion
    public static ArrayList<BCNIIFLUJ> calculosSegundaTanda(ArrayList<BCNIIFLUJ> flujo) throws ParseException {

        for (int i = 0; i < flujo.size(); i++) {
            //Crea el objeto en base al flujo en cada iteracion
            BCNIIFLUJ flujoAux = flujo.get(i);

            //INTERESES
            //1er cuota
            if (i == 0) {
                double intereses = intereses(flujoAux);
                flujoAux.setIntereses(intereses); //cargamos los intereses al objeto
            }
            
           
            
            //CARGA DEL SALDO DE LA 1ER CUOTA EN VARIABLE INSTANCIA
            if(i==0){
                double saldo = flujoAux.SALDO;
                flujoAux.setSaldo(saldo); //cargamos al objeto con el saldo actual
            }
            
            //CALCULO DEL SALDO A PARTIR DE LA 2DA CUOTA EN ADELANTE
            if(i>0){
            double saldoActual = flujo.get(i-1).getSaldo() + flujo.get(i-1).getIntereses()- flujo.get(i-1).getImpMonOrig();
            flujoAux.setSaldo(saldoActual);//carga el saldo actual al objeto
            }
            
            //INTERESES 2da cuota en adelante
            if(i>0){
                double intereses = interesesPlus(flujoAux, flujo, i);
                flujoAux.setIntereses(intereses);
            }
            //8. AMORTIZACIONES: 
            double amort = (flujoAux.SALDO / flujo.get(0).PLAZO);
            flujoAux.setAmortizac(amort);

            //Devuelve los datos al flujo en base al objeto
            flujo.set(i, flujoAux);
        }//Fin del FOR
        for (int i = 0; i < flujo.size(); i++) {
            System.out.println(flujo.get(i));
        }
        //devuelve el flujo
        return flujo;
    }

    public static double intereses(BCNIIFLUJ flujoAux) throws ParseException {
        double saldo = flujoAux.SALDO;
        double unoMasTir = 1 + flujoAux.TIR;
        //La fechaHasta es la fechaHasta en el momento n 
//               String fechHasta = flujoAux.getFechHasta();
        //La fechaCorte es la fechaHasta en el momento n-1
//               String fechCorte = flujoAux.FECHACORTE;
        int diferenciaFechas = FechasManejo.restaDias(flujoAux.getFechHasta(), flujoAux.FECHACORTE);
        //Guardo la fecha Hasta en el momento n para ser utilizada en el flujo posterior
        
        
        double difFechasSobr365 = (double) diferenciaFechas / 365;
        double potencia = Math.pow(unoMasTir, difFechasSobr365);
        double potenciaMenosUno = potencia - 1;
        double intereses = saldo * potenciaMenosUno;
        return intereses;

    }

    public static double interesesPlus(BCNIIFLUJ flujoAux, ArrayList<BCNIIFLUJ> flujo, int i) throws ParseException {
        double saldo = flujoAux.getSaldo();
        double unoMasTir = 1 + flujoAux.TIR;
        int diferenciaFechas = FechasManejo.restaDias(flujoAux.getFechHasta(), flujo.get(i-1).getFechHasta() );
       
        double difFechasSobr365 = (double) diferenciaFechas / 365;
        double potencia = Math.pow(unoMasTir, difFechasSobr365);
        double potenciaMenosUno = potencia - 1;
        double intereses = saldo * potenciaMenosUno;
        return intereses;

    }
    
}
