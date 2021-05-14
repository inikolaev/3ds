package org.github.inikolaev.threeds

import java.time.OffsetDateTime

enum class DeviceChannelType {
    APP, BROWSER
}

annotation class DeviceChannel(vararg val channels: DeviceChannelType)

enum class MessageCategoryType {
    PA, NPA
}

annotation class MessageCategory(vararg val categories: MessageCategoryType)

enum class ThreeDSCompInd {
    Y, N, U
}

data class ThreeDSRequestorAuthenticationInd(val value: String)

/**
 * Information about how the 3DS Requestor authenticated the cardholder before or during the transaction.
 * Refer to Table A.10 for data elements to include.
 */
data class ThreeDSRequestorAuthenticationInfo(
    val threeDSReqAuthData: String,
    val threeDSReqAuthMethod: String,
    val threeDSReqAuthTimestamp: OffsetDateTime,
)

/**
 * Value that represents the signature verification performed by the DS on the mechanism (e.g., FIDO)
 * used by the cardholder to authenticate to the 3DS Requestor.
 */
data class ThreeDSReqAuthMethodInd(val value: String)

/**
 * Indicates whether a challenge is requested for this transaction.
 *
 * For example:
 *   For 01-PA, a 3DS Requestor may have concerns about the transaction, and request a challenge.
 *   For 02-NPA, a challenge may be necessary when adding a new card to a wallet.
 *   For local/regional mandates or other variables.
 */
data class ThreeDSRequestorChallengeInd(val value: String)

/**
 * The AReq message is the initial message in the 3-D Secure authentication flow.
 * The 3DS Server forms the AReq message when requesting authentication of the Cardholder.
 * It can contain Cardholder, payment, and Device information for the transaction.
 * There is only one AReq message per authentication.
 */
data class AReq(
    @DeviceChannel(DeviceChannelType.BROWSER)
    @MessageCategory(MessageCategoryType.PA, MessageCategoryType.NPA)
    val threeDSCompInd: ThreeDSCompInd,

    @DeviceChannel(DeviceChannelType.APP)
    @MessageCategory(MessageCategoryType.PA, MessageCategoryType.NPA)
    val threeDSRequestorAppURL: String?,

    @DeviceChannel(DeviceChannelType.APP, DeviceChannelType.BROWSER)
    @MessageCategory(MessageCategoryType.PA, MessageCategoryType.NPA)
    val threeDSRequestorAuthenticationInd: ThreeDSRequestorAuthenticationInd,

    @DeviceChannel(DeviceChannelType.APP, DeviceChannelType.BROWSER)
    @MessageCategory(MessageCategoryType.PA, MessageCategoryType.NPA)
    val threeDSRequestorAuthenticationInfo: ThreeDSRequestorAuthenticationInfo?,

    @DeviceChannel(DeviceChannelType.APP, DeviceChannelType.BROWSER)
    @MessageCategory(MessageCategoryType.PA, MessageCategoryType.NPA)
    val threeDSReqAuthMethodInd: ThreeDSReqAuthMethodInd?,

    @DeviceChannel(DeviceChannelType.APP, DeviceChannelType.BROWSER)
    @MessageCategory(MessageCategoryType.PA, MessageCategoryType.NPA)
    val threeDSRequestorChallengeInd: ThreeDSRequestorChallengeInd?,

    val threeDSRequestorDecMaxTime: String,
    val threeDSRequestorDecReqInd: String,
    val threeDSRequestorID: String,
    val threeDSRequestorName: String,
    val threeDSRequestorPriorAuthenticationInfo: String,
    val threeDSRequestorURL: String,
    val threeDSServerRefNumber: String,
    val threeDSServerOperatorID: String,
    val threeDSServerTransID: String,
    val threeDSServerURL: String,
    val threeRIInd: String,
    val acctType: String,
    val acquirerBIN: String,
    val acquirerMerchantID: String,
    val addrMatch: String,
    val broadInfo: String,
    val browserAcceptHeader: String,
    val browserIP: String,
    val browserJavaEnabled: String,
    val browserJavascriptEnabled: String,
    val browserLanguage: String,
    val browserColorDepth: String,
    val browserScreenHeight: String,
    val browserScreenWidth: String,
    val browserTZ: String,
    val browserUserAgent: String,
    val cardExpiryDate: String,
    val acctInfo: String,
    val acctNumber: String,
    val acctID: String,
    val billAddrCity: String,
    val billAddrCountry: String,
    val billAddrLine1: String,
    val billAddrLine2: String,
    val billAddrLine3: String,
    val billAddrPostCode: String,
    val billAddrState: String,
    val email: String,
    val homePhone: String,
    val mobilePhone: String,
    val cardholderName: String,
    val shipAddrCity: String,
    val shipAddrCountry: String,
    val shipAddrLine1: String,
    val shipAddrLine2: String,
    val shipAddrLine3: String,
    val shipAddrPostCode: String,
    val shipAddrState: String,
    val workPhone: String,
    val deviceChannel: String,
    val deviceInfo: String,
    val deviceRenderOptions: String,
    val dsReferenceNumber: String,
    val dsTransID: String,
    val dsURL: String,
    val payTokenInd: String,
    val payTokenSource: String,
    val purchaseInstalData: String,
    val mcc: String,
    val merchantCountryCode: String,
    val merchantName: String,
    val merchantRiskIndicator: String,
    val messageCategory: String,
    val messageExtension: String,
    val messageType: String = "AReq",
    val messageVersion: String,
    val notificationURL: String,
    val purchaseAmount: String,
    val purchaseCurrency: String,
    val purchaseExponent: String,
    val purchaseDate: String,
    val recurringExpiry: String,
    val recurringFrequency: String,
    val sdkAppID: String,
    val sdkEncData: String,
    val sdkEphemPubKey: String,
    val sdkMaxTimeout: String,
    val sdkReferenceNumber: String,
    val sdkTransID: String,
    val transType: String,
    val whiteListStatus: String,
    val whiteListStatusSource: String,
)

