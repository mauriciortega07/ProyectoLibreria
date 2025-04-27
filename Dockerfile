#IMAGEN A UTILIZAR
FROM openjdk:11-jdk-slim

#DIRECTORIO DEL PROYECTO
WORKDIR /app

#COPIAMOS EL jar AL DOCKER
COPY contexto/build/libs/contexto-all.jar proyectoJavaLibreriaEbac.jar

#COMANDO PARA INICIAR EL PROYECTO
ENTRYPOINT ["java", "-jar", "proyectoJavaLibreriaEbac.jar"]
