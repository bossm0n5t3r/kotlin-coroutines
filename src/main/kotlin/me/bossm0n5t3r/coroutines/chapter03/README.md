# 3장. 중단은 어떻게 작동할까?

## 'suspendCoroutine' lacks cancellation guarantees

- Reports usages of kotlin.coroutines.suspendCoroutine and suggests replacing them with
  kotlinx.coroutines.suspendCancellableCoroutine when the kotlinx.coroutines dependency is present.
- The suspendCancellableCoroutine variant provides built-in cancellation support that is important for structured
  concurrency and proper resource management.

### Suppressing Inspection

```kotlin
@Suppress("SuspendCoroutineLacksCancellationGuarantees")
```

### links

- https://www.jetbrains.com/help/inspectopedia/SuspendCoroutineLacksCancellationGuarantees.html
