CLASS_INPUT =	./FONTS/Domini/*.java \
				./FONTS/ControladorsDomini/*.java \
				./FONTS/Dades/*.java \
				./FONTS/Stubs/*.java \
				./FONTS/Excepcions/*.java \
				./FONTS/Presentacio/*.java \
				./FONTS/Presentacio/views/*.java

CLASS_OUTPUT =	./EXEnoEntrega/out/

JAR_OUTPUT =	./EXEnoEntrega/

JUNIT_JARS = ./FONTS/lib/junit-4.13.2.jar:./FONTS/lib/hamcrest-core-1.3.jar

JSON_JARS = ./FONTS/lib/json-simple-1.1.jar

JUNIT_TESTS = ./FONTS/JUnit/*.java

EXE = ./EXE/

EXE1 = ./EXE1/

all:
	javac -d $(CLASS_OUTPUT) -cp FONTS:$(JSON_JARS) $(CLASS_INPUT)
	javac -cp $(JUNIT_JARS):$(JSON_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

jars:
	javac -cp FONTS:$(JSON_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./FONTS/Presentacio/Main.java
	jar cmvf ./FONTS/Presentacio/Main.mf $(JAR_OUTPUT)Main.jar -C $(CLASS_OUTPUT) . -C ./FONTS/lib/ .

jarsDrivers:
	javac -cp FONTS:$(JSON_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./FONTS/Drivers/DriverAlgorismeQAP.java ./FONTS/Drivers/DriverHungarianAlgorithm.java ./FONTS/Drivers/DriverDades.java ./FONTS/Drivers/DriverLectorFreq.java ./FONTS/Drivers/DriverTeclats.java ./FONTS/Drivers/DriverDominiv2.java ./FONTS/Presentacio/Main.java
	jar cmvf ./FONTS/Drivers/MF/DriverHungarianAlgorithm.mf $(JAR_OUTPUT)DriverHungarianAlgorithm.jar -C $(CLASS_OUTPUT) . -C ./FONTS/lib/ .
	jar cmvf ./FONTS/Drivers/MF/DriverAlgorismeQAP.mf $(JAR_OUTPUT)DriverAlgorismeQAP.jar -C $(CLASS_OUTPUT) . -C ./FONTS/lib/ .
	jar cmvf ./FONTS/Drivers/MF/DriverDades.mf $(JAR_OUTPUT)DriverDades.jar -C $(CLASS_OUTPUT) . -C ./FONTS/lib/ .
	jar cmvf ./FONTS/Drivers/MF/DriverDominiv2.mf $(JAR_OUTPUT)DriverDominiv2.jar -C $(CLASS_OUTPUT) . -C ./FONTS/lib/ .
	jar cmvf ./FONTS/Drivers/MF/DriverLectorFreq.mf $(JAR_OUTPUT)DriverLectorFreq.jar -C $(CLASS_OUTPUT) . -C ./FONTS/lib/ .
	jar cmvf ./FONTS/Drivers/MF/DriverTeclats.mf $(JAR_OUTPUT)DriverTeclats.jar -C $(CLASS_OUTPUT) . -C ./FONTS/lib/ .
	jar cmvf ./FONTS/Drivers/MF/DriverTeclats.mf $(JAR_OUTPUT)DriverTeclats.jar -C $(CLASS_OUTPUT) . -C ./FONTS/lib/ .

executaDriverHungarianAlgorithm: jarsDrivers
	java -cp ./EXEnoEntrega/DriverHungarianAlgorithm.jar:./FONTS/lib/json-simple-1.1.jar Drivers.HungarianAlgorithm

executaDriverDominiv2: jarsDrivers
	java -cp ./EXEnoEntrega/DriverDominiv2.jar:./FONTS/lib/json-simple-1.1.jar Drivers.DriverDominiv2

executaDriverLector: jarsDrivers
	java -cp ./EXEnoEntrega/DriverLectorFreq.jar:./FONTS/lib/json-simple-1.1.jar Drivers.DriverLectorFreq

executaDriverAlgorismeQAP: jarsDrivers
	java -cp ./EXEnoEntrega/DriverAlgorismeQAP.jar:./FONTS/lib/json-simple-1.1.jar Drivers.DriverAlgorismeQAP

executaMain: jars
	java -cp ./EXEnoEntrega/Main.jar:./FONTS/lib/json-simple-1.1.jar Presentacio.Main

executaMainExe:
	java -cp ./EXE/Main/Main.jar:./FONTS/lib/json-simple-1.1.jar Presentacio.Main

fulltest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.MasterTestSuite

idiomatest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.IdiomaTest

alfabettest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.AlfabetTest

factoriatest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.FactoriaTest

frequenciestest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.LlistaFrequenciesTest

perfiltest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.PerfilTest

teclattest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.TeclatTest

persistenciaFreqtest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.CtrlPersFreqTest

persistenciaPerfiltest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.CtrlPersPerfilTest

persistenciaTeclatstest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.CtrlPersTeclatsTest

persistenciaAlfabetstest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.CtrlPersAlfabetsTest

persistenciaIdiomestest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.CtrlPersIdiomesTest

hungariantest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.HungarianAlgorithmTest

branchandboundtest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.BranchandBoundTest

greedytest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.GreedyTest

nodotest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.NodoTest

postest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.posTest

inputtest: all
	java -cp $(JUNIT_JARS):$(JSON_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.InputFrequenciesTest

exe1: jarsDrivers
	cp $(JAR_OUTPUT)/DriverDades.jar $(JAR_OUTPUT)/DriverTeclats.jar $(JAR_OUTPUT)/DriverDominiv2.jar $(EXE1)/Alfabets
	cp $(JAR_OUTPUT)/DriverDades.jar $(JAR_OUTPUT)/DriverTeclats.jar $(JAR_OUTPUT)/DriverDominiv2.jar $(EXE1)/Idiomes
	cp $(JAR_OUTPUT)/DriverDades.jar $(JAR_OUTPUT)/DriverTeclats.jar $(JAR_OUTPUT)/DriverDominiv2.jar $(EXE1)/LlistaFrequencies
	cp $(JAR_OUTPUT)/DriverDades.jar $(JAR_OUTPUT)/DriverTeclats.jar $(JAR_OUTPUT)/DriverDominiv2.jar $(EXE1)/Perfil
	cp $(JAR_OUTPUT)/DriverDades.jar $(JAR_OUTPUT)/DriverTeclats.jar $(JAR_OUTPUT)/DriverDominiv2.jar $(EXE1)/Teclats
	cp $(JAR_OUTPUT)/DriverDades.jar $(JAR_OUTPUT)/DriverTeclats.jar $(JAR_OUTPUT)/DriverDominiv2.jar $(JAR_OUTPUT)/DriverLectorFreq.jar $(EXE1)/CtrlFile
	cp $(JAR_OUTPUT)/DriverAlgorismeQAP.jar $(EXE1)/Algorisme
	cp $(JAR_OUTPUT)/DriverHungarianAlgorithm.jar $(EXE1)/HungarianAlgorithm

exe: jars
	cp $(JAR_OUTPUT)/Main.jar $(EXE)/Main

clean:
	rm -r ./EXEnoEntrega/out/*

cleanSaves:
	rm ./DATA/Saves/AlfabetsSistema.json
	rm ./DATA/Saves/IdiomesSistema.json
	rm ./DATA/Saves/LlistesUsuarisActius.json
	rm ./DATA/Saves/TeclatsusuarisActius.json
	rm ./DATA/Saves/UsuarisActius.json

cleanExe1:
	rm ./EXE1/Alfabets/*
	rm ./EXE1/Algorisme/*
	rm ./EXE1/CtrlFile/*
	rm ./EXE1/HungarianAlgorithm/*
	rm ./EXE1/Idiomes/*
	rm ./EXE1/LlistaFrequencies/*
	rm ./EXE1/Perfil/*
	rm ./EXE1/Teclats/*

cleanExe:
	rm ./EXE/Main/Main.jar

distclean:
	rm -r ./EXEnoEntrega/*