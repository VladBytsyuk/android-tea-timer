package io.vbytsyuk.core.delegates.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.isActive
import kotlin.coroutines.CoroutineContext
import kotlin.reflect.KProperty

fun reCreatableCoroutineScope(contextProvider: () -> CoroutineContext) =
    ReCreatableScopeDelegate(contextProvider)

class ReCreatableScopeDelegate(
    private val contextProvider: () -> CoroutineContext,
) {
    private var scope: CoroutineScope = createScope()

    private fun createScope(): CoroutineScope = CoroutineScope(contextProvider.invoke())

    operator fun getValue(thisRef: Any?, property: KProperty<*>): CoroutineScope {
        if (!scope.isActive) scope = createScope()
        return scope
    }
}
