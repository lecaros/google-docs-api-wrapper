package com.merkenlabs.googleapiwrapper.docs

import com.google.api.services.docs.v1.Docs

interface IDocsServiceWrapper {
    fun replaceTextInFile(fileId: String, oldText: String, newText: String)
    fun replaceAllTextsInFile(fileId: String, pairsToReplace: Map<String, String>)
    fun getDocsService(): Docs
}
