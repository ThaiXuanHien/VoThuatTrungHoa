package com.hienthai.baseprojectmvvm.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

inline fun <reified T> stateFlow(initialValue: T) = MutableStateFlow(initialValue)

inline fun <reified T> sharedEventFlow() = MutableSharedFlow<T>(extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

fun <T> Flow<T>.observe(
    owner: LifecycleOwner,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    action: suspend (T) -> Unit
) {
    flowWithLifecycle(owner.lifecycle, minActiveState)
        .onEach(action)
        .launchIn(owner.lifecycleScope)
}

suspend fun <T> ioContext(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.IO, block)

suspend fun <T> mainContext(block: suspend CoroutineScope.() -> T) = withContext(Dispatchers.Main, block)

internal suspend fun <TResult> Task<TResult>.awaitOrElse(value: TResult): TResult = suspendCancellableCoroutine { continuation ->
    addOnSuccessListener {
        continuation.resume(it)
    }.addOnFailureListener {
        continuation.resume(value)
    }
}