package org.harptabber.parsing

import org.harptabber.translation.Note
import org.harptabber.translation.blow
import org.harptabber.translation.draw
import java.lang.IllegalArgumentException

class DiatonicParser
{
    private val whiteSpace = "\\s+"

    private val commentMarker = '#'

    private val drawNote = "^\\([1-9]0?\\)$".toRegex()

    private val holeNumberPattern = "([1-9]0?)".toRegex()

    fun parse(tabulature: String): Array<Note>
    {
        return when (true) {
            isCommented(tabulature) -> emptyArray()
            else -> tabulature
                .split(whiteSpace.toRegex())
                .map { parseSingleToken(it) }
                .toTypedArray()
        }
    }

    private fun isCommented(tabulature: String) = tabulature[0] == commentMarker

    private fun parseSingleToken(token: String): Note
    {
        return when {
            drawNote.matches(token) -> parseDrawNote(token)
            else -> blow(Integer.parseInt(token))
        }
    }

    private fun parseDrawNote(token: String): Note
    {
        return holeNumberPattern.find(token)
            .let { searchResult -> searchResult?.value}
            .let { holeNumberString -> Integer.parseInt(holeNumberString) }
            .let { holeNumber -> draw(holeNumber) }
    }
}