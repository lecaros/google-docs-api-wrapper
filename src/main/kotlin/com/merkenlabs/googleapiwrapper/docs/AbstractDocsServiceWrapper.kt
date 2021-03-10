package com.merkenlabs.googleapiwrapper.docs

import com.google.api.services.docs.v1.model.BatchUpdateDocumentRequest
import com.google.api.services.docs.v1.model.ReplaceAllTextRequest
import com.google.api.services.docs.v1.model.Request
import com.google.api.services.docs.v1.model.SubstringMatchCriteria

abstract class AbstractDocsServiceWrapper : IDocsServiceWrapper {
    override fun replaceTextInFile(fileId: String, oldText: String, newText: String) {
        val request = createReplaceTextRequest(oldText, newText)
        val body = BatchUpdateDocumentRequest()
        val results =
            getDocsService().documents().batchUpdate(fileId, body.setRequests(mutableListOf(request))).execute()

        val occurrenceschanged = results.replies[0].replaceAllText.occurrencesChanged
    }

    override fun replaceAllTextsInFile(fileId: String, pairsToReplace: Map<String, String>) {
        val requests = createReplaceTextRequest(pairsToReplace)

        val bodyPlural = BatchUpdateDocumentRequest()
        val result =
            getDocsService().documents().batchUpdate(fileId, bodyPlural.setRequests(requests)).execute()
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
