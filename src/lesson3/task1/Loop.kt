@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import kotlin.math.*
import kotlin.math.pow

/* Вычисление факториала */
fun factorial(n: Int): Double =
    if (n <= 1) 1.0
    else n * factorial(n - 1)

/* Проверка числа на простоту -- результат true, если число простое */
fun isPrime(n: Int): Boolean {
    when {
        n < 2 -> return false
        n == 2 -> return true
        n % 2 == 0 -> return false
    }
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/* Проверка числа на простоту со списком */
fun isPrimeFast(n: Int): Boolean {
    val primeList = mutableListOf(3)
    if (n < 2) return false
    for (m in primeList) {
        if (n % m == 0) return false
        else primeList.add(m)
    }
    return true
}

/* Проверка числа на совершенность -- результат true, если число совершенное */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/* Найти число вхождений цифры m в число n */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int =
    when {
        n < 10 -> 1
        else -> 1 + digitNumber(n / 10)
    }

/**
 * Простая
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var result = 1
    var last = 1
    for (i in 3..n) {
        result += last
        last = result - last
    }
//    if (n <= 2)
//        return 1
//    else {
//        result = fib(n - 1) + fib(n-2)
//    }
    return result
}

/**
 * Простая
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var gcd = 1
    for (i in 2..min(m, n)) {
        if (m % i == 0 && n % i == 0)
            gcd = i
    }
    return m * n / gcd
}

/**
 * Простая
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    (2..sqrt(n.toDouble()).toInt()).forEach {
        if (n % it == 0) return it
    }
    return n
}

/**
 * Простая
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    (n / 2 downTo 2).forEach {
        if (n % it == 0) return it
    }
    return 1
}

/**
 * Простая
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if ((m > n && m % n == 0) || (n > m && n % m == 0))
        return false
    for (i in 2..(sqrt(min(m, n).toDouble()).toInt())) {
        if (m / i > 0 && m % i == 0)
            if (n / i > 0 && n % i == 0) return false
    }
    return true
}

/**
 * Простая
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for (k in 1..(n - m + 1))
        if (k * k in m..n) return true

    return false
}

/**
 * Средняя
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var count = 0
    var next = x
    while (next > 1) {
        if (next % 2 == 0) next /= 2
        else next = 3 * next + 1
        count++
    }
    return count
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
    var x: Double = x
    while (x < -2 * PI || x > 2 * PI)
        x = x % PI + PI
    var sinX: Double = 0.0
    var member = x
    var grade = 1
    var sign = 1
    do {
        sinX += sign * member
        grade += 2
        sign *= -1
        member = x.pow(grade) / factorial(grade)
    } while (abs(member) >= eps)
    return sinX
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
    var x: Double = x
    while (x < -2 * PI || x > 2 * PI)
        x = -x % PI
    var cosX = 0.0
    var member = 1.0
    var grade = 0
    var sign = 1
    do {
        cosX += sign * member
        grade += 2
        sign *= -1
        member = x.pow(grade) / factorial(grade)
    } while (abs(member) >= eps)
    return cosX
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var n = n
    var answer = 0
    do {
        answer = answer * 10 + n % 10
        n /= 10
    } while (n > 0)
    return answer
}

/**
 * Средняя
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean =
    n == revert(n)

/**
 * Средняя
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var n = n
    val num = n % 10
    do {
        if (num != n % 10) return true
        n /= 10
    } while (n > 0)
    return false
}

/**
 * Сложная
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    if (n == 0) return 0
    var count = 0
    var i = 1
    var seqNum = 0
    var currentSqr = 0
    var digits: Int

    /* make sequence with self length >= n */
    while (count < n) {
        currentSqr = sqr(i)
        digits = digitNumber(currentSqr)
        count += digits
        seqNum = ((10.0).pow(digits.toDouble()) * seqNum + currentSqr).toInt()
        i++
    }
    for (i in 0 until (count - n))
        currentSqr /= 10

    return currentSqr % 10
}

/**
 * Сложная
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    if (n == 0) return 0
    var count = 0
    var i = 1
    var seqNum = 0
    var currentSqr = 0
    var digits: Int

    /* make sequence with self length >= n */
    while (count < n) {
        currentSqr = fib(i)
        digits = digitNumber(currentSqr)
        count += digits
        seqNum = ((10.0).pow(digits.toDouble()) * seqNum + currentSqr).toInt()
        i++
    }
    for (i in 0 until (count - n))
        currentSqr /= 10

    return currentSqr % 10
}