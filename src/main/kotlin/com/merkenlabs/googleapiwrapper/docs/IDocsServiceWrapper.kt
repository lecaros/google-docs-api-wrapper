package com.merkenlabs.googleapiwrapper.docs

import com.google.api.services.docs.v1.Docs
import com.google.api.services.docs.v1.model.Document
import com.google.api.services.docs.v1.model.Location
import com.google.api.services.docs.v1.model.Size

interface IDocsServiceWrapper {
    fun replaceTextInFile(fileId: String, oldText: String, newText: String)
    fun replaceAllTextsInFile(fileId: String, pairsToReplace: Map<String, String>)
    fun replaceImage(fileId: String, imageObjectId: String, newImageUri: String)
    fun getDocumentById(fileId: String): Document
    fun getDocsService(): Docs
    fun insertImage(fileId: String, newImageUri: String, location: Location, size: Size?)
}
