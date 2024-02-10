package com.jedisonknights.lightbulb

import java.io.File
inline fun BuildProgram(file_name : String) {
    var data = mutableListOf<String> ()
    var subsystems = mutableListOf<String>()
    var tempStorage = mutableListOf<String>()

    var i : Int = 0
    var c : Int = 0

    for (line : String in File(file_name).reader().readLines()) {
        if(line.filterNot {it.isWhitespace()}.contains("opModeIsActive()",true)) {
            c = i
        }
        i++
    }

    val init = data.slice(IntRange(0,c)) //find initilaization so we can search for Subsystem names

    for (line : String in init) {
        if (line.filterNot {it.isWhitespace()}.contains("SubsystemClass", true)) {
            subsystems.add(line)
        }
    } //find the subsystem calls

    for (line : String in subsystems) {
        var c: Int = line.toLowerCase().indexOf("SubsystemClass".toLowerCase())
        var space_one : Int = -1
        var space_two : Int = -1
        for (j : Int in IntRange(c, line.length)) {
            if (line[j] == ' ' && space_one == -1) {
                space_one = j
            }
            else if (line[j] == ' ' && space_two == -1 && space_one != -1) {
                space_two = j
            }
        }
        if (space_one != -1 && space_two != -1) {
            tempStorage.add(line.slice(IntRange(space_one, space_two)))
        }
    } //set temp storage to the subsystem names







    File(file_name).writeText("$data")
}