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
package digitaltwin.tcpprotocol.server;

import eapli.base.AGV.application.GetAGVInformation;
import eapli.base.AGV.domain.AGV;

/**
 * @author Paulo Gandra Sousa 01/06/2020
 */
public class GetAGVInfoRequest extends DigitalTwinProtocolRequest {

    private final String agvId;

    public GetAGVInfoRequest(String inputRequest, String agvId) {
        super(new GetAGVInformation(), inputRequest);
        this.agvId = agvId;
    }

    @Override
    public String execute() {

        try {
            return buildResponse();
        } catch (final Exception e) {
            // we should be careful about exposing the Exception to the outside!
            return buildServerError(e.getMessage());
        }
    }

    public String buildResponse() {
        AGV agv = controller.getAGVsInformationForDashBoard(agvId);

        String info = agv.getAgvId().getAGVId() + "," + agv.getAgvPosition().getAgvPosition() + "," + agv.getAgvStatus().obtainStatus().name()+ ",";

        return info;
    }
}
