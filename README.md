# Google Docs Api Wrapper

This is a wrapper around the Google Docs (google-api-services-docs v1).

## Disclaimer :)
This project was started and is modified according to the needs I'm experiencing, in the hope that someone else will find it helpful.  
For any request please open an issue.

## How to use

Extend class AbstractDocsServiceWrapper and implement required methods. For example:
```kotlin
override fun getDocsService(): Docs {
    return Docs.Builder(httpTransport, jsonFactory, loadCredential())
            .setApplicationName(applicationProperties.name).build()
}
 ```

## Current features
* Replace text in a file.
* Replaces a list of texts in a file.
* Replace an inline image in a document.
* Insert an inline image in a document.

## To-Do
* Read responses to control execution.

## Versions
### 0.1.0
* Replace text in a file.
* Replaces a list of texts in a file.
* Replace an inline image in a document.
* Insert an inline image in a document.
