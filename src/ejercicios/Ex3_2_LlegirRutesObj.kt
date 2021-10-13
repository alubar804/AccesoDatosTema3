package ejercicios

import ejemplos.Empleat
import java.io.*
import java.util.*
import ejercicios.Ruta

fun main(args: Array<String>){
    val f = ObjectInputStream(FileInputStream("Rutes.obj"))
    try {
            while (true) {
                val e = f.readObject() as Ruta
                e.mostrarRuta()
            }

    } catch (eof: EOFException) {
        f.close()
    }
}
