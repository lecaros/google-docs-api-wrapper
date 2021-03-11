package com.merkenlabs.googleapiwrapper.docs

import com.google.api.services.docs.v1.model.*


abstract class AbstractDocsServiceWrapper : IDocsServiceWrapper {
    override fun replaceTextInFile(fileId: String, oldText: String, newText: String) {
        val request = createReplaceTextRequest(oldText, newText)
        val body = getBodyWithRequests(request)
        val results =
            getDocsService().documents().batchUpdate(fileId, body).execute()

        val occurrenceschanged = results.replies[0].replaceAllText.occurrencesChanged
    }

    override fun replaceAllTextsInFile(fileId: String, pairsToReplace: Map<String, String>) {
        val requests = createReplaceTextRequest(pairsToReplace)

        val body = getBodyWithRequests(requests)
        val result =
            getDocsService().documents().batchUpdate(fileId, body).execute()
    }

    override fun replaceImage(fileId: String, imageObjectId: String, newImageUri: String) {
        val request = createReplaceImageRequest(imageObjectId, newImageUri)
        val body = getBodyWithRequests(request)
        val result = getDocsService().documents().batchUpdate(fileId, body).execute()
    }

    private fun getBodyWithRequests(requests: List<Request>): BatchUpdateDocumentRequest {
        return BatchUpdateDocumentRequest().setRequests(requests)
    }

    private fun getBodyWithRequests(request: Request): BatchUpdateDocumentRequest {
        return getBodyWithRequests(listOf(request))
    }

    private fun createReplaceImageRequest(imageObjectId: String, newImageUri: String): Request {
        return Request().setReplaceImage(
            ReplaceImageRequest()
                .setImageObjectId(imageObjectId)
                .setUri(newImageUri)
        )
    }

    private fun createReplaceTextRequest(textToReplace: String, newText: String, matchCase: Boolean = true): Request {
        return Request()
            .setReplaceAllText(
                ReplaceAllTextRequest()
                    .setContainsText(
                        SubstringMatchCriteria()
                            .setText(textToReplace)
                            .setMatchCase(matchCase)
                    )
                    .setReplaceText(newText)
            )
    }

    private fun createReplaceTextRequest(pairsToReplace: Map<String, String>): List<Request> {
        val requests = mutableListOf<Request>()
        for (pair in pairsToReplace) {
            requests.add(createReplaceTextRequest(pair.key, pair.value))
        }
        return requests
    }
}
