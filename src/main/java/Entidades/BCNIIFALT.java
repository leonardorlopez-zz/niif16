
package Entidades;

import java.util.Date;

public class BCNIIFALT {
    private int id;
    private int idAgrupador;
    private String tipoContr;
    private String descriptn;
    private String proveedor;
    private String fechAlta;
    private String fechCorte;
    private String moneda;
    private double tipCamb;
    private double tasDescDol;
    private double tasDescPes;
    private String estado;
    public static int ID; 
    
    public BCNIIFALT(){
        
    }

//    public BCNIIFALT(int id, int idAgrupador, String descriptn, String proveedor, String fechCorte, String moneda) {
//        this.id = id;
//        this.idAgrupador = idAgrupador;
//        this.descriptn = descriptn;
//        this.proveedor = proveedor;
//        this.fechCorte = fechCorte;
//        this.moneda = moneda;
//    }
    
    
    public BCNIIFALT(int idAgrupador, String tipoContr, String descriptn, String proveedor, String fechAlta, String fechCorte, String moneda, double tipCamb, double tasDescDol, double tasDescPes, String estado) {
        this.idAgrupador = idAgrupador;
        this.tipoContr = tipoContr;
        this.descriptn = descriptn;
        this.proveedor = proveedor;
        this.fechAlta = fechAlta;
        this.fechCorte = fechCorte;
        this.moneda = moneda;
        this.tipCamb = tipCamb;
        this.tasDescDol = tasDescDol;
        this.tasDescPes = tasDescPes;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAgrupador() {
        return idAgrupador;
    }

    public void setIdAgrupador(int idAgrupador) {
        this.idAgrupador = idAgrupador;
    }

    public String getTipoContr() {
        return tipoContr;
    }

    public void setTipoContr(String tipoContr) {
        this.tipoContr = tipoContr;
    }

    public String getDescriptn() {
        return descriptn;
    }

    public void setDescriptn(String descriptn) {
        this.descriptn = descriptn;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getFechAlta() {
        return fechAlta;
    }

    public void setFechAlta(String fechAlta) {
        this.fechAlta = fechAlta;
    }

    public String getFechCorte() {
        return fechCorte;
    }

    public void setFechCorte(String fechCorte) {
        this.fechCorte = fechCorte;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getTipCamb() {
        return tipCamb;
    }

    public void setTipCamb(double tipCamb) {
        this.tipCamb = tipCamb;
    }

    public double getTasDescDol() {
        return tasDescDol;
    }

    public void setTasDescDol(double tasDescDol) {
        this.tasDescDol = tasDescDol;
    }

    public double getTasDescPes() {
        return tasDescPes;
    }

    public void setTasDescPes(double tasDescPes) {
        this.tasDescPes = tasDescPes;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    
}
