import java.io.Serializable;

class Carte implements Serializable {
    private String tip;
    private String titlu;
    private String autor;
    private int numarDisponibil;

    public Carte(String tip, String titlu, String autor, int numarDisponibil) {
        this.tip = tip;
        this.titlu = titlu;
        this.autor = autor;
        this.numarDisponibil = numarDisponibil;
    }

    @Override
    public String toString() {
        return "Tip: " + tip + ", titlu: " + titlu + ", autor: " + autor + ", disponibile: " + numarDisponibil;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setNumarDisponibil(int numarDisponibil) {
        this.numarDisponibil = numarDisponibil;
    }

    public String getTip() {
        return tip;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getAutor() {
        return autor;
    }

    public int getNumarDisponibil() {
        return numarDisponibil;
    }
}
