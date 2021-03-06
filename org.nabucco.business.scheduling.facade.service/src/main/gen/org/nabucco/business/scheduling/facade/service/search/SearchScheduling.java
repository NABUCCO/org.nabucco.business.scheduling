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
package org.nabucco.business.scheduling.facade.service.search;

import org.nabucco.business.scheduling.facade.message.SchedulingListMsg;
import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.business.scheduling.facade.message.StaffingListMsg;
import org.nabucco.business.scheduling.facade.message.search.SchedulingByStaffingSearchRq;
import org.nabucco.business.scheduling.facade.message.search.SchedulingSearchRq;
import org.nabucco.business.scheduling.facade.message.search.StaffingSearchRq;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;

/**
 * SearchScheduling<p/>Service for searching schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public interface SearchScheduling extends Service {

    /**
     * Searches schedulings for the given request parameter.
     *
     * @param rq the ServiceRequest<SchedulingSearchRq>.
     * @return the ServiceResponse<SchedulingListMsg>.
     * @throws SearchException
     */
    ServiceResponse<SchedulingListMsg> searchScheduling(ServiceRequest<SchedulingSearchRq> rq) throws SearchException;

    /**
     * Searches scheduling that references the staffing with given id
     *
     * @param rq the ServiceRequest<SchedulingByStaffingSearchRq>.
     * @return the ServiceResponse<SchedulingMsg>.
     * @throws SearchException
     */
    ServiceResponse<SchedulingMsg> searchSchedulingByStaffing(ServiceRequest<SchedulingByStaffingSearchRq> rq)
            throws SearchException;

    /**
     * Searches staffings for the given request parameter.
     *
     * @param rq the ServiceRequest<StaffingSearchRq>.
     * @return the ServiceResponse<StaffingListMsg>.
     * @throws SearchException
     */
    ServiceResponse<StaffingListMsg> searchStaffing(ServiceRequest<StaffingSearchRq> rq) throws SearchException;
}
