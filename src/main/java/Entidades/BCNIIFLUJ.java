
package Entidades;

public class BCNIIFLUJ extends BCNIIFALT{

    private String fechDesde;
    private String fechHasta;
    private String usuario;
    private String fechModif;
    private double impMonOrig;
    private int diasFuturos;
    private double imporDesc;
    private double saldo;
    private double intereses;
    private double amortizac;
    private double sumatoriaTIR;
    private double tirCuota;
    private double tirPorcen;
    private double sumaImpMonOrig;
    private String fechDeveng;
    private double impDescPes;
    private double impMonNomPes;
    private double saldoPesos;
    private double interesesPes;
    private double amortizacPes;
    private double regPap;
    public static int PLAZO=0;
    public static double SALDO=0;
    //Almacena la fecha de corte para la tir
    public static String FECHACORTE;
    //Almacena la TIR para un flujo
    public static double TIR;

    public BCNIIFLUJ(String fechDesde, String fechHasta, String usuario, String fechModif, double impMonOrig, int diasFuturos, double imporDesc, double saldo, double intereses, double amortizac, double tirCuota, double tirPorcen, double sumaImpMonOrig, String fechDeveng, double impDescPes, double impMonNomPes, double saldoPesos, double interesesPes, double amortizacPes, double regPap, int idAgrupador, String tipoContr, String descriptn, String proveedor, String fechAlta, String fechCorte, String moneda, double tipCamb, double tasDescDol, double tasDescPes, String estado) {
        super(idAgrupador, tipoContr, descriptn, proveedor, fechAlta, fechCorte, moneda, tipCamb, tasDescDol, tasDescPes, estado);
        this.fechDesde = fechDesde;
        this.fechHasta = fechHasta;
        this.usuario = usuario;
        this.fechModif = fechModif;
        this.impMonOrig = impMonOrig;
        this.diasFuturos = diasFuturos;
        this.imporDesc = imporDesc;
        this.saldo = saldo;
        this.intereses = intereses;
        this.amortizac = amortizac;
        this.tirCuota = tirCuota;
        this.tirPorcen = tirPorcen;
        this.sumaImpMonOrig = sumaImpMonOrig;
        this.fechDeveng = fechDeveng;
        this.impDescPes = impDescPes;
        this.impMonNomPes = impMonNomPes;
        this.saldoPesos = saldoPesos;
        this.interesesPes = interesesPes;
        this.amortizacPes = amortizacPes;
        this.regPap = regPap;
    }

    public double getSumatoriaTIR() {
        return sumatoriaTIR;
    }

    public void setSumatoriaTIR(double sumatoriaTIR) {
        this.sumatoriaTIR = sumatoriaTIR;
    }

    public BCNIIFLUJ(double saldo) {
        this.saldo = saldo;
    }

    public BCNIIFLUJ() {
    }

//    public BCNIIFLUJ(int id, int idAgrupador, String descriptn, String proveedor, String fechCorte, String moneda, double sumatoriaTIR) {
//        super(id, idAgrupador, descriptn, proveedor, fechCorte, moneda);
//        this.sumatoriaTIR = sumatoriaTIR;
//    }

    
    public String getFechDesde() {
        return fechDesde;
    }

    public void setFechDesde(String fechDesde) {
        this.fechDesde = fechDesde;
    }

    public String getFechHasta() {
        return fechHasta;
    }

    public void setFechHasta(String fechHasta) {
        this.fechHasta = fechHasta;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFechModif() {
        return fechModif;
    }

    public void setFechModif(String fechModif) {
        this.fechModif = fechModif;
    }

    public double getImpMonOrig() {
        return impMonOrig;
    }

    public void setImpMonOrig(double impMonOrig) {
        this.impMonOrig = impMonOrig;
    }

    public int getDiasFuturos() {
        return diasFuturos;
    }

    public void setDiasFuturos(int diasFuturos) {
        this.diasFuturos = diasFuturos;
    }

    public double getImporDesc() {
        return imporDesc;
    }

    public void setImporDesc(double imporDesc) {
        this.imporDesc = imporDesc;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getIntereses() {
        return intereses;
    }

    public void setIntereses(double intereses) {
        this.intereses = intereses;
    }

    public double getAmortizac() {
        return amortizac;
    }

    public void setAmortizac(double amortizac) {
        this.amortizac = amortizac;
    }

    public double getTirCuota() {
        return tirCuota;
    }

    public void setTirCuota(double tirCuota) {
        this.tirCuota = tirCuota;
    }

    public double getTirPorcen() {
        return tirPorcen;
    }

    public void setTirPorcen(double tirPorcen) {
        this.tirPorcen = tirPorcen;
    }

    public double getSumaImpMonOrig() {
        return sumaImpMonOrig;
    }

    public void setSumaImpMonOrig(double sumaImpMonOrig) {
        this.sumaImpMonOrig = sumaImpMonOrig;
    }

    public String getFechDeveng() {
        return fechDeveng;
    }

    public void setFechDeveng(String fechDeveng) {
        this.fechDeveng = fechDeveng;
    }

    public double getImpDescPes() {
        return impDescPes;
    }

    public void setImpDescPes(double impDescPes) {
        this.impDescPes = impDescPes;
    }

    public double getImpMonNomPes() {
        return impMonNomPes;
    }

    public void setImpMonNomPes(double impMonNomPes) {
        this.impMonNomPes = impMonNomPes;
    }

    public double getSaldoPesos() {
        return saldoPesos;
    }

    public void setSaldoPesos(double saldoPesos) {
        this.saldoPesos = saldoPesos;
    }

    public double getInteresesPes() {
        return interesesPes;
    }

    public void setInteresesPes(double interesesPes) {
        this.interesesPes = interesesPes;
    }

    public double getAmortizacPes() {
        return amortizacPes;
    }

    public void setAmortizacPes(double amortizacPes) {
        this.amortizacPes = amortizacPes;
    }

    public double getRegPap() {
        return regPap;
    }

    public void setRegPap(double regPap) {
        this.regPap = regPap;
    }

    @Override
    public String toString() {
        return "BCNIIFLUJ{" + "fechDesde=" + fechDesde + ", fechHasta=" + fechHasta + ", usuario=" + usuario + ", fechModif=" + fechModif + ", impMonOrig=" + impMonOrig + ", diasFuturos=" + diasFuturos + ", imporDesc=" + imporDesc + ", saldo=" + saldo + ", intereses=" + intereses + ", amortizac=" + amortizac + ", sumatoriaTIR=" + sumatoriaTIR + ", tirCuota=" + tirCuota + ", tirPorcen=" + tirPorcen + ", sumaImpMonOrig=" + sumaImpMonOrig + ", fechDeveng=" + fechDeveng + ", impDescPes=" + impDescPes + ", impMonNomPes=" + impMonNomPes + ", saldoPesos=" + saldoPesos + ", interesesPes=" + interesesPes + ", amortizacPes=" + amortizacPes + ", regPap=" + regPap + '}';
    }

   
    
    
    
    
}
