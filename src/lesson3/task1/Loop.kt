@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import java.lang.Double.isNaN
import java.math.RoundingMode
import javax.annotation.processing.RoundEnvironment
import kotlin.math.*


/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var count = 0
    var number = abs(n)
    do {
        count++
        number /= 10
    } while (number > 0)
    return count

}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var number = 1
    var number2 = 0
    for (count in 2..n) {
        if (n < 3) break
        number += number2
        number2 = number - number2
    }
    return number
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun gcd(m: Int, n: Int): Int {
    var digitN = n
    var digitM = m
    while ((digitM != 0) && (digitN != 0)) {
        if (digitM > digitN)
            digitM %= digitN
        else digitN %= digitM
    }
    return (digitN + digitM)
}

fun lcm(m: Int, n: Int): Int = m * n / gcd(m, n)


/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for (i in 2..sqrt(n.toDouble()).toInt()) {
        if (n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int = n / minDivisor(n)

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = gcd(m, n) == 1

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean = truncate(sqrt(n.toDouble())).pow(2) >= m

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var digit = x
    var count = 0
    while (digit != 1) {
        if (digit % 2 == 0) digit /= 2 else digit = digit * 3 + 1
        count++
    }
    return (count)
}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var res = 0.0
    val sin = x % (2 * PI)
    var factorialDegree = 1.0
    var count = 1
    var minus = 1
    var member = sin
    var secondX = sin
    val constantX = sqr(sin)
    while (abs(member) >= eps) {
        res += minus * member
        count += 2
        secondX *= constantX
        minus *= -1
        factorialDegree *= count * (count - 1)
        member = secondX / factorialDegree
    }
    return (res)
}



/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var res = 0.0
    var cos = x % (2 * PI)
    if (x < 0) cos *= -1
    var count = 0
    var factorialDegree = 1.0
    var minus = 1
    var member = 1.0
    var secondX = 1.0
    val constantX = sqr(cos)
    while (abs(member) >= eps) {
        res += minus * member
        count += 2
        secondX *= constantX
        minus *= -1
        factorialDegree *= count * (count - 1)
        member = secondX / factorialDegree
    }
    return (res)
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var reverse = 0
    var revertCopy = n
    while (revertCopy > 0) {
        reverse = reverse * 10 + revertCopy % 10
        revertCopy /= 10
    }
    return reverse
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = n == revert(n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val digitLast = n % 10
    var digitN = n / 10
    while (digitN > 0) {
        if (digitLast != digitN % 10) return true
        digitN /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var count = 1
    var quantitiNumber = 1
    var digit = 1
    var digitClone = 1
    while (n > quantitiNumber) {
        count++
        digit = sqr(count)
        digitClone = digit
        quantitiNumber += digitNumber(digit)
    }
    while (n != quantitiNumber) {
        quantitiNumber -= 1
        digitClone /= 10
    }
    return (digitClone % 10)
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var count = 1
    var quantitiNumber = 1
    var digit = 1
    var digitClone = 1
    while (n > quantitiNumber) {
        count++
        digit = fib(count)
        digitClone = digit
        quantitiNumber += digitNumber(digit)
    }
    while (n != quantitiNumber) {
        quantitiNumber -= 1
        digitClone /= 10
    }
    return (digitClone % 10)
}
