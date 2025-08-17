package io.hannes.controllable

class Robot(val position: Position, var directionKey: Char, val bounds: Bounds) {
    val directions = listOf(
        Direction('N', 0, -1),
        Direction('E', 1, 0),
        Direction('S', 0, 1),
        Direction('W', -1, 0),
    )

    var currentDirectionId = 0

    init {
        // TODO PRINT INITIAL INFO? OR WHEN YOU WRITE IT IN PROMPT?
        val initialDirectionId = directions.indexOfFirst { it.key == directionKey }
        check(initialDirectionId >= 0) {
            "invalid initial direction"
        }

        currentDirectionId = initialDirectionId
    }

    fun processInstructions(instructionsRaw: String) {
        val instructions = instructionsRaw.uppercase().toCharArray()
        for (instruction in instructions) {
            processInstruction(instruction)
        }

        println("Report: ${position.x} ${position.y} ${currentDirection().key}")
    }

    fun processInstruction(instruction: Char) {
        when (instruction) {
            'F' -> moveForward()
            'L' -> rotate(-1)
            'R' -> rotate(1)
            else -> error("command not implemented")
        }

        // Fix report keyword in robot
    }

    fun rotate(steps: Int) {
        // Rotate with help of modulus to allow seamless addition of more directions
        currentDirectionId = (currentDirectionId + steps).mod(directions.size)
    }

    fun moveForward() {
        val direction = directions.get(currentDirectionId)
        val x = direction.x + position.x
        val y = direction.y + position.y

        check(bounds.inside(x, y)) {
            "invalid movement, robot position out of bounds"
        }

        position.x = x
        position.y = y
    }

    fun currentDirection(): Direction {
        return directions.get(currentDirectionId)
    }
}
