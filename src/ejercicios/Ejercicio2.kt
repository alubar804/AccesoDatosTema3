package ejercicios

import java.io.DataInputStream
import java.io.FileInputStream
import java.io.Serializable



class Coordenades  ( var latitud :Double,var longitud :Double):Serializable{
    companion object {
        private const val serialVersionUID: Long = 1
    }

}

class PuntGeo(var nom: String,var coord :Coordenades):Serializable{
    companion object {
        private const val serialVersionUID: Long = 1
    }


}
class Ruta (var nom: String, var desnivell: Int, var desnivellAcumulat: Int, var llistaDePunts: MutableList<PuntGeo>): Serializable {
    companion object {
        private const val serialVersionUID: Long = 1
    }

    fun addPunt(p: PuntGeo) {
        llistaDePunts.add(p)
    }

    fun getPunt(i: Int): PuntGeo {
        return llistaDePunts.get(i)
    }

    fun getPuntNom(i: Int): String {
        return llistaDePunts.get(i).nom
    }

    fun getPuntLatitud(i: Int): Double {
        return llistaDePunts.get(i).coord.latitud
    }

    fun getPuntLongitud(i: Int): Double {
        return llistaDePunts.get(i).coord.longitud
    }

    fun size(): Int {
        return llistaDePunts.size
    }

    fun mostrarRuta() {


            println("Ruta: $nom" )
            println("Desnivell: $desnivell")
            println("Desnivell acumulat: $desnivellAcumulat")
            val punts = size()
            println("Te $punts punts")
            for (i in 0..punts-1){
                println("Punt ${(i+1)}: ${getPuntNom(i)} (${getPuntLatitud(i)} ${getPuntLongitud(i)})")
            }
            println("")
        }


    }
//fun main(args: Array<String>){
//    val f = DataInputStream(FileInputStream("Rutes.dat"))
//    val g = ObjectOutputStream(FileOutputStream("Rutes.obj"))
//
//    while (f.available() > 0) {
//        var lista = mutableListOf<PuntGeo>()
//        var nameRuta =f.readUTF()
//        var desnivellRuta=f.readInt()
//        var desnivellAcoRuta=f.readInt()
//        val punts = f.readInt()
//        for (i in 0..punts){
//            var name = f.readUTF()
//            var cooord= Coordenades(f.readDouble(),f.readDouble())
//            var punticoGeo=PuntGeo(name,cooord)
//            lista.add(punticoGeo)
//        }
//        var ruta =Ruta(nameRuta,desnivellRuta,desnivellAcoRuta,lista)
//        ruta.mostrarRuta()
//        g.writeObject(ruta)
//    }
//    f.close()
//    g.close()
//}
