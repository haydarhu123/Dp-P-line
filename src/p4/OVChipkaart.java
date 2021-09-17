package p4;

import p2.Reiziger;

import java.sql.Date;

public class OVChipkaart {
    private int kaartnummer;
    private Date geldig_tot;
    private int klasse;
    private Double saldo;
    private Reiziger reiziger_id;


    public OVChipkaart(int kaartnummer, Date geldig_tot, int klasse, Double saldo, Reiziger reiziger_id) {
        this.kaartnummer = kaartnummer;
        this.geldig_tot = geldig_tot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.reiziger_id = reiziger_id;
    }

    public int getKaartnummer() {
        return kaartnummer;
    }

    public void setKaartnummer(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public void setGeldig_tot(Date geldig_tot) {
        this.geldig_tot = geldig_tot;
    }

    public Date getGeldig_tot() {
        return geldig_tot;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setReiziger_id(Reiziger reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    public Reiziger getReiziger_id() {
        return reiziger_id;
    }
}
