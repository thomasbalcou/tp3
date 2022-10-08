
# TP 3 et 4




## Partie Spring

Pour executer le projet, il faut run la classe SampleSimpleApplication dans le package sample.  

La partie injection de dépendance est contenue dans le package sample.simple.*  
Pour le scénario 1, le client fournit la reference de l'article, la quantité, le magasin verifie la disponibilité puis le client commande son article, le magasin fait le transfert, met à jour le stock et reaprovisionne si plus d'article.  
Pour le scénario 2, le client ajoute 3 articles dans le panier puis paie.  

le modèle est dans le package sample.data.jpa.domain.  
Il contient le modele qui represente seulement une table avec les articles et une table pour les articles dans le panier du client.  

La dao est dans le package sample.data.jpa.service.  

Les services sont dans sample.data.jpa.web. Ils permettent de visualiser tout les élements d'une classe, d'en afficher un par son id, d'en créer un ou le modifer et de le supprimer.  

La partie AOP est dans la classe ServiceMonitor dans le package sample.aop.monitor. La classe permet de logger avant les appels des fonctions dans sample.simple.* , de logger les service rest et de logger et de verifier les execeptions autour de l'appel de la fonction transfert de la classe bank  

## Partie authentification avec Keycloak

La partie authenthification permet de voir la liste des produits pour un utilisateur en cas d'acces à index.  
Et en cas d'accès à admin, cela permet de modifier la quantité et le prix d'un article.  
Seul les utilisateurs peuvent accèder à index et seulement les admins peuvent acceder à admin et modifier le contenu de la table article en base de données.
