package io.hannes.controllable

class Bounds(val width: Int, val height: Int) {
    init {
        check(width >= 0) {
            "Invalid width, must be a positive integer"
        }
        check(height >= 0) {
            "Invalid height, must be a positive integer"
        }
    }

    fun inside(x: Int, y: Int): Boolean {
        return x in 0..< width && y in 0..< height
    }
}
