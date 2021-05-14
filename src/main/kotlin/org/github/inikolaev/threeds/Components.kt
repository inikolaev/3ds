package org.github.inikolaev.threeds

import java.lang.RuntimeException

class DirectoryServer(val threeDSServer: ThreeDSServer, val accessControlServer: AccessControlServer) {
    fun process(request: AReq): ARes {
        println("Processing AReq: $request")
        return accessControlServer.process(request)
    }

    fun process(request: RReq): RRes {
        println("Processing RReq: $request")
        return threeDSServer.process(request)
    }
}

class AccessControlServer(val directoryServer: DirectoryServer) {
    fun process(request: AReq): ARes {
        println("Processing AReq: $request")
        TODO()
    }

    fun process(request: CReq): CRes {
        println("Processing CReq: $request")
        val resultsRequest = RReq()
        val resultsResponse = directoryServer.process(resultsRequest)
        val challengeResponse = CRes()

        return challengeResponse
    }
}

enum class PaymentStatus {
    AUTHENTICATED, NOT_AUTHENTICATED, REDIRECT, IDENTIFY, CHALLENGE
}

data class PaymentRequest(val amount: Int)
data class PaymentResponse(val status: PaymentStatus)

class ThreeDSServer(val directoryServer: DirectoryServer) {
    fun process(paymentRequest: PaymentRequest): PaymentResponse {
        val authorizationRequest = AReq()
        val authorizationResponse = directoryServer.process(authorizationRequest)
        val paymentResponse = PaymentResponse()

        return paymentResponse
    }

    fun process(request: RReq): RRes {
        TODO()
    }
}

class Requestor(val threeDSServer: ThreeDSServer, val threeDSSDK: ThreeDSSDK) {
    fun authenticate() {
        val paymentRequest = PaymentRequest(10)
        val paymentResponse = threeDSServer.process(paymentRequest)

        when (paymentResponse.status) {
            PaymentStatus.CHALLENGE -> challenge()
            else -> throw RuntimeException("Failed to handle ${paymentResponse.status}")
        }
    }

    fun challenge() {
        println("Sending first challenge request")
        val challengeRequest1 = ChallengeRequest()
        val challengeResponse1 = threeDSSDK.challenge(challengeRequest1)

        // Display challenge or failure
        println("Sending first challenge request")

        // Submit challenge response
        println("Sending second challenge request")
        val challengeRequest2 = ChallengeRequest()
        val challengeResponse2 = threeDSSDK.challenge(challengeRequest2)

        // Processing challenge result
    }
}

data class ChallengeRequest()
data class ChallengeResponse()

class ThreeDSSDK(val accessControlServer: AccessControlServer) {
    fun challenge(challengeRequest: ChallengeRequest): ChallengeResponse {
        println("Processing challenge request: $challengeRequest")
        val creq = CReq()
        val cres = accessControlServer.process(creq)
        val challengeResponse = ChallengeResponse()
        return challengeResponse
    }
}