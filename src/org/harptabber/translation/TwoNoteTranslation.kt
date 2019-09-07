package org.harptabber.translation

class TwoNoteTranslation(val left: Note, val right: Note):
    NoteTranslation
{
    override fun getClosestTo(previouslyPlayed: Note): Note {
        val distanceToLeft = previouslyPlayed.distanceTo(left)
        val distanceToRight = previouslyPlayed.distanceTo(right)

        return when {
            distanceToLeft < distanceToRight -> left
            previouslyPlayed == left -> left
            previouslyPlayed == right -> right
            else -> right
        }
    }

    override fun getMostRight(): Note
    {
        return right;
    }
}