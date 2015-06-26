/**
 * Created by drackley on 6/25/15.
 */
trait Action {
  def horse:Horse
  def affect(horseContext:HorseState):HorseState
}

