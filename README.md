# Java - Gestion de tournois
## Remise pour le 23 mai


### 1. Questions Dubisy
- Tâche métier doit créer un nouveau match ou on utilise ceux qui existent déjà ?
  Cela impliquerait d'obtenir tout le formulaire du CRUD...
  - Si on utilise les matchs existants, est-ce qu'on bloque le lancement du match si date différente à la date du jour, si le match a déjà eu lieu (il y a déjà des results)

- Pour créer un match, est-ce qu'on bloque si l'emplacement est déjà occupé au même moment par un autre match (idem pour referee)

- Annotation `@NotNull` (/!\\ download intelliJ) ou condition `== null`

### 2. A faire
- [x] Refaire mise en page du formulaire Match et du choix des joueurs (gestion matchs)
- [ ] Tâche métier (proposer matchs déjà créés et qui n'ont pas encore de résultats assignés)
- [ ] Fenêtre animations matchs (thread + gestion fin match)
- [ ] JavaDoc
- [ ] Trouver quelque chose pour utiliser les Rewards
  - Affichage des rewards d'un tournoi (tri places)

#### 2.1. Corrections
- [x] Model abstract JTable (à la main pour type de valeur)
- [ ] Ajouter valeurs dans BD

##### 2.2. Autres
- [ ] Réservation d'un visiteur
- [ ] Ajout d'une nouvelle personne (joueur/visiteur/referee)