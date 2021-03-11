package com.merkenlabs.googleapiwrapper.docs

import com.google.api.services.docs.v1.Docs
import com.google.api.services.docs.v1.model.Document

interface IDocsServiceWrapper {
    fun replaceTextInFile(fileId: String, oldText: String, newText: String)
    fun replaceAllTextsInFile(fileId: String, pairsToReplace: Map<String, String>)
    fun replaceImage(fileId: String, imageObjectId: String, newImageUri: String)
    fun getDocumentById(fileId: String): Document
    fun getDocsService(): Docs
}
