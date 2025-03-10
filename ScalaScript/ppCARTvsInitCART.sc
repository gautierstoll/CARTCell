import ScMaBoSS._

import java.io.{File, PrintWriter} // for writing file

val CARTProp = (1 to 40).toList.map(_.toDouble/10/4).map({ exhaust_rate =>
print(exhaust_rate)
    val upCART: UPMaBoSS =
        new UPMaBoSS("Models/CART.upp",
            CfgMbss.fromFile("Models/CART_exhaust.cfg",
                BndMbss.fromFile("Models/CART_exhaust.bnd")).update(Map("$exhaust_rate" -> exhaust_rate.toString)),
            "localhost", 20000, hexUP = true, verbose = true)
    val upResCART: UPMbssOutLight = upCART.runLight(36 * 24)
    upResCART.nodeTrajectory(node = "CART", normWithSize = true)
})

val CARTPropZip = CARTProp.zip((1 to 24).toList.map(_.toDouble/10/4))


val pw = new PrintWriter(new File("CARTSensitExaaust.csv"))
pw.write(
    CARTPropZip.head._1.map(_._1).mkString("\t") + "\t" + "exhaust_speed" + "\n" +
            CARTPropZip.map(x => x._1.map(_._2).mkString("\t") + "\t" + x._2.toString).mkString("\n")
 )
pw.close()


