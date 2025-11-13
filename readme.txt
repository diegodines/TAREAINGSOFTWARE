El sistema permite:

Agregar un nuevo usuario

Listar todos los usuarios

Editar un usuario existente

Eliminar un usuario por su ID








Primero para utilizar el CRUD debe dirijirse ala  seccion del repositorio llamada aplication properties,
ahi es donde configurara el proyecto dependiendo del nombre de su base de datos y su localhost, se debe tener creada la base de datos.
Tambien es recomendable iniciar el proyecto con la linea de codigo en spring.jpa.hibernate.ddl-auto=create ya que esto
creara la tabla persona, en caso de utilizar en "update" solo actualizara los datos que ya posee en la tabla. 
Antes de ejecutar el proyecto en el main debe inicializar XAMPP con su respectivo service de Apache y MySQL, ya ejecutado el proyecto el tomcat se iniciara solo.
Para empezar a utilizar el CRUD debe ingresar a su localhost(en este caso esta localhost:8080) donde lo dirigira a la página en donde 
podra añadir persona a la izquierda arriba. Una vez añada una persona con sus respectivos atributos tiene un redirect hacia (http://localhost:8080/listar)
y ahi en esa misma direccion tendra botones de editar y eliminar usuario, el boton eliminar. eliminara la fila completa y editar puede
editar el usuario completo. En otra forma resumida se puede acceder desde el navegador con los siguientes links:

http://localhost:8080/listar → Lista de usuarios

http://localhost:8080/new → Crear usuario

http://localhost:8080/editar/{id} → Editar usuario por su ID

Botón eliminar → Borra usuario por ID

