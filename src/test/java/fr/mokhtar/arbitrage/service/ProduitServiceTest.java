package fr.mokhtar.arbitrage.service;

import fr.mokhtar.arbitrage.model.Conseil;
import fr.mokhtar.arbitrage.model.Produit;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProduitServiceTest {

    private final ProduitService service = new ProduitService();

    @Test
    void beneficeParUnite_casNormal() {

        Produit produit = creerProduit("20", "3", "1", "5");

        // 2. Agir : appeler service.beneficeParUnite(...)
        BigDecimal resultat = service.beneficeParUnite(produit);

        // 3. Vérifier : assertEquals(new BigDecimal("8.00"), resultat);
        assertEquals(new BigDecimal("8.00"), resultat);
    }

    @Test
    void rendement_casNormal() {
        Produit produit = creerProduit("20", "3", "1", "5");
        // 2. Agir : appeler service.rendement(produit)
        BigDecimal resultat = service.rendement(produit);
        // 3. Vérifier : assertEquals(new BigDecimal("1.6000"), resultat);
        assertEquals(new BigDecimal("1.6000"), resultat);
    }

    @Test
    void rendement_prixAchatZero_lanceException() {
        // 1. Préparer : un produit avec un prix d'achat à "0"
        Produit produit = creerProduit("20", "3", "1", "0");
        // 2 + 3. Agir ET vérifier en une ligne :
        assertThrows(IllegalArgumentException.class, () -> service.rendement(produit));
    }

    @ParameterizedTest(name = "vente {0} / achat {1} → {2}")
    @CsvSource({
            "8,   10, A_EVITER",           // perte
            "24,  20, MARGE_TROP_FAIBLE",  // rendement 20 % trop bas
            "3.5, 2,  MARGE_TROP_FAIBLE",  // bénéfice 1,50 € trop bas
            "15,  5,  EXCELLENT",          // 200 %
            "15,  10, INTERESSANT",        // 50 %
            "7,   5,  INTERESSANT",        // limite : 2 € pile
            "13,  10, INTERESSANT",        // limite : 30 % pile
            "10,  5,  EXCELLENT",          // limite : 100 % pile
            "5,   5,  MARGE_TROP_FAIBLE"   // limite : 0 € pile
    })
    void conseil(String vente, String achat, Conseil attendu) {
        Produit produit = creerProduit(vente, "0", "0", achat);
        produit.setCommission(BigDecimal.ZERO);

        assertEquals(attendu, service.conseilProduit(produit).conseil());
    }

    // Méthode utilitaire : crée un produit avec les valeurs données
    private Produit creerProduit(String vente, String fba, String transport, String achat) {
        Produit produit = new Produit();

        produit.setPrixVenteEstime(new BigDecimal(vente));
        produit.setFraisFba(new BigDecimal(fba));
        produit.setFraisTransportStockage(new BigDecimal(transport));
        produit.setPrixAchat(new BigDecimal(achat));

        return produit;
    }

}