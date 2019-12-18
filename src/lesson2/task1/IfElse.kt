@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import java.lang.IllegalArgumentException
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String =
    when {
        age / 10 % 10 == 1 -> "$age лет"
        age % 10 == 0 -> "$age лет"
        age % 10 == 1 -> "$age год"
        age % 10 >= 5 -> "$age лет"
        else -> "$age года"
    }

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val halfWay = (s1 + s2 + s3) / 2.0
    return when {
        halfWay <= s1 -> halfWay / v1
        (halfWay > s1) && (halfWay <= s1 + s2) -> (halfWay - s1) / v2 + t1
        else -> (halfWay - s1 - s2) / v3 + t1 + t2
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int = when {
    ((kingX == rookX1) || (kingY == rookY1)) && ((kingX == rookX2) || (kingY == rookY2)) -> 3
    (kingX == rookX1) || (kingY == rookY1) -> 1
    (kingX == rookX2) || (kingY == rookY2) -> 2
    else -> 0
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    val rook = (rookX == kingX || rookY == kingY)
    val bishop = (Math.abs(kingX - bishopX) == Math.abs(kingY - bishopY))
    return when {
        rook && bishop -> 3
        rook && !(bishop) -> 1
        !(rook) && bishop -> 2
        else -> 0
    }
}
/**
 * Простая
 *
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    if ((maxOf(a, b, c) * 2) < (a + b + c)) {
        val cosA = (c * c + b * b - a * a) / (2.0 * c * b)
        val cosB = (c * c + a * a - b * b) / (2.0 * c * a)
        val cosC = (a * a + b * b - c * c) / (2.0 * a * b)
        return when {
            (cosA > 0) && (cosB > 0) && (cosC > 0) -> 0
            (cosA == 0.0) || (cosB == 0.0) || (cosC == 0.0) -> 1
            else -> 2
        }
    }
    return -1
}


/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = max(-1, kotlin.math.min(b, d) - max(a, c))

fun bishopCells(size: Int, row: Int, column: Int): Int {
    var cells = size - abs((size + 1) - (row + column))
    cells += size - abs(row - column)
    return cells - 2
}

fun chess(size: Int, figure: String, row: Int, column: Int): Int {
    val figures = listOf("Rook", "Bishop", "King", "Queen", "Knight")
    if ((figure !in figures) || (row > size) || (column > size) || (row < 1) || (column < 1))
        throw IllegalArgumentException()
    var numberOfCells = 0
    when (figure) {
        "Bishop" -> numberOfCells = bishopCells(size, row, column)
        "Rook" -> numberOfCells = (size - 1) * 2
        "King" -> {
            when {
                (row != 1) && (row != size) && (column != 1) && (column != size) -> numberOfCells = 8
                ((row == 1) || (row == size)) && ((column == 1) || (column == size)) -> numberOfCells = 3
                ((row == 1) || (row == size)) && (column != 1) && (column != size) -> numberOfCells = 5
                (row != 1) && (row != size) && ((column == 1) || (column == size)) -> numberOfCells = 5
            }
        }
        "Queen" -> {
            numberOfCells = (size - 1) * 2
            numberOfCells += bishopCells(size, row, column)
        }
        "Knight" -> {
            numberOfCells = 8
            if ((row + 2 > size) || (column + 1 > size)) numberOfCells -= 1
            if ((row + 2 > size) || (column - 1 < 1)) numberOfCells -= 1
            if ((row - 2 < 1) || (column - 1 < 1)) numberOfCells -= 1
            if ((row - 2 < 1) || (column + 1 > size)) numberOfCells -= 1
            if ((row + 1 > size) || (column + 2 > size)) numberOfCells -= 1
            if ((row - 1 < 1) || (column + 2 > size)) numberOfCells -= 1
            if ((row + 1 > size) || (column - 2 < 1)) numberOfCells -= 1
            if ((row - 1 < 1) || (column - 2 < 1)) numberOfCells -= 1
        }
    }
    return numberOfCells
}