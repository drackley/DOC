/**
 * Created by drackley on 6/25/15.
 */
trait ActionProposal {
  def horsesAffected:List[Horse]

  def affectHorse(startState:HorseState): HorseState
}

class MoveProposal extends ActionProposal {
  override def horsesAffected: List[Horse] = ???
  override def affectHorse(horse: HorseState): HorseState = ???
}
