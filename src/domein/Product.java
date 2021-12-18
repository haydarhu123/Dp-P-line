package domein;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private int productnummer;
    private String naam;
    private String beschrijving;
    private Double prijs;
    private List<OVChipkaart> ovchipkaart = new ArrayList<>();

    public Product(int productnummer, String naam, String beschrijving, Double prijs) {
        this.productnummer = productnummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
    }

    public void setProductnummer(int productnummer) {
        this.productnummer = productnummer;
    }

    public int getProductnummer() {
        return productnummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public Double getPrijs() {
        return prijs;
    }

    public void setPrijs(Double prijs) {
        this.prijs = prijs;
    }

    public List<OVChipkaart> getOvchipkaart() {
        return ovchipkaart;
    }

    public void setOvchipkaart(List<OVChipkaart> ovchipkaart) {
        this.ovchipkaart = ovchipkaart;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productnummer=" + productnummer +
                ", naam='" + naam + '\'' +
                ", beschrijving='" + beschrijving + '\'' +
                ", prijs=" + prijs +
                ", ovchipkaart=" + ovchipkaart +
                '}';
    }
}
