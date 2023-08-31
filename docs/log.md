# logger

## levels

1. OFF   -> for prod
2. ERROR -> for prod
3. WARN
4. INFO
5. DEBUG -> for dev
6. TRACE -> for dev

Example:

```kotlin
logger.error("Error message")
logger.warn("Warning message")
logger.info("Info message")
logger.debug("Debug message")
logger.trace("Trace message")
```