# 🌐 Advanced UDP File Management System (Full Stack)

Ky projekt përfaqëson një sistem të avancuar të komunikimit në rrjet, i ndërtuar me **Java**, që integron protokollin **UDP** për shkëmbim të dhënash dhe **HTTP** për monitorim. Sistemi është projektuar për të menaxhuar skedarët në distancë përmes një arkitekture multithreaded.

---

## 📑 Përmbajtja
1. [Arkitektura e Sistemit](#-arkitektura-e-sistemit)
2. [Modulet dhe Klasat](#-modulet-dhe-klasat)
3. [Siguria dhe Privilegjet](#-siguria-dhe-privilegjet)
4. [Protokolli i Komandave](#-protokolli-i-komandave)
5. [Udhëzimet e Instalimit](#-udhëzimet-e-instalimit)

---

## 🏗️ Arkitektura e Sistemit

Sistemi bazohet në dy motorë - engines që operojnë paralelisht, duke shfrytëzuar fuqinë e **Multithreading**:

### 1. UDP Engine (Porti 1234)
Ky është kanali kryesor i komunikimit. Përdor `DatagramSocket` për të pranuar kërkesat nga klientët. Përparësia e UDP në këtë projekt është shpejtësia dhe efikasiteti në rrjete lokale.
* **Connectionless:** Serveri nuk mban një lidhje të hapur, por proceson paketat në mënyrë të pavarur.
* **Priority Queue:** Sistemi identifikon paketat nga Adminët dhe i proceson ato pa vonesa artificiale.

### 2. HTTP Engine (Porti 8080)
Një server paralel i bazuar në `HttpServer` që shërben si një **Dashboard Monitorimi**. Ai ofron të dhëna në kohë reale mbi gjendjen e serverit pa ndërhyrë në trafikun UDP.

---

## 🧩 Modulet dhe Klasat

| Klasa | Funksioni i Detajuar |
| :--- | :--- |
| **`UDPServer`** | Pika hyrëse. Menaxhon `while(true)` loop-in dhe shpërndan punën te handler-ët. |
| **`Client.java`** | Ndërfaqja e përdoruesit. Lejon dërgimin e komandave dhe pranimin e përgjigjeve. |
| **`ClientHandler`** | Analizuesi i komandave (Parser). Ekzekuton logjikën e I/O (lexim, fshirje, kërkim). |
| **`ClientManager`** | Zbaton rregullat e "Threshold". Nuk lejon më shumë se 10 klientë aktivë. |
| **`TimeoutManager`**| Përdor `ConcurrentHashMap` për të regjistruar kohën e fundit të aktivitetit. |
| **`MessageLogger`** | Shkruan çdo transaksion në `server_logs.txt` me formatin `[Data Ora] IP: Mesazhi`. |
| **`HttpServer`** | Krijon një endpoint `/stats` që kthen gjendjen e sistemit. |

---

## 🛡️ Siguria dhe Privilegjet (RBAC)

Sistemi implementon **Role-Based Access Control**. Përdoruesit ndahen në dy kategori:

1.  **ADMIN:**
    * Qasje të plotë: `read()`, `write()`, `execute()`.
    * Mund të fshijë skedarë (`/delete`) dhe të kërkojë metadata (`/info`).
    * **Prioritet:** Paketat e Adminit nuk i nënshtrohen `Thread.sleep()` vonesave.
2.  **USER:**
    * Qasje e kufizuar: Vetëm `read()`.
    * Mund të listojë dhe lexojë skedarë, por nuk mund të modifikojë sistemin.

---

## 📁 Protokolli i Komandave

| Komanda | Parametri | Shpjegimi |
| :--- | :--- | :--- |
| `/list` | - | Shfaq listën e të gjithë skedarëve në folderin root. |
| `/read` | `<filename>` | Kthen përmbajtjen tekstuale të skedarit të kërkuar. |
| `/info` | `<filename>` | Metadata: Madhësia në bytes dhe data e modifikimit. |
| `/search`| `<keyword>` | Kërkon skedarët që përmbajnë një fjalë të caktuar në emër. |
| `/delete`| `<filename>` | (VETËM ADMIN) Fshin skedarin fizikisht nga disku. |
| `exit` | - | Mbyll sesionin e klientit në mënyrë të rregullt. |

---

## 🛠️ Udhëzimet e Instalimit dhe Testimit

### Hapi 1: Përgatitja e Rrjetit
Për testime në pajisje të ndryshme:
1. Gjeni IP-në e Serverit me `ipconfig` (p.sh., `192.168.1.15`).
2. Sigurohuni që të gjitha pajisjet janë në të njëjtin Wi-Fi/LAN.
3. Fikni **Windows Firewall** ose lejoni portin `1234`.


