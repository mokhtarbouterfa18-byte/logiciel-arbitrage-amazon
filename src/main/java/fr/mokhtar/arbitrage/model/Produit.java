package fr.mokhtar.arbitrage.model;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Produit {

    private Long id;

    private String nom;
    private String codeEan;
    private String magasin;
    private BigDecimal prixAchat, prixVenteEstime, fraisFba, fraisTransportStockage;
    private int unitesAchetees;
    private String notes;
    private LocalDate dateAchat;

    private int unitesVendues = 0;
    private BigDecimal commission = new BigDecimal("0.15");
    private Statut statut = Statut.A_ENVOYER;

    public Produit() {

    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodeEan() {
        return codeEan;
    }

    public void setCodeEan(String codeEan) {
        this.codeEan = codeEan;
    }

    public String getMagasin() {
        return magasin;
    }

    public void setMagasin(String magasin) {
        this.magasin = magasin;
    }

    public BigDecimal getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(BigDecimal prixAchat) {
        this.prixAchat = prixAchat;
    }

    public BigDecimal getPrixVenteEstime() {
        return prixVenteEstime;
    }

    public void setPrixVenteEstime(BigDecimal prixVenteEstime) {
        this.prixVenteEstime = prixVenteEstime;
    }

    public BigDecimal getFraisFba() {
        return fraisFba;
    }

    public void setFraisFba(BigDecimal fraisFba) {
        this.fraisFba = fraisFba;
    }

    public BigDecimal getFraisTransportStockage() {
        return fraisTransportStockage;
    }

    public void setFraisTransportStockage(BigDecimal fraisTransportStockage) {
        this.fraisTransportStockage = fraisTransportStockage;
    }

    public int getUnitesAchetees() {
        return unitesAchetees;
    }

    public void setUnitesAchetees(int unitesAchetees) {
        this.unitesAchetees = unitesAchetees;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    public int getUnitesVendues() {
        return unitesVendues;
    }

    public void setUnitesVendues(int unitesVendues) {
        this.unitesVendues = unitesVendues;
    }

    public BigDecimal getCommission() {
        return commission;
    }

    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }
}
