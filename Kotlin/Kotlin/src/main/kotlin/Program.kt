import com.wpruszak.kotlin.Providers
import java.security.Provider
import java.util.function.Consumer

fun main(args: Array<String>) {
    val providers = Providers()
    listProviders(providers.getProviders())
}

fun listProviders(providers: List<Provider>) {
//    works as well
//    providers.forEach({ provider -> println(provider.name) })
    providers.forEach(Consumer { provider ->
        println(provider.name)
        provider.forEach { key, value -> println("\t$key: $value") }
    })
}
