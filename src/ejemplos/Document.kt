package ejemplos



import javax.xml.parsers.DocumentBuilderFactory
import java.io.File


val dbFactory = DocumentBuilderFactory.newInstance()

val dBuilder = dbFactory.newDocumentBuilder()

val doc = dBuilder.parse(File("cotxes.xml"))

