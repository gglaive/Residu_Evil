# Projet : Gestion de stock

## Description des cas d'utilisation

| Cas d'utilisations     | Vendre                                                                                                                                                                                                                                                                                                                                                                                                                                       |
| ---------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Acteur                 | Caissier                                                                                                                                                                                                                                                                                                                                                                                                                                     |
| Description            | À l'aide de la caisse enregistreuse, le caissier scanne un article, affiche le prix, ensuite imprime le ticket de caisse détaillé et vends les articles en fonctions de leur prix.                                                                                                                                                                                                                                                           |
| Déroulement Normal     | Le caissier scanne l'article à l'aide du lecteur de code barre, il emet un bip s'il est reconnu et affiche son prix sur la caisse. Les objet passé son garder en mémoire, une fois tout les articles passé le caissier appuie sur la touche <total> ainsi le total des article vendues, leur prix est imprimé sour forme de tickets. une fois le vente terminé et le tirroir caisse fermé, le nombre en stock de ces produits se décrémente. |
| Déroulement Alternatif | Soit l'article n'est pas reconnu, alors le caissier à le choix de taper directement le code sur la caisse, celle-ci ne fait pas de différence entre un article scanné ou taper directement sur le clavier.                                                                                                                                                                                                                                   |

| Cas d'utilisations     | Retourner                                                                                                                                                                                      |
| ---------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Acteur                 | Caissier                                                                                                                                                                                       |
| Description            | Un client peut retourner un article à la discrétion du détaillant. Si les article sont vendable se rajoutent au stock du magasin.                                                              |
| Déroulement normal     | Lors d'un retour, le caissier appuie sur la touche retour de la caisse. Il scanne l'article, rembourse le client et lors de la fermeture du tirroir caisse le stock de l'article s'incrémente. |
| Déroulement alternatif | Si l'article n'est pas vendable, le détaillant peut refuser le retour, le stock reste alors inchangé.                                                                                          |

| Cas d'utilisations     | Établir solde de caisse                                                                                                                                                                                                                                                                                  |
| ---------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Acteur                 | Caissier                                                                                                                                                                                                                                                                                                 |
| Description            | A la fin de la journée le caissier établi le solde de la caisse. Un ticket s'imprime de l'ensemble des opérations depuis le dernier solde et de solde total des opérations                                                                                                                               |
| Déroulement normal     | Le caissier appuie deux fois sur le bouton du total du tirroir caisse. La tirroir caisse s'ouvre et le ticket de toute les opérations depuis le dernier solde et du solde total de la caisse s'imprime. Le caissier doit faire coincider le contenu du tirroir caisse avec le prix marqué sur le ticket. |
| Déroulement alternatif | SI le contenu du tirroir caisse n'est pas similaire au solde afficher sur le ticket, un écart de caisse est alors constaté.                                                                                                                                                                              |

| Cas d'utilisations     | Changer le prix d'un article                                                                                                                               |
| ---------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Acteur                 | Détaillant                                                                                                                                                 |
| Description            | Le détaillant peut modifier le prix d'un article.                                                                                                          |
| Déroulement normal     | Le détaillant peut effectué le changement de prix d'un article, mais cela ne peut s'effectuer qu'après un solde de caisse et avant tout autre transaction. |
| Déroulement alternatif |                                                                                                                                                            |

| Cas d'utilisation      | Ouvrir session de caisse                                                                       |
| ---------------------- | ---------------------------------------------------------------------------------------------- |
| Acteur                 | Caissier                                                                                       |
| Description            | Pour que la caisse et que le lecteur de code barre fonctionne il faut que la clef soit inséré. |
| Déroulement normal     | La clef de sécurité insérer permet d'activer la caisse et d'effectuer les transactions.        |
| Déroulement alternatif |                                                                                                |

| Cas d'utilisation      | Annuler saisie erroné                                                |
| ---------------------- | -------------------------------------------------------------------- |
| Acteur                 | Caissier                                                             |
| Description            | Pour annuler une saisie il faut retirer la clef.                     |
| Déroulement normal     | Retirer la clef de sécurité permet d'annuler une transaction erroné. |
| Déroulement alternatif |                                                                      |

| Cas d'utilisation      | Commander un article                                                                                                          |
| ---------------------- | ----------------------------------------------------------------------------------------------------------------------------- |
| Acteur                 | Détaillant                                                                                                                    |
| Description            | Le détaillant peut demander la commande d'un article au fournisseur.                                                          |
| Déroulement normal     | Le détaillant choisi un article dans la liste, selectionne le niveau voulu et envoie la commande au fournisseur par la poste. |
| Déroulement alternatif | Si un réassort fournisseur est livré au magasin, les commandes en cours des articles réassortis sont annulé.                  |

| Cas d'utilisation      | Réassortir un produit                                                                                                                                                                          |
| ---------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Acteur                 | Détaillant                                                                                                                                                                                     |
| Description            | SI un article vient à manquer un réassortiement est envoyé au détaillant par le fournisseur                                                                                                    |
| Déroulement normal     | Quand le stock d'un produit vient à passer sous un seuil, défini par le détaillant, le fournisseur envoi un réassort au détaillant, qui une fois reçu doit mettre à jour les stock du magasin. |
| Déroulement alternatif |                                                                                                                                                                                                |

| Cas d'utilisation      | Mettre à jour les produit                                                                                                                                           |
| ---------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Acteur                 | Détaillant                                                                                                                                                          |
| Description            | Le détaillant peut mettre à jour les information d'un produit, en retiré ou en ajouter des nouveaux.                                                                |
| Déroulement normal     | Le détaillant sur son interface peut ajouter un produit et donner les informations fournisseurs, mettre à jour les informations ou supprimer un produit des stocks. |
| Déroulement alternatif |                                                                                                                                                                     |

| Cas D'utilisation      | Consulter les stocks                                     |
| ---------------------- | -------------------------------------------------------- |
| Acteur                 | Détaillant                                               |
| Description            | Le détaillant peut consulter les informations des sotcks |
| Déroulement normal     |                                                          |
| Déroulement alternatif |                                                          |
