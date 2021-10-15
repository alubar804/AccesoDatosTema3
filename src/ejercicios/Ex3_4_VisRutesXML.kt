package ejercicios
import javax.swing.*
import java.awt.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.FileInputStream
import java.io.ObjectInputStream
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


//         sentències per a omplir l'ArrayList anterior amb el nom de les rutes
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

        }
    }
}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        Finestra().isVisible = true
    }
}