/**
 * The ARes message is the Issuer’s ACS response to the AReq message.
 * It can indicate that the Cardholder has been authenticated,
 * or that further Cardholder interaction is required to complete the authentication.
 * There is only one ARes message per transaction.
 */
data class ARes(    
    val threeDSServerTransID: String,
    val acsChallengeMandated: String,
    val acsDecConInd: String,
    val acsOperatorID: String,    
    val acsReferenceNumber: String,
    val acsRenderingType: String,
    val acsSignedContent: String,
    val acsTransID: String,
    val acsURL: String,
    val authenticationType: String,
    val authenticationValue: String,
    val broadInfo: String,
    val cardholderInfo: String,
    val dsReferenceNumber: String,
    val dsTransID: String,
    val eci: String,
    val messageExtension: String,
    val messageType: String = "ARes",
    val messageVersion: String,
    val sdkTransID: String,
    val transStatus: String,
    val transStatusReason: String,
    val whiteListStatus: String,
    val whiteListStatusSource: String,
)

/**
 * The CReq message initiates Cardholder interaction in a Challenge Flow
 * and can be used to carry authentication data from the Cardholder.
 *
 * App-based
 * The CReq message is sent by the 3DS SDK. There are two or
 * more CReq messages per challenge as multiple back-and-forth attempts between the ACS
 * and the Cardholder may be required to complete the authentication.
 *
 * Browser-based
 * The CReq message is sent by the 3DS Server. There is only one CReq message per challenge.
 */
data class CReq(
    val threeDSRequestorAppURL: String,
    val threeDSServerTransID: String,
    val acsTransID: String,
    val challengeCancel: String,
    val challengeDataEntry: String,
    val challengeHTMLDataEntry: String,
    val challengeNoEntry: String,
    val challengeWindowSize: String,
    val messageExtension: String,
    val messageType: String = "CReq",
    val messageVersion: String,
    val oobContinue: String,
    val resendChallenge: String,
    val sdkTransID: String,
    val sdkCounterStoA: String,
    val whitelistingDataEntry: String,
)

/**
 * The CRes message is the ACS response to the CReq message.
 * It can indicate the result of the Cardholder authentication or, in the case of an App-based model,
 * also signal that further Cardholder interaction is required to complete the authentication.
 * App-based
 * Elements of the CRes message provide the necessary data for the 3DS SDK to generate
 * and display the user interface (UI) for the challenge.
 * There are two or more CRes messages per transaction to complete Cardholder authentication.
 *
 * Browser-based
 * The CRes message contains the authentication result and completes the Cardholder challenge.
 * There is only one CRes message per challenge.
 */
