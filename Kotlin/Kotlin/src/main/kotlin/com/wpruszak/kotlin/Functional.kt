import java.security.Security

fun providers(fn: (String, String) -> Unit) {
    Security.getProviders().asList()
            .forEach { provider ->
                provider.forEach { k, v ->
                    fn(k.toString(), v.toString())
                }
            }
}

fun main(args: Array<String>) {
    providers { a, b ->
        println(a)
        println(b)
    }

    (1..10).toList().forEach(::println)
    val y = (1..10).map { it + 2 }.map { it - 3 }.reduce { a, b -> a + b }
    val z = (1..10).fold(0) { sum, element -> sum + element }
    println(y)
    println(z)

    val i = if (2 > 4) {
        3
    } else {
        2
    }
    println(i)
}
