import ScMaBoSS._
import java.io.{File, PrintWriter} // for writing file
val TableDegPD1 = 1.to(10).toList.map(_.toDouble/20+.25).map(DegPD1 => {

  val upCART: UPMaBoSS =
    new UPMaBoSS("Models/CART.upp",
      CfgMbss.fromFile("Models/CART.cfg",
        BndMbss.fromFile("Models/CART.bnd")).update(Map("$DegPD1" -> DegPD1.toString)),
      "localhost", 20001, hexUP = true, verbose = true)
  val upResCART: UPMbssOutLight = upCART.runLight(10)
  val PD1Traj = upResCART.nodeTrajectory("PD1")
  (DegPD1,PD1Traj.tail.head._2 / PD1Traj.last._2)
})

val pw = new PrintWriter(new File("DegPD14Pres.csv"))
pw.write(TableDegPD1.map(x => x._1.toString + "\t" + x._2).mkString("\n"))
pw.close()



