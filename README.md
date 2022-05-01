# Java - Gestion de tournois
## Remise pour le 23 mai


### 1. Questions Dubisy
- Tâche métier doit créer un nouveau match ou on utilise ceux qui existent déjà ?
  Cela impliquerait d'obtenir tout le formulaire du CRUD...
  - Si on utilise les matchs existants, est-ce qu'on bloque le lancement du match si date différente à la date du jour, si le match a déjà eu lieu (il y a déjà des results)
- Pour créer un match, est-ce qu'on bloque si l'emplacement est déjà occupé au même moment par un autre match (idem pour referee)
- Annotation `@NotNull` (/!\\ download intelliJ) ou condition `== null`

### 2. Notes : à faire
- [v] Refaire mise en page du formulaire Match et du choix des joueurs (gestion matchs)
  - /!\\ problème ajout Match ne retient pas l'heure de la date
- [ ] Fenêtre animations matchs
- [ ] JavaDoc
- [ ] Trouver quelque chose pour utiliser les Rewards
  - Affichage des rewards d'un tournoi (tri places)
###### autres
- [ ] Réservation d'un visiteur
- [ ] Inscription d'un joueur (= ajout d'un nouveau joueur)