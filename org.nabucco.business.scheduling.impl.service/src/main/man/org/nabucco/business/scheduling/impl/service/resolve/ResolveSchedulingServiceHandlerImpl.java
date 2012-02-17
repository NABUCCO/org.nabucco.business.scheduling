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

import org.nabucco.business.scheduling.facade.datatype.Scheduling;
import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.ResolveException;

/**
 * ResolveSchedulingServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class ResolveSchedulingServiceHandlerImpl extends ResolveSchedulingServiceHandler {

    private static final long serialVersionUID = 1L;

    @Override
    protected SchedulingMsg resolveScheduling(SchedulingMsg msg) throws ResolveException {

        if (msg.getScheduling() == null) {
            throw new ResolveException("Error maintaining scheduling '" + msg.getScheduling().getName() + "'.");
        }

        Scheduling scheduling = msg.getScheduling();

        try {
            scheduling = super.getPersistenceManager().find(scheduling);
            scheduling.getStaffList().size();

        } catch (PersistenceException pe) {
            super.getLogger().error(pe, "Error resolving scheduling '", msg.getScheduling().getName(), "'.");
            throw new ResolveException("Error resolving scheduling '" + msg.getScheduling().getName() + "'.", pe);
        }

        SchedulingMsg rs = new SchedulingMsg();
        rs.setScheduling(scheduling);

        return rs;
    }
}
