package org.harptabber

import org.harptabber.parsing.FileParser
import org.harptabber.translation.*
import java.io.File

class HarpTabber(
    private val fileParser: FileParser
) {
    private val mapping = noteMapping(
        blow(1) to single(blow(1)),
        draw(1) to single(draw(1)),
        blow(2) to single(blow(2)),
        draw(2) to single(blow(3)),
        blow(3) to single(blow(3)),
        draw(3) to single(draw(4)),
        blow(4) to multiple(blow(4), blow(4)),
        draw(4) to single(draw(5)),
        blow(5) to single(blow(6)),
        draw(5) to single(draw(6)),
        blow(6) to single(blow(7)),
        draw(6) to single(draw(7)),
        blow(7) to multiple(blow(8), blow(9)),
        draw(7) to single(draw(8)),
        blow(8) to single(blow(10)),
        draw(8) to single(draw(9)),
        blow(9) to single(blow(11)),
        draw(9) to single(draw(9)),
        blow(10) to single(blow(12)),
        draw(10) to single(draw(11))
    )

    fun translate(tabPath: String)
    {
        val tablatureFile = File(tabPath)

        val parsed = fileParser.parse(tablatureFile)
        var previouslyPlayed = parsed[0]

        parsed.map {
            val orchestraNote = mapping.toOrchestra(it, previouslyPlayed)
            previouslyPlayed = orchestraNote

            println("Mapping $it to $orchestraNote")

            orchestraNote
        }.forEach {
            print("$it ")
        }
    }
}