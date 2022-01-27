package com.example.mockitotest

class Persona (val nombre: String) {

    var hermano : Persona? = null

    fun miNombreEnFrase() : String {
        return "Me llamo $nombre"
    }

    fun tengoHermano() : Boolean {
        return hermano != null
    }

    fun decirNombreHermano() : String {
        return hermano?.nombre.orEmpty()
    }

}
