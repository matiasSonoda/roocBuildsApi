# ==========================================
# ETAPA 1: Constructor (Builder)
# ==========================================
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /build

  # Caché Estratégico: Copiamos SOLO el pom.xml y el wrapper de Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

  # Descargamos las dependencias (Si el pom.xml no cambia, Docker usa el caché aquí y se salta este paso de 2 minutos)
RUN ./mvnw dependency:go-offline

  # Ahora sí, copiamos el código fuente y compilamos el .jar
COPY src src
RUN ./mvnw clean package -DskipTests

  # ==========================================
  # ETAPA 2: Extractor de Capas
  # ==========================================
FROM builder AS extractor
WORKDIR /build
RUN java -Djarmode=tools -jar target/*-exec.jar extract --layers --launcher --destination extracted

  # ==========================================
  # ETAPA 3: Producción (Imagen Final Liviana)
  # ==========================================
  # Usamos JRE (Solo entorno de ejecución, no JDK) sobre Alpine Linux (pesa ~5MB)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN mkdir -p /app/images
RUN chmod 777 /app/images

  # Creamos un usuario sin privilegios por seguridad (Fase 4 adelantada)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

  # Copiamos las capas desde la Etapa 2 en orden estricto (de las que menos cambian a las que más cambian)
COPY --from=extractor /build/extracted/dependencies/ ./
COPY --from=extractor /build/extracted/spring-boot-loader/ ./
COPY --from=extractor /build/extracted/snapshot-dependencies/ ./
COPY --from=extractor /build/extracted/application/ ./

  # Exponemos el puerto de tu API
EXPOSE 8080

  # Comando de arranque optimizado para Spring Boot Layered
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]