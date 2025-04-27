#IMAGEN A UTILIZAR
FROM openjdk:11

#DIRECTORIO DEL PROYECTO
WORKDIR /app

#COPIAMOS EL jar AL DOCKER
COPY contexto/build/libs/contexto.jar proyecto.jar

#COMANDO PARA INICIAR EL PROYECTO
ENTRYPOINT ["java", "-jar", "proyecto.jar"
