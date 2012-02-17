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
package org.nabucco.business.scheduling.ui.rcp.communication.search;

import org.nabucco.business.scheduling.facade.message.SchedulingListMsg;
import org.nabucco.business.scheduling.facade.message.StaffingListMsg;
import org.nabucco.business.scheduling.facade.message.search.SchedulingSearchRq;
import org.nabucco.business.scheduling.facade.message.search.StaffingSearchRq;
import org.nabucco.business.scheduling.facade.service.search.SearchScheduling;
import org.nabucco.framework.base.facade.datatype.NabuccoSystem;
import org.nabucco.framework.base.facade.datatype.context.ServiceSubContext;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.plugin.base.component.communication.ServiceDelegateSupport;

/**
 * SearchSchedulingDelegate<p/>Service for searching schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class SearchSchedulingDelegate extends ServiceDelegateSupport {

    private SearchScheduling service;

    /**
     * Constructs a new SearchSchedulingDelegate instance.
     *
     * @param service the SearchScheduling.
     */
    public SearchSchedulingDelegate(SearchScheduling service) {
        super();
        this.service = service;
    }

    /**
     * SearchScheduling.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the SchedulingSearchRq.
     * @return the SchedulingListMsg.
     * @throws ClientException
     */
    public SchedulingListMsg searchScheduling(SchedulingSearchRq message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<SchedulingSearchRq> request = new ServiceRequest<SchedulingSearchRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<SchedulingListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchScheduling(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchScheduling.class, "searchScheduling", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchScheduling.searchScheduling");
    }

    /**
     * SearchStaffing.
     *
     * @param subContexts the ServiceSubContext....
     * @param message the StaffingSearchRq.
     * @return the StaffingListMsg.
     * @throws ClientException
     */
    public StaffingListMsg searchStaffing(StaffingSearchRq message, ServiceSubContext... subContexts)
            throws ClientException {
        ServiceRequest<StaffingSearchRq> request = new ServiceRequest<StaffingSearchRq>(
                super.createServiceContext(subContexts));
        request.setRequestMessage(message);
        ServiceResponse<StaffingListMsg> response = null;
        Exception exception = null;
        if ((service != null)) {
            super.handleRequest(request);
            long start = NabuccoSystem.getCurrentTimeMillis();
            try {
                response = service.searchStaffing(request);
            } catch (Exception e) {
                exception = e;
            } finally {
                long end = NabuccoSystem.getCurrentTimeMillis();
                long duration = (end - start);
                super.monitorResult(SearchScheduling.class, "searchStaffing", duration, exception);
            }
            if ((response != null)) {
                super.handleResponse(response);
                return response.getResponseMessage();
            }
        }
        throw new ClientException("Cannot execute service operation: SearchScheduling.searchStaffing");
    }
}
