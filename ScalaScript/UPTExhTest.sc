import ScMaBoSS._

val upTest: UPMaBoSS =
    new UPMaBoSS("Models/UPTExh.upp",
        CfgMbss.fromFile("Models/UPTExh.cfg",
            BndMbss.fromFile("Models/UPTExh.bnd")), "localhost", 20001, hexUP = true, verbose = true)

val upRes: UPMbssOutLight = upTest.runLight(48)

upRes.plotStateTraj(List(
    new NetState(Map("CART" -> true)),
    new NetState(Map("Tum" -> true)),
    new NetState(Map("TCell" -> true)),
    new NetState(Map("scFv" -> true))
), normWithSize = true,
    filename = "Results/TumCarTExh.pdf")

val upTest: UPMaBoSS =
    new UPMaBoSS("Models/UPTExh.upp",
        CfgMbss.fromFile("Models/UPTExhLowCART.cfg",
            BndMbss.fromFile("Models/UPTExh.bnd")), "localhost", 20001, hexUP = true, verbose = true)

val upRes: UPMbssOutLight = upTest.runLight(48)

upRes.plotStateTraj(List(
    new NetState(Map("CART" -> true)),
    new NetState(Map("Tum" -> true)),
    new NetState(Map("TCell" -> true)),
    new NetState(Map("scFv" -> true))
), normWithSize = true,
    filename = "Results/TumCarTExhLowCART.pdf")

upRes.plotStateTraj(List(
    new NetState(Map("CART" -> true,"scFv" -> true,"IFNg" -> true)),
    new NetState(Map("Tum" -> true))
), normWithSize = true,
    filename = "Results/TumCarTExhLowCART2.pdf")

