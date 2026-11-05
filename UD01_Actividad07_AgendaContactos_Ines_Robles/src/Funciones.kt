import java.util.Scanner
class Funciones {
    private val scan = Scanner(System.`in`)
    val datos = mutableListOf<Contacto>()
    fun iniciar() {
        var opcion: Int
        do {
            mostrarMenu()
            // Depuración
            if (!scan.hasNextInt()) {
                println("Opción no válida, introduce un número.")
                scan.next()
                opcion = 0
                continue
            }
            opcion = scan.nextInt()
            when (opcion) {
                1 -> {
                    anadirContacto()
                }

                2 -> {
                    listarContacto()
                }

                3 -> {
                    buscarContacto()
                }
                4 -> {
                    eliminarContacto()
                }

                0 -> {
                    println("Saliendo del programa. ¡Hasta pronto!")
                }

                else -> {
                    println("Opción no válida")
                }
            }
        } while (opcion != 4)
    }

    //Fase 3. Menú principal
    private fun mostrarMenu() {
        println("\n===== AGENDA DE CONTACTOS =====")
        println("1. Añadir contacto")
        println("2. Listar contactos")
        println("3. Buscar contacto")
        println("4. Eliminar contacto")
        println("0. Salir")
        print("Seleccione una opción: ")
    }

    //Fase 4. Añadir contacto
    private fun anadirContacto() {
        var valido = false
        var validoDos = false
        println("Introduce el nombre: ")
        var nombre = scan.next()
        var telefono = ""
        while (!valido) {
            println("Introduce el teléfono: ")
            telefono = scan.next()
            if (!telefono.matches(Regex("^[6-9][0-9]{8}$"))) {
                //IA para la validación
                println("Teléfono no válido, introduce uno válido.")
                continue
            }
            valido = true
        }
        var email = ""
        while (!validoDos) {
            println("Introduce el correo electrónico: ")
            email = scan.next()
            if (!email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"))) {
                //IA para la validación
                println("Email no válido, introduce uno válido.")
                continue
            }
            validoDos = true
        }
        datos.add(Contacto(nombre, telefono, email))
        println("Contacto añadido con exito: ${datos.last()}")
    }

    //Fase 5. Listar contactos
    private fun listarContacto() {
        if (datos.isEmpty()) {
            println("La agenda no contiene contactos.")
            return
        }
        val ordenados =datos.sortedBy {
            it.nombre.lowercase()
        }
        ordenados.forEachIndexed {
            indice, contacto -> println("${indice + 1}. ${contacto.nombre} - ${contacto.telefono} - ${contacto.email}")
        }
    }

    //Fase 6. Buscar contactos
    private fun buscarContacto() {
        println("Introduce el nombre a buscar: ")
        val texto =scan.next().trim()
        val encontrados = datos.filter {
            it.nombre.contains(texto, ignoreCase = true)
        }
        if (encontrados.isEmpty()) {
            println("No se han encontrado contactos con ese nombre.")
            return
        }
        encontrados.forEachIndexed { indice, contacto ->
            println("${indice + 1}. ${contacto.nombre} - ${contacto.telefono} - ${contacto.email}")
        }
    }

    // Lista ordenada, usada por listar y eliminar para que las posiciones coincidan
    private fun ordenados(): List<Contacto> = datos.sortedBy {
        it.nombre.lowercase()
    }

    //Fase 7. Eliminar contacto
    private fun eliminarContacto() {
        print("Introduce el número de posición: ")
        val posicion = scan.next().trim().toIntOrNull()
        val lista = ordenados()
        if (posicion == null || posicion !in 1..lista.size) {
            println("Error: no existe ningún contacto en esa posición.")
            return
        }
        datos.remove(lista[posicion - 1])
        println("Contacto eliminado correctamente.")
    }
}