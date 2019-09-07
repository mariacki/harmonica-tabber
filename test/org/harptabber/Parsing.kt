package org.harptabber

import org.harptabber.parsing.DiatonicParser
import org.harptabber.translation.Note
import org.harptabber.translation.blow
import org.harptabber.translation.draw
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.junit.jupiter.api.Test as test

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Parsing
{
    @test fun `Blow notes are parsed`()
    {
        val providedNotes = DiatonicParser().parse("1")

        assertTrue(providedNotes.count() == 1)
        assertEquals(blow(1), providedNotes[0]);
    }

    @test fun `Multiple blow notes are parsed`()
    {
        val providedNotes = DiatonicParser()
            .parse("1 10 2 3")

        assertParsed(
            providedNotes,
            blow(1),
            blow(10),
            blow(2),
            blow(3)
        )
    }

    @test fun `White chars are ignored`()
    {
        val providedNotes = DiatonicParser()
            .parse("1   \t \t 2")
    }

    @test fun `Line beginning with # char are ignored`()
    {
        val providedNotes = DiatonicParser()
            .parse("# This is a line commented out")

        assertEquals(0, providedNotes.size)
    }

    @test fun `Draw notes are parsed`()
    {
        val providedNotes = DiatonicParser()
            .parse("(1) (2) (10)  (3)")

        assertParsed(
            providedNotes,
            draw(1),
            draw(2),
            draw(10),
            draw(3)
        )
    }

    @test fun `Blow and draw notes are parsed`()
    {
        val providedNotes = DiatonicParser()
            .parse("1 (2) (3)    4")

        assertParsed(
            providedNotes,
            blow(1),
            draw(2),
            draw(3),
            blow(4)
        )
    }

    private fun assertParsed(providedNotes: Array<Note>, vararg expectedNotes: Note)
    {
        assertEquals(expectedNotes.size, providedNotes.size)

        expectedNotes.forEachIndexed { i: Int, expectedNote: Note ->
            assertEquals(providedNotes[i], expectedNote)
        }
    }
}