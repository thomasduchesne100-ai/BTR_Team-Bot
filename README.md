# 🏆 Gorilla Tag — Système de Classement

Un système de classement Discord permettant de créer et gérer facilement le classement des joueurs de chaque team Gorilla Tag.

## ⚙️ Fonctionnement

Utilisez la commande :

```text
!setup
```

Le bot affiche un panneau de configuration avec le bouton :

> 🏆 Mettre Un Classement

En cliquant dessus, vous pouvez sélectionner le rôle Discord correspondant à une team.

Le bot récupère automatiquement tous les joueurs possédant ce rôle et les ajoute au classement.

## 📊 Classement

Exemple :

```text
🏆 CLASSEMENT — TEAM 1

🥇 PlayerOne
🥈 PlayerTwo
🥉 PlayerThree
4️⃣ PlayerFour
5️⃣ PlayerFive
6️⃣ PlayerSix
```

## 🔄 Modifier les positions

Les joueurs peuvent être réorganisés grâce au système de **glisser-déposer**.

Par exemple :

```text
Avant :

🥇 PlayerOne
🥈 PlayerTwo
🥉 PlayerThree
4️⃣ PlayerFour
```

Si `PlayerFour` est déplacé au-dessus de `PlayerTwo` :

```text
🥇 PlayerOne
🥈 PlayerFour
🥉 PlayerTwo
4️⃣ PlayerThree
```

L'ordre est ensuite sauvegardé.

## 👥 Gestion des Teams

Chaque classement peut être associé à un rôle Discord.

Exemple :

```text
TEAM 1 → rôle TEAM 1
TEAM 2 → rôle TEAM 2
TEAM 3 → rôle TEAM 3
TEAM 4 → rôle TEAM 4
TEAM 5 → rôle TEAM 5
TEAM 6 → rôle TEAM 6
```

Le bot utilise les rôles pour déterminer quels joueurs appartiennent à chaque classement.

## 💾 Sauvegarde

Les classements sont sauvegardés afin que l'ordre des joueurs ne soit pas perdu lorsque le bot redémarre.

## 🎮 Utilisation

### 1. Créer les rôles

Créez vos rôles Discord :

```text
TEAM 1
TEAM 2
TEAM 3
TEAM 4
TEAM 5
TEAM 6
```

### 2. Lancer le bot

Une fois le bot connecté au serveur, utilisez :

```text
!setup
```

### 3. Créer un classement

Cliquez sur :

```text
🏆 Mettre Un Classement
```

Puis sélectionnez le rôle de la team.

### 4. Organiser les joueurs

Réorganisez les joueurs dans l'ordre souhaité avec le système de glisser-déposer.

### 5. Sauvegarder

Cliquez sur :

```text
💾 Sauvegarder
```

Le classement est alors enregistré.

---

## 🛠️ Technologies

* ☕ Java
* 📦 Maven
* 🤖 JDA
* 🌐 Interface Web
* 🗄️ Système de sauvegarde

## 📌 Exemple

```text
🏆 TEAM 2

🥇 Novax
🥈 Xortex
🥉 Player123
4️⃣ Gorilla
5️⃣ Monkey
```

Le classement peut être modifié à tout moment sans devoir retirer ou remettre les rôles Discord.
