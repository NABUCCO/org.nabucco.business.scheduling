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

import org.nabucco.business.scheduling.facade.datatype.Staffing;
import org.nabucco.business.scheduling.facade.message.StaffingListMsg;
import org.nabucco.business.scheduling.facade.message.produce.StaffingPrototypeRq;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.datatype.Tenant;
import org.nabucco.framework.base.facade.exception.service.ProduceException;

/**
 * ProduceStaffingsServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ProduceStaffingsServiceHandlerImpl extends ProduceStaffingsServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected StaffingListMsg produceStaffings(StaffingPrototypeRq msg) throws ProduceException {

        StaffingListMsg rs = new StaffingListMsg();

        if (msg.getAmount() == null || msg.getAmount().getValue() == null) {
            return rs;
        }

        for (int i = 0; i < msg.getAmount().getValue(); i++) {
            Staffing staffing = new Staffing();
            staffing.setDatatypeState(DatatypeState.INITIALIZED);
            staffing.setSummary("New Staffing");
            staffing.setTenant(new Tenant());
            rs.getStaffingList().add(staffing);
        }

        return rs;
    }

}
