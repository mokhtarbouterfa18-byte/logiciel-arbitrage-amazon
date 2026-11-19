package fr.mokhtar.arbitrage.service;


import fr.mokhtar.arbitrage.model.Conseil;
import fr.mokhtar.arbitrage.model.Produit;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ProduitService {

    private static final BigDecimal RENDEMENT_EXCELLENT = new BigDecimal("1.00");
    private static final BigDecimal RENDEMENT_MINIMUM = new BigDecimal("0.30");
    private static final BigDecimal BENEFICE_MINIMUM = new BigDecimal("2");

    public BigDecimal beneficeParUnite(Produit produit) {
        BigDecimal prixVenteEstime = produit.getPrixVenteEstime();
        BigDecimal commission = produit.getCommission();
        BigDecimal fraisFba = produit.getFraisFba();
        BigDecimal fraisTransportStockage = produit.getFraisTransportStockage();
        BigDecimal prixAchat = produit.getPrixAchat();
        BigDecimal venteNette = prixVenteEstime.multiply(BigDecimal.ONE.subtract(commission));

        BigDecimal resultat = venteNette.subtract(fraisFba).subtract(fraisTransportStockage).subtract(prixAchat);
        return resultat.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal rendement(Produit produit) throws IllegalArgumentException {
        BigDecimal beneficeUnite = beneficeParUnite(produit);
        BigDecimal prixAchat = produit.getPrixAchat();
        if (prixAchat.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Le prix d'achat doit être supérieur à 0");
        }
        return beneficeUnite.divide(prixAchat, 4, RoundingMode.HALF_UP);
    }

    public Conseil conseilProduit(Produit produit) {
        BigDecimal beneficeUnite = beneficeParUnite(produit);
        BigDecimal rendement = rendement(produit);

        if(beneficeUnite.compareTo(BigDecimal.ZERO) < 0){
            return Conseil.A_EVITER;
        }

        if(beneficeUnite.compareTo(BENEFICE_MINIMUM) < 0 || rendement.compareTo(RENDEMENT_MINIMUM) < 0){
            return Conseil.MARGE_TROP_FAIBLE;
        }

        if(rendement.compareTo(RENDEMENT_EXCELLENT) >= 0){
            return Conseil.EXCELLENT;
        }

        return Conseil.INTERESSANT;
    }

}
