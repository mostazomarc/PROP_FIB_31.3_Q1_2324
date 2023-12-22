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