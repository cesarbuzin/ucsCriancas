package criancas.ucs.com.criancas;

public class DadosIdadeBean {
    private Double pesoMin;
    private Double pesoMax;
    private Double alturaMin;
    private Double alturaMax;

    public DadosIdadeBean(Double pesoMin,Double pesoMax,Double alturaMin,Double alturaMax){
        this.pesoMin = pesoMin;
        this.pesoMax = pesoMax;
        this.alturaMin = alturaMin;
        this.alturaMax = alturaMax;
    }

    public Double getPesoMin(){
        return this.pesoMin;
    }

    public Double getPesoMax(){
        return this.pesoMax;
    }

    public Double getAlturaMin(){
        return this.alturaMin;
    }

    public Double getAlturaMax() {
        return this.alturaMax;
    }
}
