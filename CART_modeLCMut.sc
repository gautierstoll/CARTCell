import ScMaBoSS._

import java.io.{File, PrintWriter}
import scala.collection.parallel.immutable.ParSeq // for writing file
val listServerPorts = (20001 to 20004).toList // 4 MaBoSS servers

val CARTCfg = CfgMbss.fromFile("CART_LC_gs.cfg", BndMbss.fromFile("CART_LC_gs.bnd"))
// do not consider nodes representing cell type.
val nodeMut = CARTCfg.extNodeList.toSet.diff(Set("TUM", "CART", "Division", "Death")).toList

val mutCARTCfg = CARTCfg.mutatedCfg(nodeMut, 10000) // maxRate forces the mutation state, no matter the initial condition
mutCARTCfg.writeCfgToFile("mutCART_LC_gs.cfg")
mutCARTCfg.bndMbss.writeToFile("mutCART_LC_gs.bnd")

val varMut = nodeMut.map("$High_" + _) ::: nodeMut.map("$Low_" + _)
// associate server port number with mutation
val varMutPort = varMut.zip(List.fill(varMut.length / listServerPorts.length + 1)(listServerPorts).flatten)

// mutations 1 -> 24
// ".par" produce a parallel vector. If ".map" is used, it is parallel applied
// lazy is necessary for using scala REPL (a kind of bug), therefore testPar20 is not evaluated
lazy val testPar24: ParSeq[(String, Double)]= varMutPort.take(24).par.map(varPort => {
    print(varPort._1)
    val upModel = new UPMaBoSS("CART_LC_gs.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
        "localhost", varPort._2, hexUP = true, verbose = false)
    val upRes = upModel.runLight(48)
    (varPort._1, upRes.stateTrajectory(new NetState(Map("TUM" -> true)), normWithSize = true).last._2)
})

// ".toList" force the evaluation of testPar24
val testList24: String = testPar24.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")

// write the table in a file
val pw = new PrintWriter(new File("Mut24_modelLCTumSize.csv"))
pw.write(testList24)
pw.close()
