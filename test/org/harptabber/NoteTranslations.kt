package org.harptabber

import org.harptabber.translation.*
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.test.*;
import org.junit.jupiter.api.Test as test;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NoteTranslations
{
    @test fun `Diatonic note that has single mapping is resolved to single orchestra note`()
    {
        val expectedNote = blow(3);
        val mapping = noteMapping(
            blow(1) to single(expectedNote)
        );

        val providedNote = mapping.toOrchestra(blow(1))

        assertEquals(expectedNote, providedNote);
    }

    @test fun `Given diatonic note that does not have mapping, error is thrown`()
    {
        assertThrows<IllegalArgumentException> {
            noteMapping().toOrchestra(draw(1));
        }
    }

    @test fun `Given diatonic note that maps to two orchestra notes, the right one is chosen`()
    {
        val expectedNote = blow(2)
        val mapping = noteMapping(
            blow(1) to multiple(draw(1), expectedNote)
        )

        val providedNote = mapping.toOrchestra(blow(1))

        assertEquals(expectedNote, providedNote)
    }

    @test fun `Given previously played note is left of mapped the closest of two is chosen`()
    {
        val expectedNote = blow(4)
        val mapping = noteMapping(
            blow(1) to multiple(blow(4), blow(5))
        )

        val providedNote = mapping.toOrchestra(blow(1), blow(3))

        assertEquals(providedNote, expectedNote)
    }

    @test fun `Given previously played note is right of mapped the closest of two is chosen`()
    {
        val expectedNote = blow(5)
        val mapping = noteMapping(
            blow(1) to multiple(blow(4), expectedNote)
        )
        val previouslyPlayed = blow(6)

        val providedNote = mapping.toOrchestra(blow(1), previouslyPlayed)

        assertEquals(expectedNote, providedNote)
    }

    @test fun `Given previously played note is same as the first then the first is chosen`()
    {
        val first = blow(5)
        val second = blow(6)
        val previouslyPlayed = blow(5)

        val providedNote = noteMapping(blow(1) to multiple(first, second))
            .toOrchestra(blow(1), previouslyPlayed)

        assertEquals(providedNote, first)
    }

    @test fun `Given previously played note is same as the second one then the second is chosen`()
    {
        val first = blow(5)
        val second = blow(6)
        val previouslyPlayed = blow(6)

        val providedNote = noteMapping(blow(1) to multiple(first, second))
            .toOrchestra(blow(1), previouslyPlayed)

        assertEquals(providedNote, second)
    }
}
