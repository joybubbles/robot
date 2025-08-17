package controllable

import io.hannes.controllable.Bounds
import io.hannes.controllable.Position
import io.hannes.controllable.Robot
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class RobotTest {
    @Test
    fun moveForward() {
        // Given
        val robot = Robot(
            Position(4, 7),
            'N',
            Bounds(10, 10), // Outside of class?
        )

        // When
        robot.processInstruction('F')

        // Then
        assertEquals(4, robot.position.x)
        assertEquals(6, robot.position.y)
    }

    @Test
    fun rotateLeft() {
        // Given
        val robot = Robot(
            Position(4, 7),
            'N',
            Bounds(10, 10), // Outside of class?
        )

        // When
        robot.processInstruction('L')

        // Then
        assertEquals('W', robot.currentDirection().key)
    }

    @Test
    fun rotateRight() {
        // Given
        val robot = Robot(
            Position(4, 7),
            'N',
            Bounds(10, 10), // Outside of class?
        )
        // When
        robot.processInstruction('R')

        // Then
        assertEquals('E', robot.currentDirection().key)
    }


    @Test
    fun processInstructions() {
        // Given
        val robot = Robot(
            Position(1, 2),
            'N',
            Bounds(5, 5), // Outside of class?
        )
        // When
        robot.processInstructions("RFRFFRFRF")

        // Then
        assertEquals(1, robot.position.x)
        assertEquals(3, robot.position.y)
        assertEquals('N', robot.currentDirection().key)
    }

    @Test
    fun processInstructionsOutOfBounds() {
        // Given
        val robot = Robot(
            Position(1, 2),
            'N',
            Bounds(5, 5), // Outside of class?
        )

        // When / Then
        assertFailsWith<IllegalStateException> {
            robot.processInstructions("FFFFFFFFFFFF")
        }
    }

    @Test
    fun processCommandNotImplemented() {
        // Given
        val robot = Robot(
            Position(1, 2),
            'N',
            Bounds(5, 5), // Outside of class?
        )

        // When / Then
        assertFailsWith<IllegalStateException> {
            robot.processInstructions("FFRRLX")
        }
    }
}