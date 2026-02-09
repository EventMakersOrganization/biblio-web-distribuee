# 📚 Système de Gestion de Bibliothèque Universitaire

## 📖 Présentation du projet
Le Système de Gestion de Bibliothèque Universitaire** est une application informatique visant à automatiser et optimiser la gestion des bibliothèques universitaires.  
Il permet une meilleure organisation des ressources documentaires et facilite l’accès des étudiants et du personnel aux services de la bibliothèque.

Ce système centralise la gestion des livres, des emprunts, des réservations ainsi que des avis et notations, tout en assurant une expérience utilisateur fluide et sécurisée.

---

## 🎯 Objectifs du projet
- Automatiser la gestion des livres et des utilisateurs
- Simplifier les processus d’emprunt, de retour et de réservation
- Améliorer l’accessibilité aux ressources de la bibliothèque
- Offrir un système d’avis et de notation des livres
- Garantir la fiabilité et la sécurité des données

---

## 👥 Acteurs du système

### 👤 Étudiant
- Consulter le catalogue des livres
- Emprunter et retourner des livres
- Réserver des livres indisponibles
- Consulter l’historique de ses emprunts
- Publier un avis avec note et commentaire sur un livre
- Modifier ou supprimer ses propres avis

### 👨‍💼 Bibliothécaire
- Ajouter, modifier et supprimer des livres
- Gérer les emprunts et les retours
- Suivre les réservations
- Gérer les utilisateurs
- Consulter les statistiques de la bibliothèque

---

## ⚙️ Fonctionnalités principales
- Authentification et gestion des rôles (Étudiant / Bibliothécaire)
- Gestion du catalogue des livres
- Système d’emprunt et de retour
- Gestion des réservations
- Système d’avis et de notation des livres
- Calcul automatique de la note moyenne d’un livre
- Recherche et filtrage des livres
- Gestion des utilisateurs

---

## 🧩 Architecture du système
Le projet adopte une architecture modulaire orientée services, garantissant :
- Une séparation claire des responsabilités
- Une meilleure maintenabilité du code
- Une évolutivité facilitée

### Modules principaux :
- Service Utilisateur
- Service Livre
- Service Emprunt
- Service Réservation
- Service  Activités & Inscriptions
- Service Avis 

Les échanges entre les modules se font via des API REST.

---



