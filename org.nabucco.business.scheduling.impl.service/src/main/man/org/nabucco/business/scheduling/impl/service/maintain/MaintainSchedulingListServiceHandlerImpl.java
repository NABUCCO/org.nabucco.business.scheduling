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
package org.nabucco.business.scheduling.impl.service.maintain;

import org.nabucco.business.scheduling.facade.datatype.Scheduling;
import org.nabucco.business.scheduling.facade.message.SchedulingListMsg;
import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.framework.base.facade.datatype.DatatypeState;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.MaintainException;

/**
 * MaintainSchedulingListServiceHandlerImpl
 * 
 * @author Leonid Agranovskiy, PRODYNA AG
 */
public class MaintainSchedulingListServiceHandlerImpl extends MaintainSchedulingListServiceHandler {

    private static final long serialVersionUID = 1L;

    protected SchedulingMsg maintainScheduling(SchedulingMsg msg) throws MaintainException {

        if (msg.getScheduling() == null) {
            throw new MaintainException("Error maintaining scheduling '" + msg.getScheduling().getName() + "'.");
        }

        SchedulingMsg rs = new SchedulingMsg();

        try {
            Scheduling scheduling = super.getPersistenceManager().persist(msg.getScheduling());
            scheduling.getStaffList().size();
            rs.setScheduling(scheduling);

        } catch (PersistenceException pe) {
            super.getLogger().error(pe, "Error maintaining scheduling '", msg.getScheduling().getName(), "'.");
            throw new MaintainException("Error maintaining scheduling '" + msg.getScheduling().getName() + "'.", pe);
        }

        return rs;
    }

    @Override
    protected SchedulingListMsg maintainSchedulingList(SchedulingListMsg msg) throws MaintainException {
        if (msg.getSchedulingList() == null) {
            throw new MaintainException("Error maintaining scheduling the list is 'null'.");
        }

        SchedulingListMsg retVal = new SchedulingListMsg();
        for (Scheduling scheduling : msg.getSchedulingList()) {
            try {
                if (scheduling.getDatatypeState() != DatatypeState.PERSISTENT) {
                    Scheduling maintainedScheduling = super.getPersistenceManager().persist(scheduling);
                    maintainedScheduling.getStaffList().size();
                    retVal.getSchedulingList().add(maintainedScheduling);
                } else {
                    retVal.getSchedulingList().add(scheduling);
                }

            } catch (PersistenceException pe) {
                super.getLogger().error(pe, "Error maintaining scheduling '", scheduling.getName(), "'.");
                throw new MaintainException("Error maintaining scheduling '" + scheduling.getName() + "'.", pe);
            }
        }

        return retVal;
    }
}
