// old version of CARTMet4Pres.sc

import ScMaBoSS._

import java.io.{File, PrintWriter} // for writing file
val listServerPorts = (20001 to 20010).toList // 10 MaBoSS servers

val CARTCfg = CfgMbss.fromFile("CART_gs.cfg", BndMbss.fromFile("CART_gs.bnd"))
// do not consider nodes representing cell type.
val nodeMut = CARTCfg.extNodeList.toSet.diff(Set("TUM", "CART", "Division", "Death")).toList

// Construct mutable model
val mutCARTCfg = CARTCfg.mutatedCfg(nodeMut, 10000) // maxRate forces the mutation state, no matter the initial condition
mutCARTCfg.writeCfgToFile("mutCART_gs.cfg")
mutCARTCfg.bndMbss.writeToFile("mutCART_gs.bnd")

val varMut = nodeMut.map("$High_" + _) ::: nodeMut.map("$Low_" + _)
// associate server port number with mutation
val varMutPort = varMut.zip(List.fill(varMut.length / listServerPorts.length + 1)(listServerPorts).flatten)
// mutations 1 -> 20
// ".par" produce a parallel vector. If ".map" is used, it is parallel applied
// lazy is necessary for using scala REPL (a kind of bug), therefore testPar20 is not evaluated
lazy val testPar20 = varMutPort.take(20).par.map(varPort => {
  print(varPort._1)
  val upModel = new UPMaBoSS("CART_gs.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
    "localhost", varPort._2, hexUP = true, verbose = false)
    val upRes = upModel.runLight(48)
  (varPort._1, upRes.stateTrajectory(new NetState(Map("TUM" -> true)), normWithSize = true).last._2)
})

// ".toList" force the evaluation of testPar20
val testList20 = testPar20.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")

// write the table in a file
val pw = new PrintWriter(new File("Mut20TumSize.csv"))
pw.write(testList20)
pw.close()
// mutations 21 -> 40
lazy val testPar40 = varMutPort.drop(20).take(20).par.map(varPort => {
  print(varPort._1)
  val upModel = new UPMaBoSS("CART_gs.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
    "localhost", varPort._2, hexUP = true, verbose = false)
  val upRes = upModel.runLight(48)
  (varPort._1, upRes.stateTrajectory(new NetState(Map("TUM" -> true)), normWithSize = true).last._2)
})
val testList40 = testPar40.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")
val pw = new PrintWriter(new File("Mut40TumSize.csv"))
pw.write(testList40)
pw.close()
// mutations 41 -> 60
lazy val testPar60 = varMutPort.drop(40).take(20).par.map(varPort => {
  print(varPort._1)
  val upModel = new UPMaBoSS("CART_gs.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
    "localhost", varPort._2, hexUP = true, verbose = false)
  val upRes = upModel.runLight(48)
  (varPort._1, upRes.stateTrajectory(new NetState(Map("TUM" -> true)), normWithSize = true).last._2)
})
val testList60 = testPar60.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")
val pw = new PrintWriter(new File("Mut60TumSize.csv"))
pw.write(testList60)
pw.close()
// mutation 61 -> 80
lazy val testPar80 = varMutPort.drop(60).take(20).par.map(varPort => {
  print(varPort._1)
  val upModel = new UPMaBoSS("CART_gs.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
    "localhost", varPort._2, hexUP = true, verbose = false)
  val upRes = upModel.runLight(48)
  (varPort._1, upRes.stateTrajectory(new NetState(Map("TUM" -> true)), normWithSize = true).last._2)
})
val testList80 = testPar80.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")

val pw = new PrintWriter(new File("Mut80TumSize.csv"))
pw.write(testList80)
pw.close()
// mutations 81 -> 100
lazy val testPar100 = varMutPort.drop(80).take(20).par.map(varPort => {
  print(varPort._1)
  val upModel = new UPMaBoSS("CART_gs.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
    "localhost", varPort._2, hexUP = true, verbose = false)
  val upRes = upModel.runLight(48)
  (varPort._1, upRes.stateTrajectory(new NetState(Map("TUM" -> true)), normWithSize = true).last._2)
})
val testList100 = testPar100.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")
val pw = new PrintWriter(new File("Mut100TumSize.csv"))
pw.write(testList100)
pw.close()
// mutations 101 -> ...
lazy val testPar108 = varMutPort.drop(100).par.map(varPort => {
  print(varPort._1)
  val upModel = new UPMaBoSS("CART_gs.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
    "localhost", varPort._2, hexUP = true, verbose = false)
  val upRes = upModel.runLight(48)
  (varPort._1, upRes.stateTrajectory(new NetState(Map("TUM" -> true)), normWithSize = true).last._2)
})
val testList108 = testPar108.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")
// add wild type
val upModel = new UPMaBoSS("CART_gs.upp", mutCARTCfg.update(Map("thread_count" -> "1")),
  "localhost", listServerPorts.head, hexUP = true, verbose = false)
val upRes = upModel.runLight(48)
upRes.stateTrajectory(new NetState(Map("TUM" -> true)), normWithSize = true).last._2)

val pw = new PrintWriter(new File("Mut108TumSize.csv"))
pw.write(testList108+ "\n" + "wt" + "\t" +
  upRes.stateTrajectory(new NetState(Map("TUM" -> true)), normWithSize = true).last._2.toString )
pw.close()
//
//
// Tests
import ScMaBoSS._

import java.io.{File, PrintWriter}
val listServerPorts = (20001 to 20010).toList
val CARTCfg = CfgMbss.fromFile("CART_gs.cfg", BndMbss.fromFile("CART_gs.bnd"))

val nodeMut = CARTCfg.extNodeList.toSet.diff(Set("TUM", "CART", "Division", "Death")).toList

val mutCARTCfg = CARTCfg.mutatedCfg(nodeMut, 10000)
mutCARTCfg.writeCfgToFile("mutCART_gs.cfg")
mutCARTCfg.bndMbss.writeToFile("mutCART_gs.bnd")

val varMut = nodeMut.map("$High_" + _) ::: nodeMut.map("$Low_" + _)

val varMutPort = varMut.zip(List.fill(varMut.length / listServerPorts.length + 1)(listServerPorts).flatten)

lazy val test4UPParServer = varMutPort.take(10).par.map(varPort => {
  print(varPort._1)
  val upModel = new UPMaBoSS("CART_gs.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
    "localhost", varPort._2, hexUP = true, verbose = false)
  val upRes = upModel.runLight(48)
  (varPort._1, upRes.stateTrajectory(new NetState(Map("TUM" -> true)), normWithSize = true).last._2)
})


