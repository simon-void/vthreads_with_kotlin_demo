import java.net.URI
import java.util.concurrent.Executors
import kotlin.time.Duration
import kotlin.time.TimedValue
import kotlin.time.measureTime
import kotlin.time.measureTimedValue


fun main() {
    println("Let's try out VirtualThreads!")
    val urlsToFetch: List<URI> = listOf(
        URI.create("https://news.ycombinator.com/"),
        URI.create("https://www.heise.de/"),
        URI.create("https://slashdot.org/"),
    )

    val totalTime: Duration = measureTime {
        val timedResults: List<TimedValue<Result<String>>> = urlsToFetch.concurrentMap { fetch(it) }

        val sumOfIndividualTimesInMs = timedResults.sumOf { it.duration.inWholeMilliseconds }
        println("sum of individual execution times: ${sumOfIndividualTimesInMs}ms")
    }
    println("total execution time: ${totalTime.inWholeMilliseconds}ms")
}

inline fun <T, R> Iterable<T>.concurrentMap(crossinline transform: (T) -> R): List<R> =
    Executors.newVirtualThreadPerTaskExecutor().use { executorService ->
        this.map { executorService.submit<R> { transform(it) } }.map { it.get() }
    }

fun fetch(uri: URI): TimedValue<Result<String>> = measureTimedValue {
    runCatching {
        uri.toURL().openStream().use { it.readAllBytes().decodeToString() }
    }
}
