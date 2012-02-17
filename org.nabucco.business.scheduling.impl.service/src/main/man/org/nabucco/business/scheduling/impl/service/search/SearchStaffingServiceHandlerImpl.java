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

import org.nabucco.business.scheduling.facade.datatype.Scheduling;
import org.nabucco.business.scheduling.facade.datatype.Staffing;
import org.nabucco.business.scheduling.facade.message.StaffingListMsg;
import org.nabucco.business.scheduling.facade.message.search.StaffingSearchRq;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.base.impl.service.search.QuerySupport;

/**
 * SearchStaffingServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class SearchStaffingServiceHandlerImpl extends SearchStaffingServiceHandler {

    private static final long serialVersionUID = 1L;

    private static String query;

    @Override
    protected StaffingListMsg searchStaffing(StaffingSearchRq msg) throws SearchException {

        StaffingListMsg rs = new StaffingListMsg();

        try {
            NabuccoQuery<Staffing> query = this.getQuery();

            Long schedulingId = (msg.getScheduling() != null) ? msg.getScheduling().getId() : null;

            query.setParameter(Scheduling.ID, schedulingId);
            query.setParameter(Staffing.SUMMARY, QuerySupport.searchParameter(msg.getSummary()));

            rs.getStaffingList().addAll(query.getResultList());

        } catch (PersistenceException pe) {
            super.getLogger().error(pe, "Error searching for staffing '", msg.getSummary(), "'.");
            throw new SearchException("Error searching for staffing '" + msg.getSummary() + "'.", pe);
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
    private NabuccoQuery<Staffing> getQuery() throws PersistenceException {
        if (query == null) {
            StringBuilder queryString = new StringBuilder();
            queryString.append("select st from Scheduling sch");
            queryString.append(" inner join sch.staffListJPA st");
            queryString.append(" where sch.id = :id or :id is null");
            queryString.append(" and (st.summary like :summary or :summary is null)");
            query = queryString.toString();
        }

        return super.getPersistenceManager().createQuery(query);
    }

}
