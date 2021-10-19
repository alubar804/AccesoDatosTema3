package ejercicios

class EX6 {
}
import javax.swing.*
import java.awt.*
import com.squareup.moshi.Moshi
import exercicis.Ruta
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import org.w3c.dom.Element
import java.io.File
import java.io.FileReader

class FinestraJSON : JFrame() {

    init {
        var llistaRutes: ArrayList<Ruta>
        // sentències per a omplir llistaRutes
        val r_json = FileReader("Rutas.json")
        val arrel = JSONTokener(r_json).nextValue() as JSONObject

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("JSON: Punts d'una ruta")
        setSize(400, 300)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout())
        val panell2 = JPanel(BorderLayout())
        add(panell1, BorderLayout.NORTH)
        add(panell2, BorderLayout.CENTER)

        var nomsLlistaRutes = arrayListOf<String>()
        // sentències per a omplir l'ArrayList anterior amb el nom de les rutes
        for (i in arrel.getJSONArray("rutes")) {
            val jo = i as JSONObject
            nomsLlistaRutes.add(jo.get("nom") as String)
        }

        val combo = JComboBox(nomsLlistaRutes.toArray())
        panell1.add(combo)

        panell2.add(JLabel("Llista de punts de la ruta:"), BorderLayout.NORTH)
        val area = JTextArea()
        panell2.add(area)

        combo.addActionListener {
            // accions quan s'ha seleccionat un element del combobox,
            // i que han de consistir en omplir el JTextArea
            val arr = arrel.getJSONArray("rutes")
            val ruta = arr[combo.selectedIndex] as JSONObject
            val punts = ruta.getJSONArray("punts") as JSONArray
            var acum = ""
            for (i in punts) {
                val a = i as JSONObject
                val nom = a.get("nom")
                val coor = a.getJSONObject("coor")
                val latitud = coor.get("latitud")
                val longitud = coor.get("longitud")
                acum += "${nom} (${latitud} ${longitud})\n"
            }
            area.text = acum
        }
    }
}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        FinestraJSON().isVisible = true
    }
}
import exercicis.Ruta
import org.json.JSONArray
import org.json.JSONObject
import java.io.EOFException
import java.io.FileInputStream
import java.io.FileWriter

import java.io.ObjectInputStream

fun main() {
    val f = ObjectInputStream(FileInputStream("Rutas.obj"))

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
                punt.put("coor", coor)
                punts.put(punt)
            }
            ruta.put("punts", punts)
            rutesArray.put(ruta)
        }
    } catch (eof: EOFException) {
        f.close();
        val e = FileWriter("Rutas.json")
        e.write(arrel.toString(4))
        e.close()
    }

}