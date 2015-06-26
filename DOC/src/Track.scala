import scala.util.Random

class Track(trackLength: Int, horses: List[Horse]) {

  var tickState:List[HorseState] = horses.map{horse => new HorseState(horse, 0, 0, 1.0)}

  def tick: Unit = {
    val contexts = horses.map{horse =>(horse,computeContext(horse,horses))}.toMap//context for each horse

    val actionProposals =
      Random.shuffle(horses)                      //randomize horse precedence
      .map{horse => horse.tick(contexts(horse))}  //collect proposed actions

    val actions = resolve(actionProposals)        //resolve proposals into actions

    tickState = tickState map {tkState => applyAllActions(tkState,actions.filter(_.horse == tkState.horse))}//apply actions
  }

  def applyAllActions(thisState:HorseState, actionsToTake:List[Action]): HorseState = {
      actionsToTake.foldRight(thisState)((ac,state) => ac.affect(state))
  }

  def computeContext(horse:Horse,horses:List[Horse]): HorseTickContext = {
    new HorseTickContext
  }

  def resolve(actProps:List[ActionProposal]):List[Action] = {
    //determine which to do, and cascading actions
    actProps.flatMap(act => act.horsesAffected.map(acthorse => new Action(){
      override def horse: Horse = acthorse
      override def affect(horseContext: HorseState): HorseState = {act.affectHorse(horseContext) }
    }))
  }

  def raceOver: Boolean = {
    tickState.count(_.postion > trackLength) > 0
  }

  def printState(tickNum:Int): Unit = {
    tickState foreach {hts => println(s"($tickNum)${hts.horse.name} @ ${hts.postion} | ${hts.speed}m/s [${hts.stamina}]")}
  }
}