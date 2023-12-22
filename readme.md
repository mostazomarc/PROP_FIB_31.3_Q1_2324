# PROP_FIB
Projecte de l'assignatura PROP de la FIB.

## Participants
- Mostazo González, Marc
- Tajahuerce Brulles, Arnau
- Costabella Moreno, Agustí
- Torredemer Pueyo , Francisco

## Emails
- marc.mostazo@estudiantat.upc.edu
- arnau.tajahuerce@estudiantat.upc.edu
- agusti.costabella@estudiantat.upc.edu
- francisco.torredemer@estudiantat.upc.edu

## Instruccions per compilar i executar projecte
- **Compilar**
  - Per compilar el projecte **al directori `./EXE/Main` (directori entrega)** s'ha de fer `make exe` al directori root
  - Per compilar el projecte **fora del directori `./EXE/` (fora del directori d'entrega)** s'ha de fer `make jars` al directori root
  - Per compilar el projecte **sense fer executables** s'ha de fer `make all` al directori root
  - Per compilar els drivers s'ha de fer `make jarsDrivers` al directori root
- **Executar**
  - Per executar el projecte **del directori `./EXE/Main` (directori entrega)** s'ha de fer `make executaMainExe` al directori root
  - Per **compilar i executar** el projecte **fora del directori `./EXE/` (fora del directori d'entrega)** s'ha de fer `make executaMain` al directori root
  - Per executar els drivers s'ha de fer `make executaDriver'NomDriver'` al directori root

## Instruccions per netejar el projecte
  - Per netejar les **classes compilades** del projecte s'ha de fer `make clean` al directori root
  - Per netejar els **fitxers amb dades del sistema** s'ha de fer `make cleanSaves` al directori root
  - Per netejar **l'executable del projecte** a `./EXE/Main` s'ha de fer `make cleanExe` al directori root
  - Per netejar **tots els arxius comilats a `./EXEnoEntrega/`** s'ha de fer `make distclean` al directori root

# Estructura del Projecte

- **DATA/**
    - Directori amb la informació guardada del projecte i amb els fitxers proporcionats per a ser utilitzats com a input del programa.

- **DOCS/**
    - Aquest directori conté tota la documentació de les diferents entregues.
        - **DOCS1aEntrega/**
        - **DOCS2aEntrega/**
        - **DOCS3aEntrega/**

- **EXE/**
    - Subdirectori amb els executables de l'entrega 3.
        - **Main/**
            - Directori amb l'executable del programa principal de l'entrega 3.

- **EXE1/**
    - Aquest subdirectori conté els executables (de la primera entrega) per provar les classes o funcionalitats implementades. Està dividit per subdirectoris, un per cada classe a provar amb els executables necessaris.
        - **Alfabets/**
            - Conté el `.jar` amb el/s drivers necessaris per fer els jocs de prova de la classe Alfabet.
        - **Algorisme/**
            - Conté el `.jar` amb el/s drivers necessaris per fer els jocs de prova de l'Algorisme.
        - **CtrlFile/**
            - Conté el `.jar` amb el/s drivers necessaris per fer els jocs de prova de la classe CtrlFile.
        - **HungarianAlgorithm/**
            - Conté el `.jar` amb el/s drivers necessaris per fer els jocs de prova de la classe HungarianAlgorithm.
        - **Idiomes/**
            - Conté el `.jar` amb el/s drivers necessaris per fer els jocs de prova de la classe Idiomes.
        - **LlistaFrequencies/**
            - Conté el `.jar` amb el/s drivers necessaris per fer els jocs de prova de la classe LlistaFrequencies.
        - **Perfil/**
            - Conté el `.jar` amb el/s drivers necessaris per fer els jocs de prova de la classe Perfil.
        - **Teclats/**
            - Conté el `.jar` amb el/s drivers necessaris per fer els jocs de prova de la classe Teclats.

- **FONTS/**
    - En aquest subdirectori es troben tots els codis de les classes del sistema, seguint l'estructura de packages. També conté el makefile per compilar/executar el projecte.
        - **ControladorsDomini/**
            - Conte el `.java` del controlador de domini encarregat de comunicar-se amb les altres capes.
        - **Dades/**
            - Conte els `.java` dels controladors de persistència de les classes del sistema i el controlador per llegir arxius de txt.
        - **Drivers/**
            - Conte els `.java` dels drivers que s'usen per provar les funcionalitats del sistema.
        - **Excepcions/**
            - Conte els `.java` de les excepcions que hem creat per cada sistema.
        - **JUnit/**
            - Conte el `.java` dels tests usats per provar les diferents classes del sistema.
        - **lib/**
            - Conte les llibreries necessàries per executar els tests, junit i hamcrest-core; i per utilitzar els `.json`.
        - **Presentacio/**
            - Conte els `.java` de les classes de la capa de presentació.
        - **Stubs/**
            - Conte els stubs utilitzats per provar el programa.
        - **makefile**
            - Conte les ordres per compilar i executar el projecte.

- **Lliuraments/**
  - En aquest subdirectori es troben els fitxers comprimits de cada entrega.
    - **Lliurament-1.0-Subgrup31.3.zip**
        - `.zip` de la primera entrega.
    - **Lliurament-2.0-Subgrup31.3.zip**
        - `.zip` de la segona entrega.
    - **Lliurament-3.0-Subgrup31.3.zip**
        - `.zip` de la tercera entrega.

- **Equip.txt**
  - Fitxer amb la informació dels membres del grup.
- **README.md**
  - Fitxer amb la informació del projecte.
- **makefile**
  - Fitxer amb les ordres per compilar i executar el projecte.
- **.gitignore**
  - Fitxer amb els arxius que no es volen pujar al repositori.
- **Enunciat t2324.pdf**
  - Enunciat del projecte.
- **normativa-1q2324.pdf**
  - Normativa de l'assignatura **PROP-Q1-23/24**.