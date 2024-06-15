# ClimaDataEngine

¡Hola! Soy Víctor Medina, y este es mi proyecto ClimaDataEngine. Este proyecto está diseñado para obtener datos del clima de OpenWeather y procesarlos periódicamente.

## Descripción

ClimaDataEngine es una aplicación en Java que obtiene datos del clima utilizando la API de OpenWeather. La aplicación está estructurada para ejecutar tareas periódicas que alimentan y envían datos a través de un productor de clima.

## Estructura del Proyecto
ClimaDataEngine-master/
├── analytics/
├── commons/
├── datalake-builder/
├── src/
│ └── main/
│ └── java/
│ ├── Main.java
│ ├── OpenWeatherProducer.java
│ └── Weather.java
├── .idea/
├── pom.xml
└── .DS_Store



## Requisitos

- Java 11 o superior
- Maven

## Configuración

1. Clona el repositorio en tu máquina local:
    ```sh
    git clone https://github.com/tu-usuario/ClimaDataEngine.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd ClimaDataEngine
    ```
3. Compila el proyecto usando Maven:
    ```sh
    mvn clean install
    ```
4. Configura las credenciales de la API de OpenWeather en el archivo de configuración correspondiente (por ejemplo, en `src/main/resources/application.properties`):
    ```properties
    openweather.apiKey=TU_API_KEY
    ```

## Uso

Para ejecutar la aplicación, usa el siguiente comando:
```sh
java -cp target/ClimaDataEngine.jar Main
```

La aplicación iniciará un proceso que obtendrá y enviará datos del clima periódicamente.

## Contribución

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

Haz un fork del repositorio.
Crea una nueva rama (git checkout -b feature/nueva-funcionalidad).
Realiza tus cambios y commitea (git commit -am 'Añadir nueva funcionalidad').
Sube tus cambios (git push origin feature/nueva-funcionalidad).
Abre un Pull Request.
Licencia

Este proyecto está licenciado bajo la Licencia MIT - mira el archivo LICENSE para más detalles.

## Contacto

Si tienes alguna pregunta o sugerencia, no dudes en contactarme a través de victormedina2157@gmail.com o abrir un issue en GitHub.

¡Gracias por visitar mi proyecto!




