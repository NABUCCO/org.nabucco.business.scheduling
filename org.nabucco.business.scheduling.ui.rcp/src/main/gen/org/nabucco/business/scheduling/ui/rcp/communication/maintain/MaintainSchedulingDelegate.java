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
package org.nabucco.business.scheduling.ui.rcp.communication.maintain;

import org.nabucco.business.scheduling.facade.message.SchedulingListMsg;
import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.business.scheduling.facade.message.StaffingMsg;
import org.nabucco.business.scheduling.facade.service.maintain.MaintainScheduling;
import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * MaintainSchedulingDelegate<p/>Service for maintaining scheduling datatypes.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-10-04
 */
public class MaintainSchedulingDelegate extends ServiceDelegateSupport {

    private MaintainScheduling service;

    /**
     * Constructs a new MaintainSchedulingDelegate instance.
     *
     * @param service the MaintainScheduling.
     */
    public MaintainSchedulingDelegate(MaintainScheduling service) {
        super();
        this.service = service;
    }

    /**
     * MaintainScheduling.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the SchedulingMsg.
     * @return the SchedulingMsg.
     * @throws ClientException
     */
    public SchedulingMsg maintainScheduling(SchedulingMsg message, ServiceSubContext... subContexts)
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
                response = service.maintainScheduling(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainScheduling.class, "maintainScheduling", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainScheduling.maintainScheduling");
    }

    /**
     * MaintainSchedulingList.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the SchedulingListMsg.
     * @return the SchedulingListMsg.
     * @throws ClientException
     */
    public SchedulingListMsg maintainSchedulingList(SchedulingListMsg message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<SchedulingListMsg> request = new ServiceRequest<SchedulingListMsg>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<SchedulingListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainSchedulingList(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainScheduling.class, "maintainSchedulingList", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainScheduling.maintainSchedulingList");
    }

    /**
     * MaintainStaffing.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the StaffingMsg.
     * @return the StaffingMsg.
     * @throws ClientException
     */
    public StaffingMsg maintainStaffing(StaffingMsg message, ServiceSubContext... subContexts) throws ClientException {
        ServiceRequest<StaffingMsg> request = new ServiceRequest<StaffingMsg>(super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<StaffingMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.maintainStaffing(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(MaintainScheduling.class, "maintainStaffing", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: MaintainScheduling.maintainStaffing");
    }
}
