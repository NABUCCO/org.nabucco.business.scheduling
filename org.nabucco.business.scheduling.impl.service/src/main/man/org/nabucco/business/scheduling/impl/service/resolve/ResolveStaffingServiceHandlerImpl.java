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
package org.nabucco.business.scheduling.impl.service.resolve;

import org.nabucco.business.scheduling.facade.datatype.Staffing;
import org.nabucco.business.scheduling.facade.message.StaffingMsg;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;

/**
 * ResolveStaffingServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ResolveStaffingServiceHandlerImpl extends ResolveStaffingServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected StaffingMsg resolveStaffing(StaffingMsg msg) throws ResolveException {

        if (msg.getStaffing() == null) {
            throw new ResolveException("Error maintaining staffing '" + msg.getStaffing().getSummary() + "'.");
        }

        StaffingMsg rs = new StaffingMsg();

        try {
            Staffing staffing = super.getPersistenceManager().find(msg.getStaffing());
            rs.setStaffing(staffing);

        } catch (PersistenceException pe) {
            super.getLogger().error(pe, "Error resolving staffing '", msg.getStaffing().getSummary(), "'.");
            throw new ResolveException("Error resolving staffing '" + msg.getStaffing().getSummary() + "'.", pe);
        }

        return rs;
    }

}
