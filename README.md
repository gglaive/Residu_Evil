TODO

- Gestion des shortcuts (raccourcis) (via une fonction unlock?)
	- accès bloqué au début
	- possibilité de débloquer l'accès une fois arrivé dans la bonne pièce
	- l'accès devient un chemin normal en permanence après déblocage
	- ajouter un booleen dans les salles ?

- Gestion des accès nécessitant des objets: fonction use sur des directions (sortie nécessitant les 2 médaillons)
	- message disant genre "chemin pas encore ouvert.." si on essaie d'aller dans la direction
	- eventuellement un message disant quels objets il nous manque

- fonction use
	- verification de la location avant utilisation (medaillon qui ne s'utilisent que dans "station_hall")

- modifier le contenu de help

- ajouter un etat de fin de partie (cas de victoire/ cas de mort)
	- game over quand pv = 0
	- victoire quand salle actuelle = la salle accessible avec les deux medaillons

- ajouter une photo pour chaque pièce

- ajouter une attaque pour les zombies
	- inflige x degats apres x "tours" (actions du joueur)
	- creation d'un game over quand pv du joueur = 0

- fonctions go et flee
	- empecher l'utilisation de go si il y a des zombies dans la salle, ou remplacer go par flee (dynamique)
	- empecher l'utilisation de flee si il n'y a pas de zombies, ou remplacer flee par go (dynamique)

BONUS

- ajouter des objets
	- des armes : fusil à pompe (genre 3 degat), magnum (~5), et donc des munitions qui vont avec
	- du soin : herbe rouge, à combiner avec herbe normale pour restaurer toute sa vie, ou un spray de premier soin à la place
	- des objets clés : une clé pour déverouiller une certaine porte (ex: clé pour ouvrir l'armory), une lampe torche pour avancer dans une piece sombre etc..

- ajouter des ennemis
	- un boss : un gros zombie (gilet jaune) avec beaucoup de pv, avant de finir le jeu
	- des variantes de monstre : un gilet jaune à casque, ou un chien zombie ..

- nouvelles zones : agrandir le commissariat, ou rajouter d'autres niveaux (egouts et labo)

- fonction shoot
	- modifier degats fixes par degats random ?
	- ajout possibilité de coups critiques ? degats x2
