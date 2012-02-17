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
package org.nabucco.business.scheduling.facade.service.produce;

import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.business.scheduling.facade.message.StaffingListMsg;
import org.nabucco.business.scheduling.facade.message.produce.SchedulingPrototypeRq;
import org.nabucco.business.scheduling.facade.message.produce.StaffingPrototypeRq;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.Service;

/**
 * ProduceScheduling<p/>Service for producing schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public interface ProduceScheduling extends Service {

    /**
     * Creates a new scheduling instance.
     *
     * @param rq the ServiceRequest<SchedulingPrototypeRq>.
     * @return the ServiceResponse<SchedulingMsg>.
     * @throws ProduceException
     */
    ServiceResponse<SchedulingMsg> produceScheduling(ServiceRequest<SchedulingPrototypeRq> rq) throws ProduceException;

    /**
     * Creates a list of new staffing instances.
     *
     * @param rq the ServiceRequest<StaffingPrototypeRq>.
     * @return the ServiceResponse<StaffingListMsg>.
     * @throws ProduceException
     */
    ServiceResponse<StaffingListMsg> produceStaffings(ServiceRequest<StaffingPrototypeRq> rq) throws ProduceException;
}
