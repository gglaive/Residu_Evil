| action         | vente                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
| -------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| acteur         | caissier                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            |
| description    | procéder à la vente d'un article                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    |
| déroulé normal | Lorsqu’un client achète un article, le caissier scanne son code
barre. S’il est reconnu, la caisse émet un bip et affiche le nom de l’article et son prix. Si un code
barre est illisible, le caissier tape le code au clavier et  appuie  sur  la  touche "Entrée",  ce  qui  a  le  même  effet  que  de  le  scanner.  Cette saisie  manuelle  est  mémorisée  par  la  caisse. A la fin, le caissier appuie sur "total" afin d'ouvrir le tiroire de caisse pour permettre le paiment et l'impression du ticket. |

| action         | retourner un article                                                                                                                                                                                                                          |
| -------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| acteur         | caissier                                                                                                                                                                                                                                      |
| description    | retour et remboursement d'un article                                                                                                                                                                                                          |
| déroulé normal | le caissier appuie sur la touche  "Retour" puis scanne l’article en question. Ceci provoque l’ouverture du tiroir
caisse et permet de rembourser l’article. La quantité en stock est alors incrémentée lorsque le caissier referme le tiroir. |

| action         | établir solde de caisse                                                                                                                                                                                                                                                                                                                                                   |
| -------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| acteur         | caissier                                                                                                                                                                                                                                                                                                                                                                  |
| description    | établir le solde de la caisse en fin de service                                                                                                                                                                                                                                                                                                                           |
| déroulé normal | appuie deux fois sur la touche  "Total" pour lancer l’opération. Le second appui sur la touche "Total" provoque l’ouverture
du tiroir et l’impression de la  liste complète  des  tickets  de  caisse  pour  toutes
les  transactions  ayant  eu  lieu  depuis  le dernier  solde.  Celle
ci  est  suivie  du  montant  total  de  l’ensemble  des  opérations réalisées. |

| action         | annuler saisie érronée                                       |
| -------------- | ------------------------------------------------------------ |
| acteur         | caissier                                                     |
| description    | annuler une saisie                                           |
| déroulé normal | retirer la clef du tiroire qui provoque l'arret de la caisse |

| action         | commander                                                                                                                                                                                                                                                   |
| -------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| acteur         | détaillant                                                                                                                                                                                                                                                  |
| description    | passer une commande                                                                                                                                                                                                                                         |
| déroulé normal | Le  détaillant  peut  choisir  un  article  dans  la  liste  et  demander  la génération d’une commande. Le système lui demande alors le nombre ou la quantité puis il imprime une commande au fournisseur. Le détaillant a la responsabilité de la poster. |

| action          | consulter les stocks                                                                                      |
| --------------- | --------------------------------------------------------------------------------------------------------- |
| acteur          | détaillant                                                                                                |
| description     | consulter les stocks                                                                                      |
| déroullé normal | le détaillant affiche sur la console principale le détail des stocks et peut si il le souhaite l'imprimer |

| action          | modifier les informations                                                                                                                             |
| --------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------- |
| acteur          | détaillant                                                                                                                                            |
| description     | modifier les informations des produits dans la base de donnée                                                                                         |
| déroullé normal | le détaillant séléctionne une ligne de produit qui va ouvrir une boite de dialogue et lui permettre de modifier toutes les informations à son propos. |