data class CRes(
    val threeDSServerTransID: String,
    val acsCounterAtoS: String,
    val acsTransID: String,
    val acsHTML: String,
    val acsUiType: String,
    val challengeCompletionInd: String,
    val challengeInfoHeader: String,
    val challengeInfoLabel: String,
    val challengeInfoText: String,
    val challengeInfoTextIndicator: String,
    val challengeSelectInfo: String,
    val expandInfoLabel: String,
    val expandInfoText: String,
    val issuerImage: String,
    val messageExtension: String,
    val messageType: String = "CRes",
    val messageVersion: String,
    val oobAppURL: String,
    val oobAppLabel: String,
    val oobContinueLabel: String,
    val psImage: String,
    val resendInformationLabel: String,
    val sdkTransID: String,
    val submitAuthenticationLabel: String,
    val transStatus: String,
    val whitelistingInfoText: String,
    val whyInfoLabel: String,
    val whyInfoText: String,
)

/**
 * The RReq message communicates the results of the authentication or verification.
 * The message is sent by the ACS through the DS to the 3DS Server.
 * There is only one RReq message per AReq message.
 * The RReq message is not present in a Frictionless transaction.
 */
data class RReq(
    val threeDSServerTransID: String,
    val acsTransID: String,
    val acsRenderingType: String,
    val authenticationMethod: String,
    val authenticationType: String,
    val authenticationValue: String,
    val challengeCancel: String,
    val dsTransID: String,
    val eci: String,
    val interactionCounter: String,
    val messageCategory: String,
    val messageExtension: String,
    val messageType: String = "RReq",
    val messageVersion: String,
    val sdkTransID: String,
    val transStatus: String,
    val transStatusReason: String,
    val whiteListStatus: String,
    val whiteListStatusSource: String,
)

/**
 * The RRes message acknowledges receipt of the RReq message.
 * The message is sent by the 3DS Server through the DS to the ACS.
 * There is only one RRes message per RReq message.
 */

data class RRes(
    val threeDSServerTransID: String,
    val acsTransID: String,
    val dsTransID: String,
    val messageExtension: String,
    val messageType: String = "RRes",
    val messageVersion: String,
    val resultsStatus: String,
    val sdkTransID: String,
)

/**
 * PReq/PRes
 */

enum class ActionInd(val value: String) {
    ADD("A"),
    DELETE("D"),
    MODIFY("M")
}

data class CardRange(
    val acsEndProtocolVersion: String,
    val acsInfoInd: Array<String>? = null,
    val acsStartProtocolVersion: String,
    val actionInd: ActionInd?,
    val dsEndProtocolVersion: String? = null,
    val dsStartProtocolVersion: String? = null,
    val endRange: String,
    val startRange: String,
    val threeDSMethodURL: String? = null,
)

/**
 * The PReq message is sent from the 3DS Server to the DS to request information about the ACSs and the DS.
 * This message is not part of the 3-D Secure authentication message flow.
 */
data class PReq(
    val threeDSServerRefNumber: String,
    val threeDSServerOperatorID: String? = null,
    val threeDSServerTransID: String,
    val messageExtension: String? = null,
    val messageType: String = "PReq",
    val messageVersion: String,
    val serialNum: String? = null,
)

/**
 * The PRes message is the DS response to the PReq message.
 * The 3DS Server can utilise the PRes message to cache information about the ACSs
 * and the DS (for example, about which Protocol Version(s) are supported).
 * This message is not part of the 3-D Secure authentication message flow.
 */
data class PRes(
    val threeDSServerTransID: String,
    val cardRangeData: List<CardRange>? = null,
    val dsEndProtocolVersion: String,
    val dsStartProtocolVersion: String,
    val dsTransID: String,
    val messageExtension: String? = null,
    val messageType: String = "PRes",
    val messageVersion: String,
    val serialNum: String? = null,
)

enum class ErrorComponent {
    C, // 3DS SDK
    S, // 3DS Server
    D, // DS
    A, // ACS
}

data class Erro(
    val threeDSServerTransID: String? = null,
    val acsTransID: String? = null,
    val dsTransID: String? = null,
    val errorCode: String,
    val errorComponent: ErrorComponent,
    val errorDescription: String,
    val errorDetail: String,
    val errorMessageType: String? = null,
    val messageType: String,
    val messageVersion: String,
    val sdkTransID: String? = null,
)


/**
 * These are part of 3DS 1.0 and uses XML instead of JSON
 *
 */

/**
 * Payer Authentication, equivalent to CReq/CRes + RReq/RRes
 */
data class PAReq(val placeholder: String? = null)
data class PARes(val placeholder: String? = null)

/**
 * Verify Enrolment, equivalent to AReq/ARes
 */
data class VEReq(val placeholder: String? = null)
data class VERes(val placeholder: String? = null)