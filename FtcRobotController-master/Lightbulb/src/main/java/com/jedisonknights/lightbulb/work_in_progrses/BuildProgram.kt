package com.jedisonknights.lightbulb.work_in_progrses

import java.io.File
inline fun BuildProgram(file_name : String, deployment_file_name : String) {
    var data = mutableListOf<String> ()
    var subsystems = mutableListOf<String>()
    var subsystem_names = mutableListOf<String>()
    var tempStorage = mutableListOf<String>()
    var subsystem_ticks_refresh_rate = mutableListOf<Int>()
    var subsystem_ticks_last = mutableListOf<Int>()
    var method_calls = mutableListOf<FunctionCall>()
    var drivetrain : String = ""

    var i : Int = 0
    var c : Int = 0

    for (line : String in File(file_name).reader().readLines()) {
        if(line.filterNot {it.isWhitespace()}.contains("opModeIsActive()",true)) {
            c = i
        }
        i++
    } //grab the index the init/pre-init phase ends at

    val init = data.slice(IntRange(0,c))

    for (line : String in init) {
        if (line.filterNot {it.isWhitespace()}.contains("SubsystemClass", true)) {
            subsystems.add(line)
        }
        if (line.filterNot { it.isWhitespace() }.contains("MechanumDriveTrain")) {
            drivetrain = line.filterNot { it.isWhitespace() }.substring(IntRange(line.filterNot { it.isWhitespace() }.indexOf("MechanumDriveTrain")+18, line.filterNot { it.isWhitespace() }.indexOf("=")))
        }
    } //find the subsystem & drivetrain calls

    for (line : String in subsystems) {
        var space_one : Int = -1
        var space_two : Int = -1
        if (line.toLowerCase().indexOf("SubsystemClass ".toLowerCase()) != -1) {
            for (j: Int in IntRange(
                line.toLowerCase().indexOf("SubsystemClass ".toLowerCase()),
                line.length
            )) {
                if (line[j] == ' ' && space_one == -1) {
                    space_one = j
                } else if (line[j] == ' ' && space_two == -1 && space_one != -1) {
                    space_two = j
                }
            }
            if (space_one != -1 && space_two != -1) {
                subsystem_names.add(line.substring(IntRange(space_one, space_two)))

                space_one = -2
                for (j: Int in IntRange(line.toLowerCase().indexOf("("), line.toLowerCase().indexOf(")"))) {
                    if (line[j] == ',') {
                        space_one == j
                    }
                    if (space_one != -2) {
                        break
                    }
                }
                subsystem_ticks_last.add(line.substring(IntRange(line.toLowerCase().indexOf("("), space_one)).toInt())
                subsystem_ticks_refresh_rate.add(line.substring(IntRange(space_one+1, line.toLowerCase().indexOf(")"))).toInt())
            }
        }
    } //find the names & init values of the subsystems for later calling

    for (line : String in data.slice(IntRange(c, data.size))) {
        for (i in IntRange(0, subsystem_names.size)) {
            if (line.toLowerCase().filterNot { it.isWhitespace() }.contains(subsystem_names[i].toLowerCase())) {
                method_calls.add(FunctionCall(line, subsystem_ticks_last[i], subsystem_ticks_refresh_rate[i]))
            }
            else if (line.toLowerCase().filterNot { it.isWhitespace() }.contains(drivetrain.toLowerCase())) {
                method_calls.add(FunctionCall(line, 1, 1))
            }
        }
    } //find all the subsystems and their method calls, tick rate, tick last and prepare for the final deployment

    File(deployment_file_name).writeText("$data")
}