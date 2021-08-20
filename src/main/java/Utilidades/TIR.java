package Utilidades;

import Entidades.BCNIIFLUJ;
import java.util.ArrayList;
import org.decampo.xirr.NewtonRaphson;
import org.decampo.xirr.Transaction;
import org.decampo.xirr.Xirr;

public class TIR {

    public static ArrayList<BCNIIFLUJ> xirr(ArrayList<BCNIIFLUJ> flujo) {
        double[] vecTirCuota = new double[flujo.size()];
        String[] vecTirFecha = new String[flujo.size()];
        
 //Creamos dos arrays y los cargamos con la tir importe y la fecha hasta de cada cuota
        for (int i = 0; i < flujo.size(); i++) {
            BCNIIFLUJ fl = flujo.get(i);
            vecTirCuota[i] = fl.getTirCuota();
            vecTirFecha[i] = Utilidades.cambiarFormatoFechaTIR(fl.getFechHasta());
        }
        
        //Array que contiene las transactions en base al tamaño del flujo- permite calcular la TIR% dinamicamente
        Transaction [] array = new Transaction[flujo.size()+1]; //es +1 porque el flujo viene con 43 lugares, tengo que agregar las variables estaticas lo cual genera 44 lugares
        
        //Le cargamos al array en el subindice cero el SALDO y la FECHA corte, variables estaticas
        array[0] = new Transaction(BCNIIFLUJ.SALDO, Utilidades.cambiarFormatoFechaTIR(BCNIIFLUJ.FECHACORTE));
        //Cargamos las siguientes intancias de Transaction
        for (int i = 1; i < array.length; i++) {
            array[i] = new Transaction(vecTirCuota[i-1], vecTirFecha[i-1]);
        }
//Obtenemos la TIR porcentaje                
double rate1 = Xirr.builder().withNewtonRaphsonBuilder(
        NewtonRaphson.builder().withIterations(1000).withTolerance(0.0001)).withGuess(.20).withTransactions(
        array).xirr();
System.out.println(rate1); // Prints 0.2504234710540838
        //La TIR% del flujo la guardamos en el 1er objeto del ArrayList
        //queda guardada en la variable estatica
         flujo.get(0).TIR = rate1; 

         return flujo;

    }
}

