/*
 * Copyright 2012 PRODYNA AG
 *
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nabucco.business.scheduling.ui.rcp.communication.produce;

import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.business.scheduling.facade.message.StaffingListMsg;
import org.nabucco.business.scheduling.facade.message.produce.SchedulingPrototypeRq;
import org.nabucco.business.scheduling.facade.message.produce.StaffingPrototypeRq;
import org.nabucco.business.scheduling.facade.service.produce.ProduceScheduling;
import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * ProduceSchedulingDelegate<p/>Service for producing schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class ProduceSchedulingDelegate extends ServiceDelegateSupport {

    private ProduceScheduling service;

    /**
     * Constructs a new ProduceSchedulingDelegate instance.
     *
     * @param service the ProduceScheduling.
     */
    public ProduceSchedulingDelegate(ProduceScheduling service) {
        super();
        this.service = service;
    }

    /**
     * ProduceScheduling.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the SchedulingPrototypeRq.
     * @return the SchedulingMsg.
     * @throws ClientException
     */
    public SchedulingMsg produceScheduling(SchedulingPrototypeRq message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<SchedulingPrototypeRq> request = new ServiceRequest<SchedulingPrototypeRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<SchedulingMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceScheduling(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceScheduling.class, "produceScheduling", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ProduceScheduling.produceScheduling");
    }

    /**
     * ProduceStaffings.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the StaffingPrototypeRq.
     * @return the StaffingListMsg.
     * @throws ClientException
     */
    public StaffingListMsg produceStaffings(StaffingPrototypeRq message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<StaffingPrototypeRq> request = new ServiceRequest<StaffingPrototypeRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<StaffingListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.produceStaffings(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ProduceScheduling.class, "produceStaffings", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ProduceScheduling.produceStaffings");
    }
}
