package ServletsNIIF;

import Calculos.CalculoAltaFlujo;
import Entidades.BCNIIFLUJ;
import Entidades.BCNIIFALT;
import Utilidades.LectorMejorado;
import Utilidades.TIR;
import Utilidades.Utilidades;
import static Utilidades.Utilidades.reemplComaPorPunto;
import baseDeDatos.FlujoDAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name = "Alta_Datos_TXT_Servlet", urlPatterns = {"/Alta_Datos_TXT_Servlet"})
public class Alta_Datos_TXT_Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    //MANEJO DEL REQUEST
    //Creamos un factory para disk-based file items
    DiskFileItemFactory factory = new DiskFileItemFactory();

    //Creamos un File upload handler
    ServletFileUpload upload = new ServletFileUpload(factory);

        try {
    //Parseamos el request
    List<FileItem> items = upload.parseRequest(request);
    Iterator<FileItem> iter = items.iterator();
    
   //Le cargamos los datos a b1 (Alta)
    BCNIIFALT b1 = Utilidades.cargaAlta(iter);

    //Leemos los datos del txt (3 columnas que nos sirven para los calculos)
    ArrayList<BCNIIFLUJ> arr1 = LectorMejorado.lector();
            
     //CALCULOS 1RA TANDA (Dias Futuros, Importe Descontado, TIR)
     ArrayList<BCNIIFLUJ> flujoPrimeraTanda = CalculoAltaFlujo.calculoAltaFlujo(arr1, b1);
     
    //CALCULO TIR (tasa)
      ArrayList<BCNIIFLUJ> flujoTIR = TIR.xirr(flujoPrimeraTanda);
     
     //CALCULOS 2da TANDA (Saldo, Intereses, Amortizacion)
     ArrayList<BCNIIFLUJ> flujoSegundaTanda = CalculoAltaFlujo.calculosSegundaTanda(flujoTIR);
     
     //COPIA DE VALORES DE b1 al flujoSegundaTanda
     Utilidades.copiaAltaAFlujo(b1, flujoSegundaTanda);
     
     
//     //PERSISTIMOS EL FLUJO EN TABLA AUXILIAR BCNIIFLUJ (Antes de aprobacion)
//     FlujoDAO.insertaFlujo(flujoSegundaTanda);
     
//Agrega el flujo Arraylist al request
     
//        request.setAttribute("flujo", flujoSegundaTanda);
        HttpSession s = request.getSession();
        s.setAttribute("flujo", flujoSegundaTanda);
        //Cargamos el objeto BCNIIFALT a la sesion para mostrar algunos 4 campos en el front de salida
        s.setAttribute("flujoAlta", b1);  
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally{
//            request.getRequestDispatcher("ingresoDeContratoSalida.jsp").forward(request, response);
          response.sendRedirect("ingresoDeContratoSalida.jsp");
        
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

}
