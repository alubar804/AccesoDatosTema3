package ejercicios


import org.json.JSONArray
import org.json.JSONObject
import java.io.EOFException
import java.io.FileInputStream
import java.io.FileWriter

import java.io.ObjectInputStream


fun main() {
    val f = ObjectInputStream(FileInputStream("Rutes.obj"))

    val arrel = JSONObject()
    val rutesArray = JSONArray()
    arrel.put("rutes", rutesArray)
    try {
        while (true) {
            val obj = f.readObject() as Ruta

            val ruta = JSONObject()
            val punts = JSONArray()

            ruta.put("nom", obj.nom)
            ruta.put("desnivell", obj.desnivell)
            ruta.put("desnivellAcumulat", obj.desnivellAcumulat)

            for (i in obj.llistaDePunts) {
                val punt = JSONObject()
                val coor = JSONObject()
                punt.put("nom", i.nom)
                coor.put("latitud", i.coord.latitud)
                coor.put("longitud", i.coord.longitud)
                punt.put("coord", coor)
                punts.put(punt)
            }

            ruta.put("punts", punts)
            rutesArray.put(ruta)
        }
    } catch (eof: EOFException) {
        f.close();
        val e = FileWriter("Rutes.json")
        e.write(arrel.toString(4))
        e.close()
    }

}


