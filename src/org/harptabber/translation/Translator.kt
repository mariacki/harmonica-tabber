package org.harptabber.translation

import org.harptabber.translation.Note
import org.harptabber.translation.NoteTranslation
import java.lang.IllegalArgumentException

class Translator(vararg pairs: Pair<Note, NoteTranslation>) {

    private val representation: Map<Note, NoteTranslation>;

    init {
        representation = hashMapOf(*pairs)
    }

    fun toOrchestra(diatonicNote: Note): Note
    {
        return (representation[diatonicNote]?:notFound(diatonicNote))
            .getMostRight();
    }

    fun toOrchestra(diatonicNote: Note, previouslyPlayed: Note): Note
    {
        return (representation[diatonicNote]?:notFound(diatonicNote))
            .getClosestTo(previouslyPlayed)
    }

    private fun notFound(diatonicNote: Note): NoteTranslation
    {
        throw IllegalArgumentException()
    }
}