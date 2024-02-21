# F2X_Technical_Test

Este proyecto fue diseñado en Java en su verison 18, utilizando Maven como gestor de dependencias y se 
implementó la **libreria RestAssured** para realizar las pruebas API.

**Ejecucion de las pruebas**

Las pruebas se pueden ejecutar de 2 formas:
1. Ejecutando la clase **webServiceTest.java** que se encuentra ubicada en el path: src/test/java/tests/webServiceTest.java. Se utiliza JUnit para su ejecución
2. Por medio de la terminal ejecutando el comando **mvn clean test** el cual ejecuta todos los test del proyecto.

Una ves ejecutadas las pruebas, se creará un carpeta llamada **reports** dentro del proyecto, la cual genera un archivo .html que al abrirlo en el IDE y seleccionando un navegador de preferencia se puede visualizar el informe de las pruebas ejecutadas.