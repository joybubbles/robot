package io.hannes

import io.hannes.controllable.Bounds
import io.hannes.controllable.Position
import io.hannes.controllable.Robot

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("Input room bounds as width height (example: 8 7)")

    val bounds = readln().split(' ')
    check(bounds.count() == 2) { "Invalid number of bounds arguments, expected is 2" }
    val width = bounds.get(0).toIntOrNull() ?: error("Invalid width value, must be an integer")
    val height = bounds.get(1).toIntOrNull() ?: error ("Invalid height value, must be an integer")

    println("Input robot starting position as x y direction (example: 4 5 N)")

    val position = readln().split(' ')
    check(position.count() == 3) { "Invalid number of position arguments, expected is 3" }
    val x = position.get(0).toIntOrNull() ?:  error("Invalid x value, must be an integer")
    val y = position.get(1).toIntOrNull() ?:  error("Invalid y value, must be an integer")
    val directionKey = position.get(2).uppercase().toCharArray().first()

    val robot = Robot(
        Position(x, y),
        directionKey,
        Bounds(width, height),
    )

    println("The robot is ready to accept commands")
    while(true) {
        robot.processInstructions(readln())
    }
}