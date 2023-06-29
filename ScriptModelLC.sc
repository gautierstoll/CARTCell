import ScMaBoSS._

val upTest: UPMaBoSS =
    new UPMaBoSS("CART_LC_gs.upp",
        CfgMbss.fromFile("CART_LC_gs.cfg",
            BndMbss.fromFile("CART_LC_gs.bnd")), "localhost", 20001, hexUP = true, verbose = true)


val upRes: UPMbssOutLight = upTest.runLight(48)


upRes.plotStateTraj(List(new NetState(Map("TUM" -> true)),
    new NetState(Map("CART" -> true)),
    new NetState(Map("CASP3_tum" -> true)),
    new NetState(Map("Perforin" -> true)),
    new NetState(Map("SCFV" -> true))
), normWithSize = true,
    filename = "TumCarT1_LC.pdf")

