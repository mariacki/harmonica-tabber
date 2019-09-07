package org.harptabber.translation

fun noteMapping(vararg pairs: Pair<Note, NoteTranslation>): Translator
{
    return Translator(*pairs);
}

fun blow(hole: Int): Note
{
    return Note(hole, AirDirection.BLOW)
}

fun draw(hole: Int): Note
{
    return Note(hole, AirDirection.DRAW)
}

fun single(note: Note): NoteTranslation
{
    return SingleNoteTranslation(note)
}

fun multiple(left: Note, right: Note): NoteTranslation
{
    return TwoNoteTranslation(left, right);
}

