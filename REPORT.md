# Lab 2 Web Server -- Project Report

## What I specified

Customizing the whitelabel error page. Requires generating an html in src/main/resources/templates with a marker that the test checks for.

Add /time. Being able to return the current time as JSON. 

Enable HTTP/2 and TLS. Creating a self-signed certificate to be able to use Https.

## What I changed

Added src/main/resources/templates/error.html and generated its contents so that it fulfills the test on the guide. Built the server with `./gradlew build` and run it with `./gradlew bootRun` and opened the serevr in my browser to check that it renders my html instead of the default one.

Added another line in the error.html so that I could test /time

Edited the test unknownPathRendersErrorHtml so that it would still work after the HTPP2 and TLS changes, since it didn't trust the certificate.

## Technical decisions

Adding the time to the html to test it.

## How I verified

Built the web to see any changes made and used curl to make sure it connected propely and that /time worked.

`curl --http2 -k -i https://127.0.0.1:8443/time` should output a JSON with the current date.

## AI disclosure

- **Tools / skills:** Github copilot (GPT-6 Luna)
- **Purpose:** Generating HTML and helping with debugging.
- **Representative prompts:** see below
- **Affected files/sections:** src/main/resources/templates/error.html
- **Validation steps:** Reviewing code and running the application to see the changes.

### Representative prompts
Ask:
Rellena error.htm de forma que cumpla el test unknownPathRendersErrorHtml

Answer (GPT-6 Luna):
Voy a revisar la plantilla vacía y la configuración de Spring que controla errores 404; la hipótesis es que Spring buscará templates/error.html y el test solo necesita encontrar ahí el marcador literal your marker. Luego ejecutaré específicamente ese test para comprobarlo.
La guía confirma que Spring Boot renderiza esa plantilla para peticiones HTML sin ruta y que status/path están disponibles en el modelo de error. Voy a completar error.html con una página sencilla que muestre el 404, la ruta y el texto esperado por el test.