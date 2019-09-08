package org.harptabber

import org.harptabber.parsing.FileParser
import org.harptabber.parsing.TablatureParser

fun main(args: Array<String>)
{
    if (args.isEmpty()) {
        println("Specify tablature file as argument")
        return
    }

    val tabber = HarpTabber(
        FileParser(TablatureParser())
    );

    tabber.translate(args[0]);
}