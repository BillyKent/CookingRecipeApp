# Cooking Recipe App

Aplicación simple de recetas de cocina desarrollada como reto técnico para aplicar al cargo de desarrollador móvil.

## Descripción general

La aplicación permite seleccionar recetas de cocina y visualizar una descripción de la receta, sus ingredientes y su lugar de procedencia. La aplicación consta de tres vistas:

1. Ingreso del criterio de búsqueda y visualización de resultados
2. Visualización del detalle de una receta: ingredientes, descripción, tiempo de cocción, etc.
3. Visualización en el mapa del lugar de procedencia de la receta y datos complementarios.

La versión mínima soportada es Android 7.0 con lo cual se cubre el 96% del total de dispositivos que usan este sistema operativo.

## Arquitectura
La aplicación se desarrolló siguiendo los lineamientos de Clean Architecture y el patrón MVVM. La aplicación se distribuye en tres módulos de la siguiente manera:

### domain
Definición de alto nivel de los repositorios y de los objetos modelo a usar. Estas definiciones son independientes de la fuente de datos. También contiene la lógica de negocio en casos de uso.

### data
Implementación de los repositorios de acuerdo a la fuente de datos. Incluye la definición de objetos DTO (*data transfer object*, por sus siglas en inglés) usados para la deserialización de la respuesta de la API REST, así como métodos para la conversión de estos objetos a los objetos modelo de la capa de dominio.

### app
Es el módulo principal de la app, contiene los componentes de la vista (actividades, fragmentos, etc.), *clases utilitarias* para la lógica de presentación y *view models* para administrar el estado de la vista y eventos.


## Librerías
La aplicación usa varias libreras de Android y de terceros con el fin de simplificar el desarrollo. En todos los casos se trata de librerías ampliamente usadas por la comunidad.

* **Gson:** deserialización de los resultados de la API.
* **Retrofit:** comunicación con API web.
* **OkHttp:** cliente para la comunicación con API web, permite configurar interceptores para realizar una traza de las peticiones.
* **Dagger Hilt:** se encarga de la gestión e inyección de las dependencias necesarias para todos los módulos.
* **Architecture components - View Model y Live Data:** para almacenar y emitir el estado a la vista, también se usan para recepcionar los eventos provenientes de la misma.
* **Material:** librería de google con la implementación de los componentes de Material Design, en este caso estoy usando la especificación de Material Design 3
* **Picasso:** librería para cargar imágenes de internet mediante la url.
* **Android-KTX:** conjunto de librerías con funciones de extensión de kotlin útiles para simplificar el uso de librerías legacy de android.
* **Maps-API:** librería para mostrar mostrar y manipular mapas en la aplicación.
* **Navigation-component:** para manejar la navegación entre pantallas.
* **Facebook-Shimmer:** librería de facebook para representar estados de carga.

## Manejo de errores

* **Dispositivo no conectado a la red**, se valida que el dispositivo se encuentre conectado a una red wifi o datos.
* **Error de servidor**, se valida que los datos obtenidos del servidor sean correctos.
* **Error desconocido**, en caso ocurra algún error al momento de obtener las recetas, se maneja como un caso de error más.

El manejo de errores se da en la pantalla principal y permiten el reintento.

## Recursos externos.

* Inspiración del diseño: [Behance](https://www.behance.net/gallery/166732789/Food-Recipe-Mobile-App?tracking_source=search_projects%7Crecipe+app)
* Inspiración del diseño: [Dribble](https://dribbble.com/shots/17501406-Cooking-App)
* Íconos e ilustraciones: [Flaticon](https://www.flaticon.com/packs/food-109?word=ingredients)
* Mocks de API: [mockable.io](https://www.mockable.io/)

## Capturas
<img src="https://github.com/BillyKent/CookingRecipeApp/blob/master/screenshots/main.png" alt="drawing" width="300"/>|
<img src="https://github.com/BillyKent/CookingRecipeApp/blob/master/screenshots/detail.png" alt="drawing" width="300"/>|
<img src="https://github.com/BillyKent/CookingRecipeApp/blob/master/screenshots/map.png" alt="drawing" width="300"/>|
<img src="https://github.com/BillyKent/CookingRecipeApp/blob/master/screenshots/map_detail.png" alt="drawing" width="300"/>|
<img src="https://github.com/BillyKent/CookingRecipeApp/blob/master/screenshots/map_confirmation.png" alt="drawing" width="300"/>|

## Descarga la aplicación
Descarga el apk debug [aquí](https://github.com/BillyKent/CookingRecipeApp/blob/apk/CookingRecipeApkDebug.apk)
