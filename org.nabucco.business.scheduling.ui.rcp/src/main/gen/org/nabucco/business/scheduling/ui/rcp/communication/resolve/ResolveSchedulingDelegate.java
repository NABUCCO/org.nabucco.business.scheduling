/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.business.scheduling.ui.rcp.communication.resolve;

import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.business.scheduling.facade.message.StaffingMsg;
import org.nabucco.business.scheduling.facade.service.resolve.ResolveScheduling;
import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * ResolveSchedulingDelegate<p/>Service for resolving schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class ResolveSchedulingDelegate extends ServiceDelegateSupport {

    private ResolveScheduling service;

    /**
     * Constructs a new ResolveSchedulingDelegate instance.
     *
     * @param service the ResolveScheduling.
     */
    public ResolveSchedulingDelegate(ResolveScheduling service) {
        super();
        this.service = service;
    }

    /**
     * ResolveScheduling.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the SchedulingMsg.
     * @return the SchedulingMsg.
     * @throws ClientException
     */
    public SchedulingMsg resolveScheduling(SchedulingMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<SchedulingMsg> request = new ServiceRequest<SchedulingMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<SchedulingMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveScheduling(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveScheduling.class, "resolveScheduling", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveScheduling.resolveScheduling");
    }

    /**
     * ResolveStaffing.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the StaffingMsg.
     * @return the StaffingMsg.
     * @throws ClientException
     */
    public StaffingMsg resolveStaffing(StaffingMsg message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<StaffingMsg> request = new ServiceRequest<StaffingMsg>(super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<StaffingMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.resolveStaffing(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(ResolveScheduling.class, "resolveStaffing", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: ResolveScheduling.resolveStaffing");
    }
}
