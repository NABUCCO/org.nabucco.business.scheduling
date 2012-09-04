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
package org.nabucco.business.scheduling.facade.service.resolve;

import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.business.scheduling.facade.message.StaffingMsg;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;

/**
 * ResolveScheduling<p/>Service for resolving schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public interface ResolveScheduling extends Service {

    /**
     * Resolves a single scheduling.
     *
     * @param rq the ServiceRequest<SchedulingMsg>.
     * @return the ServiceResponse<SchedulingMsg>.
     * @throws ResolveException
     */
    ServiceResponse<SchedulingMsg> resolveScheduling(ServiceRequest<SchedulingMsg> rq) throws ResolveException;

    /**
     * Resolves a single staffing.
     *
     * @param rq the ServiceRequest<StaffingMsg>.
     * @return the ServiceResponse<StaffingMsg>.
     * @throws ResolveException
     */
    ServiceResponse<StaffingMsg> resolveStaffing(ServiceRequest<StaffingMsg> rq) throws ResolveException;
}
