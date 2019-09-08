package org.harptabber.translation

import kotlin.math.abs

class Note(
    private val holeNumber: Int,
    private val airDirection: AirDirection
) {
    fun distanceTo(another: Note): Int
    {
        return abs(holeNumber - another.holeNumber)
    }

    override fun equals(other: Any?): Boolean {
        return (other is Note) &&
                other.airDirection == airDirection &&
                other.holeNumber == holeNumber
    }

    override fun hashCode(): Int {
        return holeNumber + when(airDirection) {
            AirDirection.BLOW -> 0
            AirDirection.DRAW -> 1
        }
    }

    override fun toString(): String {
        return when (airDirection) {
            AirDirection.BLOW -> holeNumber.toString()
            AirDirection.DRAW -> "($holeNumber)"
        }
    }
}