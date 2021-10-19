package ejercicios
import org.w3c.dom.Element
import java.awt.*
import javax.swing.*
import javax.xml.parsers.DocumentBuilderFactory


class Finestra : JFrame() {

    init {

        var doc =  DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("Ruta.xml")
        val arrel = doc.getDocumentElement()  // apuntarà a l'element arrel
        val llista = arrel.getElementsByTagName("ruta")

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("Punts d'una ruta")
        setSize(400, 300)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout())
        val panell2 = JPanel(BorderLayout())
        add(panell1,BorderLayout.NORTH)
        add(panell2,BorderLayout.CENTER)

        val llistaRutes = arrayListOf<String>()

        for (i in 0 until llista.getLength()){
            val el = llista.item(i) as Element
            var nombreRuta =el.getElementsByTagName("nom").item(0).getChildNodes().item(0).getNodeValue()
            llistaRutes.add(nombreRuta)
        }
        val combo = JComboBox(llistaRutes.toArray())
        panell1.add(combo)

        panell2.add(JLabel("Llista de punts de la ruta:"),BorderLayout.NORTH)
        val area = JTextArea()
        panell2.add(area)

        combo.addActionListener{
            // accions quan s'ha seleccionat un element del combobox,
            // i que han de consistir en omplir el JTextArea
            //TODO: esto peta, no debug que rompe el pc
            val seleccion = combo.getSelectedIndex()
            val ruta = llista.item(seleccion) as Element
            val punt = ruta.getElementsByTagName("punt")
            var cadena = ""
            for (i in 0..punt.length-1) {
                val el = punt.item(i) as Element
                val nom = el.getElementsByTagName("nom").item(0).getChildNodes().item(0).nodeValue
                val latitud = el.getElementsByTagName("latitud").item(0).getChildNodes().item(0).nodeValue
                val longitud = el.getElementsByTagName("longitud").item(0).getChildNodes().item(0).nodeValue
                cadena += "$nom ($latitud $longitud)\n"
            }
            area.text = cadena

        }
    }
}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        Finestra().isVisible = true
    }
}

