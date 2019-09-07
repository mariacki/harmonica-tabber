package org.harptabber.translation

interface NoteTranslation {
    fun getClosestTo(previouslyPlayed: Note): Note
    fun getMostRight(): Note
}