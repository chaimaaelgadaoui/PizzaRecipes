package com.example.pizzarecipes.service;

import com.example.pizzarecipes.R;
import com.example.pizzarecipes.classes.Produit;
import com.example.pizzarecipes.dao.IDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProduitService implements IDao<Produit> {

    private static ProduitService INSTANCE;
    private final List<Produit> data = new ArrayList<>();

    private ProduitService() {
        seed();
    }

    public static ProduitService getInstance() {
        if (INSTANCE == null) INSTANCE = new ProduitService();
        return INSTANCE;
    }

    private void seed() {

        int margheritaImg = R.mipmap.pizzamar;
        int napolitaineImg = R.mipmap.pizza_napolitaine;
        int quattroImg = R.mipmap.quattro_formaggi;
        int prosciuttoImg = R.mipmap.prosciutto_e_funghi;
        int pepperoniImg = R.mipmap.pizza_pepperoni;
        int placeholder = android.R.drawable.ic_menu_gallery;

        data.add(new Produit("MARGHERITA PIZZA", 9.0,
                margheritaImg, "20 min",
                "- Farine\n- 350 ml d’eau\n- Levure\n- Sucre\n- Sel\n- Sauce tomate\n- Mozzarella\n- Basilic\n- Huile d’olive",
                "La véritable pizza italienne, simple et savoureuse avec une pâte maison.",
                "1. Mélanger 50 ml d’eau avec la levure et le sucre.\n"
                        + "2. Dissoudre le sel dans 300 ml d’eau.\n"
                        + "3. Mélanger la farine avec l’eau à la levure puis ajouter l’eau salée.\n"
                        + "4. Laisser reposer 10 min puis pétrir jusqu’à obtenir une pâte lisse.\n"
                        + "5. Laisser lever 30 min puis diviser en pâtons et laisser lever 2h.\n"
                        + "6. Préchauffer le four à température maximale pendant 20 min.\n"
                        + "7. Étaler la pâte sans écraser les bords.\n"
                        + "8. Ajouter la sauce tomate et la mozzarella.\n"
                        + "9. Cuire au four pendant 8 à 12 min.\n"
                        + "10. Ajouter le basilic et un filet d’huile d’olive avant de servir."
        ));

        data.add(new Produit("NAPOLITAN PIZZA", 11.0,
                napolitaineImg, "45 min",
                "- Farine type 00\n- 500 g d’eau\n- 25 g de sel\n- 10 g de sucre\n- 1 g de levure boulangère\n- 25 g d’huile d’olive\n- Tomates San Marzano\n- Mozzarella fior di latte\n- Basilic frais\n- Parmesan râpé",
                "La pizza napolitaine traditionnelle est une pizza italienne authentique avec une pâte moelleuse, un bord épais et une cuisson rapide à haute température.",
                "1. Dissoudre le sel et le sucre dans l’eau.\n"
                        + "2. Ajouter la farine progressivement et mélanger.\n"
                        + "3. Ajouter la levure puis pétrir doucement.\n"
                        + "4. Incorporer l’huile d’olive et travailler la pâte jusqu’à obtenir une texture lisse.\n"
                        + "5. Laisser reposer la pâte et former des boules.\n"
                        + "6. Laisser lever plusieurs heures jusqu’à fermentation complète.\n"
                        + "7. Étaler délicatement la pâte sans écraser les bords.\n"
                        + "8. Ajouter la sauce tomate, la mozzarella et le parmesan.\n"
                        + "9. Cuire au four très chaud pendant 5 à 7 minutes.\n"
                        + "10. Ajouter le basilic frais et servir immédiatement."
        ));
        data.add(new Produit("QUATTRO FORMAGGI", 13.0,
                quattroImg, "45 min",
                "- Pâte à pizza\n- Mozzarella\n- Gorgonzola\n- Taleggio\n- Parmesan Reggiano\n- Basilic frais\n- Huile d’olive\n- Levure boulangère\n- Farine type 00\n- Sel\n- Eau",
                "Une pizza riche et crémeuse pour les amateurs de fromage, sans sauce tomate afin de mettre en valeur les différentes saveurs des fromages.",
                "1. Mélanger l’eau tiède avec la levure et laisser reposer.\n"
                        + "2. Ajouter la farine et le sel, puis pétrir jusqu’à obtenir une pâte lisse.\n"
                        + "3. Laisser reposer la pâte jusqu’à ce qu’elle double de volume.\n"
                        + "4. Diviser la pâte en boules et laisser fermenter.\n"
                        + "5. Étaler la pâte délicatement sur un plan fariné.\n"
                        + "6. Ajouter mozzarella, gorgonzola, taleggio et parmesan.\n"
                        + "7. Cuire dans un four bien chaud jusqu’à ce que la pizza soit dorée.\n"
                        + "8. Ajouter un filet d’huile d’olive et du basilic frais avant de servir."
        ));
        data.add(new Produit("PROSCIUTTO E FUNGHI", 12.0,
                prosciuttoImg, "60 min",
                "- Farine\n- Levure boulangère\n- Eau tiède\n- Sel\n- Huile d’olive\n- Sauce tomate\n- Ail\n- Origan\n- Tomates pelées\n- Mozzarella\n- Jambon prosciutto\n- Champignons\n- Parmesan râpé\n- Basilic frais",
                "Une pizza classique italienne garnie de jambon prosciutto et de champignons, avec une sauce tomate parfumée et du fromage fondant.",
                "1. Mélanger farine, levure, eau, sel et huile d’olive.\n"
                        + "2. Pétrir la pâte jusqu’à obtenir une texture lisse.\n"
                        + "3. Laisser reposer la pâte environ 30 minutes.\n"
                        + "4. Préparer la sauce tomate avec ail, tomates, sel, sucre et origan.\n"
                        + "5. Étaler la pâte en cercles sur une surface farinée.\n"
                        + "6. Ajouter la sauce tomate sur la pâte.\n"
                        + "7. Ajouter mozzarella, jambon et champignons.\n"
                        + "8. Cuire au four à 220°C pendant environ 15 minutes.\n"
                        + "9. Ajouter du basilic frais avant de servir."
        ));
        data.add(new Produit("PEPPERONI PIZZA", 11.5,
                pepperoniImg, "40 min",
                "- Farine\n- Levure boulangère\n- Eau tiède\n- Sel\n- Huile d’olive\n- Sauce tomate\n- Mozzarella\n- Pepperoni\n- Origan\n- Basilic frais",
                "Une pizza classique américaine-italienne garnie de sauce tomate, mozzarella fondante et pepperoni légèrement épicé.",
                "1. Préparer la pâte en mélangeant farine, levure, eau, sel et huile.\n"
                        + "2. Pétrir jusqu’à obtenir une pâte lisse.\n"
                        + "3. Laisser reposer la pâte environ 30 minutes.\n"
                        + "4. Étaler la pâte en forme de cercle.\n"
                        + "5. Ajouter la sauce tomate sur la base.\n"
                        + "6. Ajouter la mozzarella et les tranches de pepperoni.\n"
                        + "7. Saupoudrer légèrement d’origan.\n"
                        + "8. Cuire au four à 220°C pendant 12 à 15 minutes.\n"
                        + "9. Ajouter du basilic frais et servir chaud."
        ));
    }
    @Override
    public Produit create(Produit p) {
        data.add(p);
        return p;
    }

    @Override
    public Produit update(Produit p) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getId() == p.getId()) {
                data.set(i, p);
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean delete(long id) {
        return data.removeIf(x -> x.getId() == id);
    }

    @Override
    public Produit findById(long id) {
        for (Produit p : data) if (p.getId() == id) return p;
        return null;
    }

    @Override
    public List<Produit> findAll() {
        return Collections.unmodifiableList(data);
    }
}
