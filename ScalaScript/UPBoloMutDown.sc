import ScMaBoSS._

import java.io.{File, PrintWriter}
val listServerPorts = (20001 to 20004).toList // 4 MaBoSS servers
val CARTCfg = CfgMbss.fromFile("Models/UPBolour.cfg",
    BndMbss.fromFile("Models/UPBolour.bnd"))

val nodeMut = CARTCfg.extNodeList.toSet.diff(
    Set("Tum","TCell","CART","TA_tum","Death","Prolif")).toList

val mutCARTCfg = CARTCfg.mutatedCfg(nodeMut, 10000)

val varMut = nodeMut.map("$Low_" + _)

val varMutPort = varMut.zip(List.fill(varMut.length / listServerPorts.length + 1)(listServerPorts).flatten)

// Mut 1 to 8
lazy val ResPar8 = varMutPort.take(8).par.map(varPort => {
    print(varPort._1)
    val upModel = new UPMaBoSS("Models/UPBolour.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
        "localhost", varPort._2, hexUP = true, verbose = false)
    val upRes = upModel.runLight(20)
    (varPort._1, upRes.stateTrajectory(new NetState(Map("Tum" -> true)), normWithSize = true).last._2)
})
val ResList8 = ResPar8.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")
val pw = new PrintWriter(new File("Mut8TumSizeBolo.csv"))
pw.write(ResList8)
pw.close()
// Mut 9 to 16
lazy val ResPar16 = varMutPort.drop(8).take(8).par.map(varPort => {
    print(varPort._1)
    val upModel = new UPMaBoSS("Models/UPBolour.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
        "localhost", varPort._2, hexUP = true, verbose = false)
    val upRes = upModel.runLight(20)
    (varPort._1, upRes.stateTrajectory(new NetState(Map("Tum" -> true)), normWithSize = true).last._2)
})
val ResList16 = ResPar16.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")
val pw = new PrintWriter(new File("Mut16TumSizeBolo.csv"))
pw.write(ResList16)
pw.close()
//
// Mut 17 to 24
lazy val ResPar24 = varMutPort.drop(16).take(8).par.map(varPort => {
    print(varPort._1)
    val upModel = new UPMaBoSS("Models/UPBolour.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
        "localhost", varPort._2, hexUP = true, verbose = false)
    val upRes = upModel.runLight(20)
    (varPort._1, upRes.stateTrajectory(new NetState(Map("Tum" -> true)), normWithSize = true).last._2)
})
val ResList24 = ResPar24.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")
val pw = new PrintWriter(new File("Mut24TumSizeBolo.csv"))
pw.write(ResList24)
pw.close()
// Mut 25 to 32
lazy val ResPar32 = varMutPort.drop(24).take(8).par.map(varPort => {
    print(varPort._1)
    val upModel = new UPMaBoSS("Models/UPBolour.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
        "localhost", varPort._2, hexUP = true, verbose = false)
    val upRes = upModel.runLight(20)
    (varPort._1, upRes.stateTrajectory(new NetState(Map("Tum" -> true)), normWithSize = true).last._2)
})
val ResList32 = ResPar32.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")
val pw = new PrintWriter(new File("Mut32TumSizeBolo.csv"))
pw.write(ResList32)
pw.close()
// Mut 33 to 40
lazy val ResPar40 = varMutPort.drop(32).take(8).par.map(varPort => {
    print(varPort._1)
    val upModel = new UPMaBoSS("Models/UPBolour.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
        "localhost", varPort._2, hexUP = true, verbose = false)
    val upRes = upModel.runLight(20)
    (varPort._1, upRes.stateTrajectory(new NetState(Map("Tum" -> true)), normWithSize = true).last._2)
})
val ResList40 = ResPar40.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")
val pw = new PrintWriter(new File("Mut40TumSizeBolo.csv"))
pw.write(ResList40)
pw.close()
// Mut 41 to 48
lazy val ResPar48 = varMutPort.drop(40).take(8).par.map(varPort => {
    print(varPort._1)
    val upModel = new UPMaBoSS("Models/UPBolour.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
        "localhost", varPort._2, hexUP = true, verbose = false)
    val upRes = upModel.runLight(20)
    (varPort._1, upRes.stateTrajectory(new NetState(Map("Tum" -> true)), normWithSize = true).last._2)
})
val ResList48 = ResPar48.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")
val pw = new PrintWriter(new File("Mut48TumSizeBolo.csv"))
pw.write(ResList48)
pw.close()
// Mut 49 to 56
lazy val ResPar56 = varMutPort.drop(48).take(8).par.map(varPort => {
    print(varPort._1)
    val upModel = new UPMaBoSS("Models/UPBolour.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
        "localhost", varPort._2, hexUP = true, verbose = false)
    val upRes = upModel.runLight(20)
    (varPort._1, upRes.stateTrajectory(new NetState(Map("Tum" -> true)), normWithSize = true).last._2)
})
val ResList56 = ResPar56.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")
val pw = new PrintWriter(new File("Mut56TumSizeBolo.csv"))
pw.write(ResList56)
pw.close()
// Mut 57 to 64
lazy val ResPar64 = varMutPort.drop(56).take(8).par.map(varPort => {
    print(varPort._1)
    val upModel = new UPMaBoSS("Models/UPBolour.upp", mutCARTCfg.update(Map(varPort._1 -> "1", "thread_count" -> "1")),
        "localhost", varPort._2, hexUP = true, verbose = false)
    val upRes = upModel.runLight(20)
    (varPort._1, upRes.stateTrajectory(new NetState(Map("Tum" -> true)), normWithSize = true).last._2)
})
val ResList64 = ResPar64.toList.map(x => x._1 + "\t" + x._2.toString).mkString("\n")
// add wild type
val upModel = new UPMaBoSS("Models/CART.upp", mutCARTCfg.update(Map("thread_count" -> "1")),
    "localhost", listServerPorts.head, hexUP = true, verbose = false)
val upRes = upModel.runLight(20)

val pw = new PrintWriter(new File("Mut64TumSizeBolo.csv"))
pw.write(ResList64+ "\n" + "wt" + "\t" +
        upRes.stateTrajectory(new NetState(Map("Tum" -> true)), normWithSize = true).last._2.toString )
pw.close()