/*
ejemplo de como seria un flujo estatico, no necesita el for
 double rate1 = Xirr.builder().withNewtonRaphsonBuilder(
                NewtonRaphson.builder().withIterations(1000).withTolerance(0.0001)).withGuess(.20).withTransactions(
                new Transaction(BCNIIFLUJ.SALDO, Utilidades.cambiarFormatoFechaTIR(BCNIIFLUJ.FECHACORTE)),
                new Transaction(vecTirCuota[0], vecTirFecha[0]),
                new Transaction(vecTirCuota[1], vecTirFecha[1]),
                new Transaction(vecTirCuota[2], vecTirFecha[2]),
                new Transaction(vecTirCuota[3], vecTirFecha[3]),
                new Transaction(vecTirCuota[4], vecTirFecha[4]),
                new Transaction(vecTirCuota[5], vecTirFecha[5]),
                new Transaction(vecTirCuota[6], vecTirFecha[6]),
                new Transaction(vecTirCuota[7], vecTirFecha[7]),
                new Transaction(vecTirCuota[8], vecTirFecha[8]),
                new Transaction(vecTirCuota[9], vecTirFecha[9]),
                new Transaction(vecTirCuota[10], vecTirFecha[10]),
                new Transaction(vecTirCuota[11], vecTirFecha[11]),
                new Transaction(vecTirCuota[12], vecTirFecha[12]),
                new Transaction(vecTirCuota[13], vecTirFecha[13]),
                new Transaction(vecTirCuota[14], vecTirFecha[14]),
                new Transaction(vecTirCuota[15], vecTirFecha[15]),
                new Transaction(vecTirCuota[16], vecTirFecha[16]),
                new Transaction(vecTirCuota[17], vecTirFecha[17]),
                new Transaction(vecTirCuota[18], vecTirFecha[18]),
                new Transaction(vecTirCuota[19], vecTirFecha[19]),
                new Transaction(vecTirCuota[20], vecTirFecha[20]),
                new Transaction(vecTirCuota[21], vecTirFecha[21]),
                new Transaction(vecTirCuota[22], vecTirFecha[22]),
                new Transaction(vecTirCuota[23], vecTirFecha[23]),
                new Transaction(vecTirCuota[24], vecTirFecha[24]),
                new Transaction(vecTirCuota[25], vecTirFecha[25]),
                new Transaction(vecTirCuota[26], vecTirFecha[26]),
                new Transaction(vecTirCuota[27], vecTirFecha[27]),
                new Transaction(vecTirCuota[28], vecTirFecha[28]),
                new Transaction(vecTirCuota[29], vecTirFecha[29]),
                new Transaction(vecTirCuota[30], vecTirFecha[30]),
                new Transaction(vecTirCuota[31], vecTirFecha[31]),
                new Transaction(vecTirCuota[32], vecTirFecha[32]),
                new Transaction(vecTirCuota[33], vecTirFecha[33]),
                new Transaction(vecTirCuota[34], vecTirFecha[34]),
                new Transaction(vecTirCuota[35], vecTirFecha[35]),
                new Transaction(vecTirCuota[36], vecTirFecha[36]),
                new Transaction(vecTirCuota[37], vecTirFecha[37]),
                new Transaction(vecTirCuota[38], vecTirFecha[38]),
                new Transaction(vecTirCuota[39], vecTirFecha[39]),
                new Transaction(vecTirCuota[40], vecTirFecha[40]),
                new Transaction(vecTirCuota[41], vecTirFecha[41]),
                new Transaction(vecTirCuota[42], vecTirFecha[42])
        ).xirr();


double rate1 = Xirr.builder().withNewtonRaphsonBuilder(
                NewtonRaphson.builder().withIterations(1000).withTolerance(0.0001)).withGuess(.20).withTransactions(
                new Transaction(BCNIIFLUJ.SALDO, BCNIIFLUJ.FECHACORTE),
                new Transaction(-77000, "2020-01-31"),
                new Transaction(-77000, "2020-02-29"),
                new Transaction(-77000, "2020-03-31"),
                new Transaction(-77000, "2020-04-30"),
                new Transaction(-77000, "2020-05-31"),
                new Transaction(-77000, "2020-06-30"),
                new Transaction(-77000, "2020-07-31"),
                new Transaction(-90000, "2020-08-31"),
                new Transaction(-90000, "2020-09-30"),
                new Transaction(-90000, "2020-10-31"),
                new Transaction(-90000, "2020-11-30"),
                new Transaction(-90000, "2020-12-31"),
                new Transaction(-90000, "2021-01-31"),
                new Transaction(-103500, "2021-02-28"),
                new Transaction(-103500, "2021-03-31"),
                new Transaction(-103500, "2021-04-30"),
                new Transaction(-103500, "2021-05-31"),
                new Transaction(-103500, "2021-06-30"),
                new Transaction(-103500, "2021-07-31"),
                new Transaction(-119025, "2021-08-31"),
                new Transaction(-119025, "2021-09-30"),
                new Transaction(-119025, "2021-10-31"),
                new Transaction(-119025, "2021-11-30"),
                new Transaction(-119025, "2021-12-31"),
                new Transaction(-119025, "2022-01-31"),
                new Transaction(-136879, "2022-02-28"),
                new Transaction(-136879, "2022-03-31"),
                new Transaction(-136879, "2022-04-30"),
                new Transaction(-136879, "2022-05-31"),
                new Transaction(-136879, "2022-06-30"),
                new Transaction(-136879, "2022-07-31"),
                new Transaction(-157411, "2022-08-31"),
                new Transaction(-157411, "2022-09-30"),
                new Transaction(-157411, "2022-10-31"),
                new Transaction(-157411, "2022-11-30"),
                new Transaction(-157411, "2022-12-31"),
                new Transaction(-157411, "2023-01-31"),
                new Transaction(-181022, "2023-02-28"),
                new Transaction(-181022, "2023-03-31"),
                new Transaction(-181022, "2023-04-30"),
                new Transaction(-181022, "2023-05-31"),
                new Transaction(-181022, "2023-06-30"),
                new Transaction(-181022, "2023-07-31")
        ).xirr();
        System.out.println(rate1); // Prints 0.2504234710540838
*/