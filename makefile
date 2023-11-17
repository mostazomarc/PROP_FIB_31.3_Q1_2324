CLASS_INPUT =	./FONTS/Domini/*.java \
				./FONTS/Drivers/*.java \
				./FONTS/ControladorsDomini/*.java \
				./FONTS/Dades/*.java \
				./FONTS/Stubs/*.java \
				./FONTS/Excepcions/*.java

CLASS_OUTPUT =	./EXE/out/

JAR_OUTPUT =	./EXE/

JUNIT_JARS = ./FONTS/lib/junit-4.13.2.jar:./FONTS/lib/hamcrest-core-1.3.jar

JUNIT_TESTS = ./FONTS/JUnit/*.java



all:
	javac -d $(CLASS_OUTPUT) -cp FONTS $(CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

jars:
	javac -d $(CLASS_OUTPUT) $(CLASS_INPUT) ./FONTS/Drivers/DriverAlgorismeQAP.java ./FONTS/Drivers/DriverHungarianAlgorithm.java ./FONTS/Drivers/DriverDades.java ./FONTS/Drivers/DriverTeclats.java ./FONTS/Drivers/DriverDominiv2.java
	jar cmvf ./FONTS/Drivers/MF/DriverHungarianAlgorithm.mf $(JAR_OUTPUT)DriverHungarianAlgorithm.jar -C $(CLASS_OUTPUT) .

executaDriverHungarianAlgorithm:
	java -jar $(JAR_OUTPUT)DriverHungarianAlgorithm.jar

executaDriverDomini:
	java -cp ./EXE Drivers.DriverDomini

executaDriverDominiv2:
	java -cp ./EXE Drivers.DriverDominiv2

executaDriverAlgorismeQAP:
	java -cp ./EXE Drivers.DriverAlgorismeQAP

fulltest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.MasterTestSuite

idiomatest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.IdiomaTest

alfabettest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.AlfabetTest

factoriatest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.FactoriaTest

frequenciestest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.LlistaFrequenciesTest

perfiltest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.PerfilTest

teclattest: all
	java -cp $(JUNIT_JARS):$(CLASS_OUTPUT) org.junit.runner.JUnitCore JUnit.TeclatTest

clean:
	rm -r ./EXE/*

