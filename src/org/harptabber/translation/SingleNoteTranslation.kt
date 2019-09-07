package org.harptabber.translation

class SingleNoteTranslation(private val value: Note): NoteTranslation
{
    override fun getMostRight(): Note {
        return value
    }

    override fun getClosestTo(previouslyPlayed: Note): Note {
        return value
    }
}