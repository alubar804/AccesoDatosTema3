package ejercicios

import java.io.*
import java.util.*


fun main(args: Array<String>){
    val f = DataInputStream(FileInputStream("Rutes.dat"))
    val g = ObjectOutputStream(FileOutputStream("Rutes.obj"))
    var lista = mutableListOf<PuntGeo>()
    var ruta = Ruta("",0,0,lista)
    //TODO en la segunda vuelta explota
    while (f.available() > 0) {
        ruta =Ruta (f.readUTF(),
            f.readInt(),
            f.readInt(),
            lista)
        val punts = f.readInt()
        for (i in 0..punts){
            var name = f.readUTF()
            var cooord= Coordenades(f.readDouble(),f.readDouble())
            var punticoGeo=PuntGeo(name,cooord)
            ruta.addPunt(punticoGeo)
        }
        ruta.mostrarRuta()
        g.writeObject(ruta)
    }
    f.close()
    g.close()
}
