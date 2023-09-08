import ScMaBoSS._


val upCART: UPMaBoSS =
  new UPMaBoSS("Models/CART.upp",
    CfgMbss.fromFile("Models/CART.cfg",
      BndMbss.fromFile("Models/CART.bnd")), "localhost", 20001, hexUP = true, verbose = true)

val upResCART: UPMbssOutLight = upCART.runLight(48)

upResCART.writeStateTraj(List(new NetState(Map("TUM" -> true)),
  new NetState(Map("CART" -> true)),
  new NetState(Map("CASP3_tum" -> true)),
  new NetState(Map("Perforin" -> true))),normWithSize = true,
  filename = "ScalaScript/resCART.csv")

//

val upCARTNoTreat: UPMaBoSS =
  new UPMaBoSS("Models/CART.upp",
    CfgMbss.fromFile("Models/CART.cfg",
      BndMbss.fromFile("Models/CART.bnd")).update(Map("$Initial_CART" ->  "0.0")),
    "localhost", 20001, hexUP = true, verbose = true)

val upResCARTNoTreat: UPMbssOutLight = upCARTNoTreat.runLight(48)

upResCARTNoTreat.writeStateTraj(List(new NetState(Map("TUM" -> true)),
  new NetState(Map("CART" -> true)),
  new NetState(Map("CASP3_tum" -> true)),
  new NetState(Map("Perforin" -> true))
),normWithSize = true,
  filename = "ScalaScript/resCARTNoTreat.csv")

