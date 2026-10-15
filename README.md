# Prueba ASV Backend

Backend desarrollado con Java y Spring Boot para consultar municipios y obtener la predicción de temperatura a través de la API de AEMET.

## Requisitos previos

Antes de ejecutar la aplicación, es necesario tener instalado:

- **JDK 17** o compatible con Java 17.
- **Git**, para clonar el repositorio.
- Una **API key de AEMET OpenData**, necesaria para consultar sus datos (obtener desde https://opendata.aemet.es/centrodedescargas/inicio).
- No es un requisito explícito, pero este readme está pensado para realizar la ejecución desde Windows.

No es necesario instalar Maven, ya que el proyecto incluye Maven Wrapper.

Para comprobar la instalación de Java, lanzar este comando: ```
cmd java -version 
```
Si se tienen varias versiones de java instaladas, revisar que se utiliza la de la jdk17.


## Descargar el proyecto

Clonar el repositorio:
```cmd
git clone https://github.com/pepeamat1996/prueba-asv-backend.git
cd prueba-asv-backend
```


## Configuración

El proyecto obtiene la clave de API mediante la variable de entorno AEMET_API_KEY. Por motivos de seguridad, la clave no se almacena directamente en el código.

Antes de ejecutar el backend, hay que definir esta variable en la terminal que se vaya a utilizar.

**Opción A: PowerShell**

Abre PowerShell en la carpeta raíz del proyecto y ejecuta:

```powershell
$env:AEMET_API_KEY="TU_API_KEY_REAL"
```

**Opción B: CMD (símbolo del sistema)**

Abre CMD en la carpeta raíz del proyecto y ejecuta:

```cmd
set AEMET_API_KEY=TU_API_KEY_REAL
```

En ambos casos, sustituye TU_API_KEY_REAL por tu clave personal de AEMET. La variable de entorno debe configurarse y la aplicación debe ejecutarse desde la misma terminal. Al cerrar esa terminal, la variable definida de esta manera deja de estar disponible.


## Arrancar la aplicación

Desde la raíz del proyecto:

```cmd
mvnw.cmd -pl application -am install -DskipTests
mvnw.cmd -pl application spring-boot:run
```

Espera a que Spring Boot indique que la aplicación se ha iniciado correctamente.

Salvo que se haya configurado otro puerto, Spring Boot utiliza el puerto `8080`.

### 3. Endpoints disponibles

Una vez iniciado el backend, estará disponible en `http://localhost:8080`, salvo que se configure otro puerto.

**Obtener municipios**

```http
GET http://localhost:8080/api/municipio/obtenerMunicipios
```

**Obtener la predicción de temperatura**

```http
GET http://localhost:8080/api/prediccion/obtenerPrediccionTemperatura?codigoMunicipio=03014&unidadTemperatura=G_CEL
```

Parámetros:
- `codigoMunicipio`: código del municipio.
- `unidadTemperatura`: unidad de temperatura. Utiliza `G_CEL` para grados Celsius o `G_FAH` para grados Fahrenheit.

## Notas
- Las consultas dependen de la disponibilidad del servicio de AEMET.
- La API key debe estar correctamente configurada para que las consultas externas funcionen.
