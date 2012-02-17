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
package org.nabucco.business.scheduling.impl.service.produce;

import java.math.BigDecimal;
import java.util.Date;

import org.nabucco.business.scheduling.facade.datatype.Scheduling;
import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.business.scheduling.facade.message.produce.SchedulingPrototypeRq;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.service.ProduceException;

/**
 * ProduceSchedulingServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ProduceSchedulingServiceHandlerImpl extends ProduceSchedulingServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected SchedulingMsg produceScheduling(SchedulingPrototypeRq rq) throws ProduceException {

        Scheduling scheduling = new Scheduling();
        scheduling.setDatatypeState(DatatypeState.INITIALIZED);

        if (rq.getName() != null) {
            scheduling.setName(rq.getName());
            scheduling.setDescription("Description of " + rq.getName().getValue());
        }

        scheduling.setOwner(super.getContext().getOwner());
        scheduling.setTenant(super.getContext().getTenant());
        scheduling.setCreator(super.getContext().getUserId());

        scheduling.setStartDate(new Date());
        scheduling.setOnsite(new BigDecimal(0));

        SchedulingMsg rs = new SchedulingMsg();
        rs.setScheduling(scheduling);

        return rs;
    }

}
