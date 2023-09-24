import java.net.URI
import java.time.Duration
import java.util.concurrent.Executors
import kotlin.time.TimedValue
import kotlin.time.measureTime
import kotlin.time.measureTimedValue


fun main() {
    println("Hello World!")

    measureTime {
        val timedResults = fetchAll(
            URI.create("https://news.ycombinator.com/"),
            URI.create("https://www.heise.de/"),
        )
        val sumOfIndividualTimes = Duration.ofMillis(timedResults.sumOf { it.duration.inWholeMilliseconds })
        println("sum of individual execution times: $sumOfIndividualTimes")
    }.also { totalTime->
        println("total execution time: $totalTime")
    }
}

fun fetchAll(vararg uris: URI): List<TimedValue<Result<String>>> = Executors.newVirtualThreadPerTaskExecutor().use { executor ->
    uris
        .map { executor.submit<TimedValue<Result<String>>> { fetch(it) } }
        .map { it.get() }
}

fun fetch(uri: URI): TimedValue<Result<String>> = measureTimedValue {
    runCatching {
        uri.toURL().openStream().use { it.readAllBytes().decodeToString() }
    }
}
