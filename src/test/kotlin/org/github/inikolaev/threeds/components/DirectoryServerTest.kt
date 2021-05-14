package org.github.inikolaev.threeds.components

import io.mockk.mockk
import org.github.inikolaev.threeds.AccessControlServer
import org.github.inikolaev.threeds.ActionInd
import org.github.inikolaev.threeds.CardRange
import org.github.inikolaev.threeds.DirectoryServer
import org.github.inikolaev.threeds.PReq
import org.github.inikolaev.threeds.PRes
import org.github.inikolaev.threeds.ThreeDSServer
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.*

class DirectoryServerTest {
    private val threeDSServer = mockk<ThreeDSServer>()
    private val accessControlServer = mockk<AccessControlServer>()
    private val directoryServer = DirectoryServer(threeDSServer, accessControlServer)
    private val cardRangeData = listOf(
        CardRange(
            acsEndProtocolVersion = "2.2.0",
            acsStartProtocolVersion = "2.1.0",
            actionInd = ActionInd.ADD,
            dsEndProtocolVersion = "2.2.0",
            dsStartProtocolVersion = "2.1.0",
            endRange = "4012000000001999",
            startRange = "4012000000001000",
        ),
        CardRange(
            acsEndProtocolVersion = "2.2.0",
            acsStartProtocolVersion = "2.1.0",
            actionInd = ActionInd.ADD,
            dsEndProtocolVersion = "2.2.0",
            dsStartProtocolVersion = "2.1.0",
            endRange = "4012000000002999",
            startRange = "4012000000002000",
        ),
        CardRange(
            acsEndProtocolVersion = "2.2.0",
            acsStartProtocolVersion = "2.1.0",
            actionInd = ActionInd.ADD,
            dsEndProtocolVersion = "2.2.0",
            dsStartProtocolVersion = "2.1.0",
            endRange = "4012000000003999",
            startRange = "4012000000003000",
        ),
        CardRange(
            acsEndProtocolVersion = "2.2.0",
            acsStartProtocolVersion = "2.1.0",
            actionInd = ActionInd.ADD,
            dsEndProtocolVersion = "2.2.0",
            dsStartProtocolVersion = "2.1.0",
            endRange = "4012000000004999",
            startRange = "4012000000004000",
        ),
        CardRange(
            acsEndProtocolVersion = "2.2.0",
            acsStartProtocolVersion = "2.1.0",
            actionInd = ActionInd.ADD,
            dsEndProtocolVersion = "2.2.0",
            dsStartProtocolVersion = "2.1.0",
            endRange = "4012000000005999",
            startRange = "4012000000005000",
        ),
    )

    @Test
    fun shouldReturnAllAccessControlServersIfSerialNumberNotProvided() {
        cardRangeData.forEach(directoryServer::addCardRange)

        val threeDSServerTransID = UUID.randomUUID().toString()
        val preq = PReq(
            threeDSServerRefNumber = "3DS_SERVER_001",
            threeDSServerTransID = threeDSServerTransID,
            messageVersion = "2.1.0",
        )
        val pres = directoryServer.process(preq)

        assertEquals(PRes(
            threeDSServerTransID = threeDSServerTransID,
            cardRangeData = cardRangeData,
            dsEndProtocolVersion = "2.1.0",
            dsStartProtocolVersion = "2.1.0",
            dsTransID = pres.dsTransID,
            messageVersion = "2.1.0"
        ), pres)
    }

    @Test
    fun shouldReturnAllAccessControlServersIfSerialNumberIsProvided() {
        cardRangeData.forEach(directoryServer::addCardRange)

        val threeDSServerTransID = UUID.randomUUID().toString()
        val preq = PReq(
            threeDSServerRefNumber = "3DS_SERVER_001",
            threeDSServerTransID = threeDSServerTransID,
            messageVersion = "2.1.0",
            serialNum = "1",
        )
        val pres = directoryServer.process(preq)

        assertEquals(PRes(
            threeDSServerTransID = threeDSServerTransID,
            cardRangeData = cardRangeData,
            dsEndProtocolVersion = "2.1.0",
            dsStartProtocolVersion = "2.1.0",
            dsTransID = pres.dsTransID,
            messageVersion = "2.1.0"
        ), pres)
    }
}