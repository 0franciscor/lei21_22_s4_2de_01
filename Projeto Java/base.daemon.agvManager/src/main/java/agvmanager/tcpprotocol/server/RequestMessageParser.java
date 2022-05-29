/*
 * Copyright (c) 2013-2022 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package agvmanager.tcpprotocol.server;

import eapli.base.AGV.application.CallAGVManagerController;
import eapli.framework.csv.util.CsvLineMarshaler;
import eapli.framework.util.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;

/**
 * The message parser for the Booking protocol.
 *
 * @author Paulo Gandra Sousa 01/06/2020
 */
@Utility
public class RequestMessageParser {

    private static final Logger LOGGER = LogManager.getLogger(RequestMessageParser.class);

    public RequestMessageParser() {}

    /**
     * Parse and build the request.
     *
     * @param inputLine
     *
     * @return
     */
    public AGVManagerProtocolRequest parse(final String inputLine) {
        // as a fallback make sure we return unknown
        AGVManagerProtocolRequest request = null;

        // parse to determine which type of request and if it is sintactally valid
        String[] tokens;
        try {
            tokens = CsvLineMarshaler.tokenize(inputLine).toArray(new String[0]);
            if (String.valueOf(CallAGVManagerController.CALL_FIFO).equals(tokens[1]))
                request = callTaskAssignment(inputLine, tokens);
            if (String.valueOf(CallAGVManagerController.DASHBOARD_REQUEST).equals(tokens[1]))
                request = parseGetAGVInformations(inputLine, tokens);
        } catch (final ParseException e) {
            LOGGER.info("Unable to parse request: {}", inputLine);
            request = new BadRequest(inputLine, "Unable to parse request");
        }
        return request;
    }

    private AGVManagerProtocolRequest callTaskAssignment(final String inputLine, final String[] tokens) {
        AGVManagerProtocolRequest request;
        if (tokens.length != 3) {
            request = new BadRequest(inputLine, "Wrong number of parameters");
        } else{
            request = new AssignTasksRequest(inputLine);
        }
        return request;
    }

    private AGVManagerProtocolRequest parseGetAGVInformations(final String inputLine, final String[] tokens) {
        AGVManagerProtocolRequest request;
        if (tokens.length != 4) {
            request = new ErrorInRequest(inputLine, "Wrong number of parameters");

        } else if (tokens[3].isBlank()) {
            request = new ErrorInRequest(inputLine, "File name must be Pointed");

        } else {
            request = new DashboardRequest(inputLine, tokens[3]);
        }

        return request;
    }
}