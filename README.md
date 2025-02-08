# Lab03CVDS
## Integrantes
Yojhan Toro - Ivan Cubillos
## Desarrollo
En primer lugar se crean las clases espejo para poder operar los metodos de prueba de una buena manera.
![alt text](image/repositories.png)
Posteriormente se agrega Jacoco al archivo pom.xml para que nos arroje la cobertura de pruebas.
Como paso siguiente se programan las pruebas con los resultados esperados y se ejecuta el pom para rectificar que las este probando de manera adecuada. Nos arroja el siguiente resultado:
![alt text](image/failedTestJacoco.png)
Posterior a eso se codifica el metodo, para que pasen las pruebas. Se ejecuta nuevamente el pom y obtenemos el siguiente resultado:
![alt text](image/approvedTestJacoco.png)
Para que pasará el metodo se uso el siguiente metodo:
![alt text](image/initialMethodAddBook.png)