package org.harptabber.translation

import kotlin.math.min

class TwoNoteTranslation(val left: Note, val right: Note):
    NoteTranslation
{
    override fun getClosestTo(previouslyPlayed: Note): Note {
        val distanceToLeft = previouslyPlayed.distanceTo(left)
        val distanceToRight = previouslyPlayed.distanceTo(right)

        return when(min(distanceToLeft, distanceToRight)) {
            distanceToLeft -> left
            else -> right
        }
    }

    override fun getMostRight(): Note
    {
        return right;
    }
}