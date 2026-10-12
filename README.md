# prueba-asv-backend

Backend REST de pruebas desarrollado con Java 17 y Spring Boot para una aplicación de control meteorológico.

## Requisitos

- JDK 17.
- Git.
- Conexión a Internet para descargar Maven y las dependencias durante la primera ejecución.

El proyecto incluye Maven Wrapper, por lo que no es necesario instalar Maven por separado.

## Descargar el proyecto

```bat
git clone https://github.com/pepeamat1996/prueba-asv-backend.git
cd prueba-asv-backend
```

## Comprobar Java

```bat
java -version
```

Debe utilizarse Java 17. Si hay varias versiones de Java instaladas, comprueba que `JAVA_HOME` y `PATH` apuntan al JDK correcto.

## Compilar el proyecto

Desde la carpeta raíz:

```bat
mvnw.cmd clean package
```

## Ejecutar la aplicación

```bat
mvnw.cmd spring-boot:run
```

Por defecto, el backend se inicia en:

http://localhost:8080

## API de prueba

La aplicación expone este endpoint:

`GET http://localhost:8080/api/saludo`

Al abrir la URL en el navegador, debe mostrarse el mensaje de prueba configurado en el controlador.