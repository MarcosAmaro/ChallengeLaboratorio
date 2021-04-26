### Features

- El repositorio contiene los dos proyectos (Frontend con React y Backend con Spring boot) para poder importarse en Eclipse.

- Spring boot está configurado para correr en el puerto 8090

- Se agregó swagger para poder probar los endpoints: http://localhost:8090/swagger-ui.html
 
 ![](https://imgur.com/RlSWPDQ.png)
 
 
- Para el punto de los productos, no se hizo frontend y se prueba directamente en swagger (ya tiene precargados algunos ejemplos), solo basta con seleccionar la operación y realizar la llamada completando los campos necesarios

![](https://imgur.com/peohwOr.png)

- El punto de medicamentos, tiene frontend con React, está separado en tres pestañas:
1. Medicina - Alta: acá se da de alta un nuevo medicamento:

![](https://imgur.com/FMIJL7G.png)

2. Medicina - Búsqueda: en esta pantalla se integró la búsqueda por nombre y también por tipo, ambos filtros como opcionales:

![](https://imgur.com/HDGgcoZ.png)

3. Tipo de medicina: en esta pantalla se integraron el alta de un medicamento y la actualización(campo de baja lógica). Para la actualización, primero se busca el tipo(por nombre y/o tipo), y en los resultados de la tabla se tildan o destildan los registros para dar de baja/alta. Luego con el botón Guardar cambios(aparece cuando se haya modificado algún registro) se llama a la rest api para que actualice los cambios:

![](https://imgur.com/ZKLRr6R.png)
