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
package org.nabucco.business.scheduling.impl.service.search;

import java.util.List;

import org.nabucco.business.scheduling.facade.datatype.Scheduling;
import org.nabucco.business.scheduling.facade.datatype.Staffing;
import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.business.scheduling.facade.message.search.SchedulingByStaffingSearchRq;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;

/**
 * SearchSchedulingByStaffingServiceHandlerImpl
 * 
 * Searches for a scheduling that references the given staffing
 * 
 * @author Leonid Agranovskiy, PRODYNA AG
 */
public class SearchSchedulingByStaffingServiceHandlerImpl extends SearchSchedulingByStaffingServiceHandler {

    private static final long serialVersionUID = 1L;

    private static String query;

    @Override
    protected SchedulingMsg searchSchedulingByStaffing(SchedulingByStaffingSearchRq msg) throws SearchException {
        SchedulingMsg rs = new SchedulingMsg();
        Long staffingId = msg.getRelatedStaffing().getId();

        try {
            NabuccoQuery<Scheduling> query = this.getQuery();

            query.setParameter(Staffing.ID, staffingId);

            List<Scheduling> resultList = query.getResultList();

            if (!resultList.isEmpty() && resultList.size() == 1) {
                Scheduling scheduling = resultList.get(0);

                // Resolve Scheduling
                scheduling = super.getPersistenceManager().find(scheduling);
                scheduling.getStaffList().size();

                rs.setScheduling(scheduling);
            }

        } catch (PersistenceException pe) {
            super.getLogger().error(pe, "Error searching for scheduling related to the staffing with id '", staffingId,
                    "'.");
            throw new SearchException("Error searching for scheduling related to the staffing with id  '"
                    + staffingId + "'.", pe);
        }

        return rs;
    }

    /**
     * Create the staffing query and place it into the memory.
     * 
     * @return a new create query instance
     * 
     * @throws PersistenceException
     *             when the query cannot be created
     */
    private NabuccoQuery<Scheduling> getQuery() throws PersistenceException {
        if (query == null) {
            StringBuilder queryString = new StringBuilder();
            queryString.append("select sch from Scheduling sch");
            queryString.append(" inner join sch.staffListJPA st");
            queryString.append(" where st.id = :id");
            query = queryString.toString();
        }

        return super.getPersistenceManager().createQuery(query);
    }

}
