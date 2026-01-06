package ru.itis.gloriaartis.utils

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.perf.metrics.Trace
import com.google.firebase.perf.performance
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PerformanceCustomTracer @Inject constructor() {

    private val traces = mutableMapOf<String, Trace>()
    private val mutex = Mutex()

    suspend fun startTrace(traceName: String) {
        mutex.withLock {
            traces[traceName]?.let { oldTrace ->
                oldTrace.stop()
                traces.remove(traceName)
            }
            val trace = Firebase.performance.newTrace(traceName)
            trace.start()
            traces[traceName] = trace
        }
    }
    suspend fun stopTrace(traceName: String) {
        mutex.withLock {
            traces[traceName]?.let {
                it.stop()
                traces.remove(traceName)
            }
        } ?: run {
            Log.w("PerformanceTracer", "Trace '$traceName' not found")
        }
    }

    suspend fun clearAllTraces() {
        mutex.withLock {
            traces.values.forEach { it.stop() }
            traces.clear()
        }
    }
}