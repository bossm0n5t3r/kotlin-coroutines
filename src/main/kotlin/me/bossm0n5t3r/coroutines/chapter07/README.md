# 7장. 코루틴 컨텍스트

## Potentially ambiguous 'kotlin.coroutine.coroutineContext' usage

- Reports usages of kotlin.coroutine.coroutineContext property in code that has a dependency on kotlinx.coroutines
  library.
- When both kotlin.coroutine.coroutineContext and kotlinx.coroutines.CoroutineScope.coroutineContext can be present in
  the code, it can lead to confusion and potential bugs.
- The kotlinx.coroutines library provides currentCoroutineContext() function as a clearer alternative that should be
  preferred even when there is no explicit clash.
- See the documentation for kotlin.coroutine.coroutineContext and kotlinx.coroutines.currentCoroutineContext for more
  details.

### Before

```kotlin
suspend fun getCurrentJob(): Job? {
    return coroutineContext[Job]
}
```

### After

```kotlin
suspend fun getCurrentJob(): Job? {
    return currentCoroutineContext()[Job]
}
```

### links

- https://www.jetbrains.com/help/inspectopedia/PreferCurrentCoroutineContextToCoroutineContext.html
