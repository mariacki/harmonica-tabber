package org.harptabber.parsing

import org.harptabber.translation.Note
import org.harptabber.translation.blow
import org.harptabber.translation.draw

class TablatureParser
{
    private val whiteSpace = "\\s+"

    private val commentMarker = '#'

    private val drawNote = "^\\([1-9]0?\\)$".toRegex()

    private val holeNumberPattern = "([1-9]0?)".toRegex()

    fun parseLine(tablature: String): Array<Note> = when (true) {
        isCommented(tablature) -> emptyArray()
        else -> tablature
            .split(whiteSpace.toRegex())
            .map { parseSingleToken(it) }
            .toTypedArray()
    }

    private fun isCommented(tablature: String) = tablature[0] == commentMarker

    private fun parseSingleToken(token: String): Note = when {
        drawNote.matches(token) -> parseDrawNote(token)
        else -> blow(Integer.parseInt(token))
    }

    private fun parseDrawNote(token: String): Note = holeNumberPattern.find(token)
            .let { searchResult -> searchResult?.value}
            .let { holeNumberString -> Integer.parseInt(holeNumberString) }
            .let { holeNumber -> draw(holeNumber) }
}