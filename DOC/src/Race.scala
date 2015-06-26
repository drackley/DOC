object Race {
  def main(args: Array[String]) {
    val TriXc = new Horse(500,500,500,500,500,500,1.0,500,500,Personality.Rough, "TriXc")
    val FreeStandingUnit = new Horse(100,100,100,100,100,100,1.0,100,100,Personality.Sloppy, "FSU")

    val KentuckyDerby = new Track(5000, List(TriXc, FreeStandingUnit))

    var racing = true

  var ticks:Int = 0;
    println("Welcome To the Kentucky Derby!")
    while(!KentuckyDerby.raceOver) {
      KentuckyDerby.tick
      KentuckyDerby.printState(ticks)
      ticks+=1
    }
    println("The derby is finished!")
    KentuckyDerby.printState(ticks)
  }
}