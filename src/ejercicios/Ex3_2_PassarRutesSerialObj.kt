package ejercicios

import java.io.*
import java.util.*


fun main(args: Array<String>){
    val f = DataInputStream(FileInputStream("Rutes.dat"))
    val g = ObjectOutputStream(FileOutputStream("Rutes.obj"))
    while (f.available() > 0) {
        var lista = mutableListOf<PuntGeo>()
        var ruta =Ruta(f.readUTF(),f.readInt(),f.readInt(),lista)

        val punts = f.readInt()
        for (i in 1..punts){
            ruta.llistaDePunts.get(i).nom=f.readUTF()
            var cooord= Coordenades(f.readDouble(),f.readDouble())
            ruta.llistaDePunts.get(i).coord=cooord
        }
        ruta.mostrarRuta()
        g.writeObject(ruta)
    }
    f.close()
}
