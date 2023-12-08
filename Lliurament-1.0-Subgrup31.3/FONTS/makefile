CLASS_INPUT =	./Domini/*.java \
				./ControladorsDomini/*.java \
				./Dades/*.java \
				./Stubs/*.java \
				./Excepcions/*.java

CLASS_OUTPUT =	../EXEnoEntrega/out/

JAR_OUTPUT =	../EXEnoEntrega/

JUNIT_JARS = ./lib/junit-4.13.2.jar:./lib/hamcrest-core-1.3.jar

JUNIT_TESTS = ./JUnit/*.java

EXE = ../EXE/

all:
	javac -d $(CLASS_OUTPUT) -cp FONTS $(CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

jars:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./Drivers/DriverAlgorismeQAP.java ./Drivers/DriverHungarianAlgorithm.java ./Drivers/DriverDades.java ./Drivers/DriverLectorFreq.java ./Drivers/DriverTeclats.java ./Drivers/DriverDominiv2.java
	jar cmvf ./Drivers/MF/DriverHungarianAlgorithm.mf $(JAR_OUTPUT)DriverHungarianAlgorithm.jar -C $(CLASS_OUTPUT) .
	jar cmvf ./Drivers/MF/DriverAlgorismeQAP.mf $(JAR_OUTPUT)DriverAlgorismeQAP.jar -C $(CLASS_OUTPUT) .
	jar cmvf ./Drivers/MF/DriverDades.mf $(JAR_OUTPUT)DriverDades.jar -C $(CLASS_OUTPUT) .
	jar cmvf ./Drivers/MF/DriverDominiv2.mf $(JAR_OUTPUT)DriverDominiv2.jar -C $(CLASS_OUTPUT) .
	jar cmvf ./Drivers/MF/DriverLectorFreq.mf $(JAR_OUTPUT)DriverLectorFreq.jar -C $(CLASS_OUTPUT) .
	jar cmvf ./Drivers/MF/DriverTeclats.mf $(JAR_OUTPUT)DriverTeclats.jar -C $(CLASS_OUTPUT) .


exe: jars
	cp $(JAR_OUTPUT)/DriverDades.jar $(JAR_OUTPUT)/DriverTeclats.jar $(JAR_OUTPUT)/DriverDominiv2.jar $(EXE)/Alfabets
	cp $(JAR_OUTPUT)/DriverDades.jar $(JAR_OUTPUT)/DriverTeclats.jar $(JAR_OUTPUT)/DriverDominiv2.jar $(EXE)/Idiomes
	cp $(JAR_OUTPUT)/DriverDades.jar $(JAR_OUTPUT)/DriverTeclats.jar $(JAR_OUTPUT)/DriverDominiv2.jar $(EXE)/LlistaFrequencies
	cp $(JAR_OUTPUT)/DriverDades.jar $(JAR_OUTPUT)/DriverTeclats.jar $(JAR_OUTPUT)/DriverDominiv2.jar $(EXE)/Perfil
	cp $(JAR_OUTPUT)/DriverDades.jar $(JAR_OUTPUT)/DriverTeclats.jar $(JAR_OUTPUT)/DriverDominiv2.jar $(EXE)/Teclats
	cp $(JAR_OUTPUT)/DriverDades.jar $(JAR_OUTPUT)/DriverTeclats.jar $(JAR_OUTPUT)/DriverDominiv2.jar $(JAR_OUTPUT)/DriverLectorFreq.jar $(EXE)/CtrlFile
	cp $(JAR_OUTPUT)/DriverAlgorismeQAP.jar $(EXE)/Algorisme
	cp $(JAR_OUTPUT)/DriverHungarianAlgorithm.jar $(EXE)/HungarianAlgorithm

clean:
	rm -r .././EXEnoEntrega/out/*

cleanExe:
	rm .././EXE/Alfabets/*
	rm .././EXE/Algorisme/*
	rm .././EXE/CtrlFile/*
	rm .././EXE/HungarianAlgorithm/*
	rm .././EXE/Idiomes/*
	rm .././EXE/LlistaFrequencies/*
	rm .././EXE/Perfil/*
	rm .././EXE/Teclats/*

distclean:
	rm -r .././EXEnoEntrega/*