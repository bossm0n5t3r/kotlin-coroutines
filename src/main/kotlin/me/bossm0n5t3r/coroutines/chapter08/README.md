# 8장. 잡과 자식 코루틴 기다리기

## CoroutineContext' can contain 'Job' element

- Reports coroutine builders calls that violate structured concurrency by accepting a CoroutineContext containing a Job.
- Passing contexts with a Job to coroutine builders like launch, async, produce, promise, or to withContext breaks the
  parent-child relationship between coroutines, defeating the purpose of structured concurrency.

### Suppressing Inspection

```kotlin
@Suppress("CoroutineContextWithJob", "DEPRECATION")
```

### links

- https://www.jetbrains.com/help/inspectopedia/CoroutineContextWithJob.html
