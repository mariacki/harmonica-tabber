package org.harptabber.parsing

import org.harptabber.translation.Note
import org.harptabber.translation.blow

class DiatonicParser
{
    private val whiteSpace = "\\s+"

    private val commentMarker = '#'

    fun parse(tabulature: String): Array<Note>
    {
        return when (true) {
            tabulature[0] == commentMarker -> emptyArray()
            else -> tabulature
                .split(whiteSpace.toRegex())
                .map { blow(Integer.parseInt(it)) }
                .toTypedArray()
        }
    }
}