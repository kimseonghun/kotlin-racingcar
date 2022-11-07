package nexstep.mission.racingcar

import nexstep.mission.racingcar.io.Input
import nexstep.mission.racingcar.io.Output
import nexstep.mission.racingcar.io.RacingCarDto
import java.util.Random

private val RANDOM = Random()

class RacingCarController(
    private val input: Input,
    private val output: Output
) {

    fun race() {
        val racingCarNames = input.inputRacingCarNames()
        val racingGame = RacingGame(racingCarNames)
        val round = input.inputRound()
        startRace(round, racingGame)
        val winners = racingGame.winner()
        output.printWinner(winners.map { it.name.value })
    }

    private tailrec fun startRace(round: Int, racingGame: RacingGame): Unit =
        when (true) {
            (round == 0) -> Unit
            else -> {
                racingGame.race { RANDOM.nextInt(10) }
                output.printPositions(racingGame.racingCars.map { RacingCarDto(it.name.value, it.position) })
                startRace(round - 1, racingGame)
            }
        }
}
