import ScMaBoSS._

import scala.collection.parallel.ParSeq

// Create the MaBoSS model
val CARTCfg: CfgMbss = CfgMbss.fromFile("CART_LC_gs.cfg", BndMbss.fromFile("CART_LC_gs.bnd"))

val listServerPorts: List[Int] = (20001 to 20004).toList  //list of server ports
val probTAPort: IndexedSeq[(Double, Int)] = (0 to 10).map(x => x.toDouble / 10).
        zip(List.fill( 10 / listServerPorts.length + 1)(listServerPorts).flatten) // aggregate server ports with ProbTA

// lazy is necessary in order to create a new parallel collection by running function on elements in a scala REPL (kind of bug)
lazy val tumorSizes: ParSeq[(Double, List[(Double, Double)])] = probTAPort.par.map(propTAPort => { // .par created a parallel collection
    print("Prob TA " + propTAPort._1.toString)
    val upCART = new UPMaBoSS("CART_gs.upp", // create an UPMaBoSS model...
        CARTCfg.update(Map("$TumorAntigeneProp " -> propTAPort._1.toString, // update ProbTA...h
            "sample_count" -> "30000")), // larger sample count
        "localhost", propTAPort._2, hexUP = true, verbose = false)
    (propTAPort._1,upCART.runLight(48).nodeTrajectory("TUM", normWithSize = true)) //collect propTA, time-dependent tumor size
}) // return probTA with its time dependant tumor size

// .toList runs the parallel collection, sort it by ProbTA and keep only time dependant tumor size
val tumorSizesList: List[List[(Double, Double)]] = tumorSizes.toList.sortWith((x, y) => x._1 < y._1).map(x => x._2) //


import java.io._

// Construct the table of tumor sizes and export it in a file
val pw = new PrintWriter(new File("TumSizeSensitPropTA_modelLC.csv"))
pw.write("Time" + "\t" +
        probTAPort.map(x=> x._1).mkString("\t") + "\n" +// header of "$TumorAntigeneProp"
        (0 to 47).
                map(timeStep => { // for each time step...
                    tumorSizesList.head(timeStep)._1.toString + // real time, extracted by ._1
                            "\t" +
                            tumorSizesList.map(l => l(timeStep)._2.toString).mkString("\t") // probability, extracted by ._2
                }).
                mkString("\n"))
pw.close()
