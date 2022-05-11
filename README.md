# Java - Gestion de tournois
## Remise pour le 23 mai


### 1. Questions Dubisy
- Pour créer un match, est-ce qu'on bloque si l'emplacement est déjà occupé au même moment par un autre match (idem pour referee)

- Checks BD sur tous les int ? (fait)

- Formatter, Manager, Controller et DB en static ?

- Message d'exceptions de la BD en anglais, Problème ?

- Annotation `@NotNull` (/!\\ addon intelliJ) ou condition `== null`

- Trop dur de faire un bel affichage de l'animationPanel :'( La taille du pong nous fait la gueule

### 2. A faire
- [x] Refaire mise en page du formulaire Match et du choix des joueurs (gestion matchs)
- [ ] (en cours) Tâche métier (proposer matchs déjà créés et qui n'ont pas encore de résultats assignés)
- [ ] (en cours) Fenêtre animations matchs (thread + gestion fin match)
- [ ] (en cours) JavaDoc
- [ ] Trouver quelque chose pour utiliser les Rewards
  - Affichage des rewards d'un tournoi (tri places)

#### 2.1. Corrections
- [x] Model abstract JTable (à la main pour type de valeur)
  - [x] Affichage date
- [x] Ajouter valeurs dans BD

##### 2.2. Autres
- [x] Réservation d'un visiteur
- [x] Ajout d'une nouvelle personne (joueur/visiteur/referee)