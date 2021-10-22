package ejercicios


import javax.swing.*
import java.awt.*

import org.json.*
import org.json.JSONTokener
import java.io.FileReader

class FinestraJSON : JFrame() {

    init {
        var llistaRutes: ArrayList<Ruta>
        // sentències per a omplir llistaRutes
        val f = FileReader("Rutes.json")
        val raiz = JSONTokener(f).nextValue() as JSONObject

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("JSON: Punts d'una ruta")
        setSize(400, 300)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout())
        val panell2 = JPanel(BorderLayout())
        add(panell1, BorderLayout.NORTH)
        add(panell2, BorderLayout.CENTER)

        var listaNombres = arrayListOf<String>()
        // sentències per a omplir l'ArrayList anterior amb el nom de les rutes
        for (i in raiz.getJSONArray("rutes")) {
            val e = i as JSONObject
            listaNombres.add(e.get("nom") as String)
        }

        val combo = JComboBox(listaNombres.toArray())
        panell1.add(combo)

        panell2.add(JLabel("Llista de punts de la ruta:"), BorderLayout.NORTH)
        val area = JTextArea()
        panell2.add(area)

        combo.addActionListener {
            // accions quan s'ha seleccionat un element del combobox,
            // i que han de consistir en omplir el JTextArea
            val elarray = raiz.getJSONArray("rutes")
            val ruta = elarray[combo.selectedIndex] as JSONObject
            val punts = ruta.getJSONArray("punts") as JSONArray
            var palabras = ""
            for (i in punts) {
                val e = i as JSONObject
                val nom = e.get("nom")
                val coord = e.getJSONObject("coor")
                val latitud = coord.get("latitud")
                val longitud = coord.get("longitud")
                palabras += "${nom} (${latitud} ${longitud})\n"
            }
            area.text = palabras
        }
    }
}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        FinestraJSON().isVisible = true
    }
}
