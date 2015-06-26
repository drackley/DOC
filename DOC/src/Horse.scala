import Personality.Personality

class Horse(inStart:Int, c:Int, o:Int, comp:Int, t:Int, sp:Int, stamina:Double, speed:Int, sharp:Int, per:Personality, na:String) { thisHorse =>
  var start: Int = inStart
  var corner: Int = c
  var out_of_the_box: Int = o
  var competing: Int = comp
  var tenacious: Int = t
  var spurt: Int = sp

  var personality: Personality = per
  var name: String = na

  var stamina_internal: Double = stamina
  var speed_internal: Int = speed
  var sharp_internal: Int = sharp

  def tick(ctx:HorseTickContext): ActionProposal = {

    new ActionProposal {
      override def horsesAffected: List[Horse] = List(thisHorse)
      override def affectHorse(startState:HorseState): HorseState =
        HorseState(startState.horse,0,startState.postion + thisHorse.speed,{if((startState.stamina - .1) > 0)
        startState.stamina - .1
      else
        .01})
    }
  }

  def move: Double = {
    if((stamina_internal - .1) > 0)
      stamina_internal -= .1
    else
      stamina_internal = .01
    speed_internal * stamina_internal
  }
}

object Personality extends Enumeration {
  type Personality = Value
  val Rough, Imposing, Honest, Coward, Sloppy = Value
}
