package ejercicios

import org.json.JSONObject
import org.json.JSONArray
import java.io.EOFException
import java.io.FileInputStream
import java.io.FileWriter
import java.io.ObjectInputStream

fun main(args: Array<String>) {
    val inp = ObjectInputStream(FileInputStream("Rutes.obj"))
    val f = FileWriter("Rutes.json")
//    var noms = mutableListOf<String>()
//    var desnivells = mutableListOf<Int>()
//    var desnivellAcumulats = mutableListOf<Int>()
//    var  = mutableListOf<PuntGeo>()
//    var  = mutableListOf<PuntGeo>()

    val raiz = JSONArray()
    try {
        val rutas = JSONArray()

        var x = 0
        while (true) {
            val ruta = JSONObject()
            val noms = JSONObject()
            val desnivells = JSONObject()
            val desnivellAcumulats = JSONObject()
            val carac = JSONArray()
            val e = inp.readObject() as Ruta
            noms.put("nom",e.nom)
            desnivells.put("desnivell",e.desnivell)
            desnivellAcumulats.put("desnivell acumulat",e.desnivellAcumulat)
            carac.put(noms)
            carac.put(desnivells)
            carac.put(desnivellAcumulats)
            for (i in 0 until e.size()){
                val punt = JSONObject()
                punt.put("nom",e.getPuntNom(i).toString())
                val coord = JSONObject()
                coord.put("latitud",e.getPuntLatitud(i).toString())
                coord.put("longitud",e.getPuntLongitud(i).toString())
                punt.put("coord",coord)
                carac.put(punt)

            }
            ruta.put("ruta",carac)
            rutas.put(ruta)
            raiz.put(rutas)
            f.write(raiz.toString(4))
            x++

        }

    } catch (eof: EOFException) {
        inp.close()
    }



    inp.close()
    f.close()
}

