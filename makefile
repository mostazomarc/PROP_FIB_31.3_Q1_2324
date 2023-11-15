CLASS_INPUT =	./FONTS/Domini/*.java \
				./FONTS/Drivers/*.java \
				./FONTS/ControladorsDomini/*.java \
				./FONTS/Dades/*.java \
				./FONTS/Stubs/*.java

CLASS_OUTPUT =	./EXE/

JAR_OUTPUT =	./EXE/

JUNIT_JARS = ./FONTS/lib/junit-4.13.2.jar:./FONTS/lib/hamcrest-core-1.3.jar

JUNIT_TESTS = ./FONTS/JUnit/*.java



all:
	javac -d $(CLASS_OUTPUT) -cp FONTS $(CLASS_INPUT)
	javac -cp $(JUNIT_JARS) -d $(CLASS_OUTPUT) $(CLASS_INPUT) $(JUNIT_TESTS)

executaDriverDomini:
	java -cp ./EXE Drivers.DriverDomini

executaDriverHungarianAlgorithm:
	java -cp ./EXE Drivers.DriverHungarianAlgorithm

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
