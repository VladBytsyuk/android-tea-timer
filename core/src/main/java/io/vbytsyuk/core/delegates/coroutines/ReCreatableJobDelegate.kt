package io.vbytsyuk.core.delegates.coroutines

import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.reflect.KProperty

fun reCreatableSupervisorJob() = ReCreatableJobDelegate { SupervisorJob() }

class ReCreatableJobDelegate(
    private val jobProvider: () -> Job,
) {
    private var job: Job = jobProvider.invoke()

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Job {
        if (job.isCancelled) job = jobProvider.invoke()
        return job
    }
}
