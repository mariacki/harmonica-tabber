package org.harptabber.parsing

import org.harptabber.translation.Note
import java.io.File

class FileParser(
    private val tabParser: TablatureParser
) {
    fun parse(fileToRead: File): Array<Note>
    {
        var parsedNotes: Array<Note> = emptyArray()

        fileToRead.forEachLine {
            println(it)
            parsedNotes += tabParser.parseLine(it)
        }

        return parsedNotes
    }
}