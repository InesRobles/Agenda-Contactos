class Contacto(
    var nombre: String,
    var telefono: String,
    var email: String
) {
    override fun toString(): String {
        return "nombre= $nombre, telefono= $telefono, email= $email"
    }
}