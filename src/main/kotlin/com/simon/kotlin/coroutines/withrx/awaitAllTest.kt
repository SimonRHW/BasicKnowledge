package com.simon.kotlin.coroutines.withrx


import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.rx2.await
import kotlinx.coroutines.rx2.awaitFirstOrNull
import java.util.concurrent.TimeUnit
import kotlin.random.Random

data class RefreshEmit(val flag: Int = 0) {
    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return Random.nextInt()
    }
}

object BehaviorProcessor {

    private var publisher = io.reactivex.processors.BehaviorProcessor.create<String>()

    fun getInstallStatusFlowable(): Flowable<String> {
        return publisher.retry().subscribeOn(Schedulers.io())
    }

    fun publishInstallStatus(name: String) {
        println("publishInstallStatus =======> $name")
        publisher.onNext(name)
    }
    private var loaderTask: Disposable? = null
    fun loader(): Observable<String> {
//        loaderTask?.takeUnless { it.isDisposed }?.dispose()
        return Observable.create(ObservableOnSubscribe<String> {
            println("onNext loader")
            it.onNext("loader")
            it.onComplete()
        }).doOnSubscribe {
            loaderTask = it
        }.delay(100, TimeUnit.MILLISECONDS)
    }

    fun upload(
    ): Single<String> {
        return Single.create {
            println("onSuccess upload")
            it.onSuccess("upload")
        }.delay(200, TimeUnit.MILLISECONDS)
    }
}

/**
 * @author Simon
 * @desc
 */
suspend fun main() = runBlocking {
    val modeA = Mode("a")
    val modeB = Mode("b")
    delay(500)
    BehaviorProcessor.publishInstallStatus("1")
    delay(5)
    BehaviorProcessor.publishInstallStatus("1")
    delay(8)
    BehaviorProcessor.publishInstallStatus("1")
    Thread.sleep(3000L) // 阻塞主线程 3 秒钟来保证 JVM 存活
    println("Coroutine scope is over")
}

class Mode(val tag: String) {
    private val refreshFlow = MutableStateFlow(RefreshEmit())

    init {
        BehaviorProcessor.getInstallStatusFlowable().subscribe({
            println("$tag packageManagerDelegate  subscribe $it")
            refresh()
        }, {
            println("$tag packageManagerDelegate  error $it")
        })
        fetch()
    }

    private fun refresh() {
        GlobalScope.launch {
            println("$tag   refresh")
            refreshFlow.emit(RefreshEmit())
        }
    }

    private var fetchJob: Job? = null
    private fun fetch() {
        println("$tag fetch")
        fetchJob?.cancel()
        fetchJob = GlobalScope.launch {
            combine(
                refreshFlow,
            ) {
                println("$tag combine")
                loadData()
            }.collect()
        }
    }

    private suspend fun loadData() = coroutineScope {
        println("$tag loadData")
        val awaitLoader = async {
            BehaviorProcessor.loader().awaitFirstOrNull()
        }
        println("$tag awaitLoader$awaitLoader")
        val awaitUpload = async {
            BehaviorProcessor.upload().await()
        }
        println("$tag awaitUpload$awaitUpload")
        val awaitAll = listOf(awaitLoader, awaitUpload).awaitAll()
        println("$tag awaitAll$awaitAll")
    }
}

