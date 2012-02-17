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
import org.nabucco.business.scheduling.facade.message.SchedulingListMsg;
import org.nabucco.business.scheduling.facade.message.search.SchedulingSearchRq;
import org.nabucco.framework.base.facade.exception.persistence.PersistenceException;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.impl.service.maintain.NabuccoQuery;
import org.nabucco.framework.base.impl.service.search.QuerySupport;

/**
 * SearchSchedulingServiceHandlerImpl
 * 
 * @author Nicolas Moser, PRODYNA AG
 */
public class SearchSchedulingServiceHandlerImpl extends SearchSchedulingServiceHandler {

    private static final long serialVersionUID = 1L;

    private static String query;

    @Override
    protected SchedulingListMsg searchScheduling(SchedulingSearchRq msg) throws SearchException {

        SchedulingListMsg rs = new SchedulingListMsg();

        try {
            NabuccoQuery<Scheduling> query = this.getQuery();
            query.setParameter(Scheduling.STATUS, msg.getStatus());
            query.setParameter(Scheduling.NAME, msg.getName());
            query.setParameter(Scheduling.OWNER, msg.getOwner());
            query.setParameter(Scheduling.DESCRIPTION, QuerySupport.searchParameter(msg.getDescription()));

            rs.getSchedulingList().addAll(query.getResultList());

        } catch (PersistenceException pe) {
            super.getLogger().error(pe, "Error resolving scheduling '", msg.getName(), "'.");
            throw new SearchException("Error resolving scheduling '" + msg.getName() + "'.", pe);
        }

        return rs;
    }

    /**
     * Create the scheduling query and place it into the memory.
     * 
     * @return a new create query instance
     * 
     * @throws PersistenceException
     *             when the query cannot be created
     */
    private NabuccoQuery<Scheduling> getQuery() throws PersistenceException {
        if (query == null) {
            StringBuilder queryString = new StringBuilder();
            queryString.append("select s from Scheduling s where");
            queryString.append(" (s.status = :status)");
            queryString.append(" and (s.name = :name or :name is null)");
            queryString.append(" and (s.owner = :owner or :owner is null)");
            queryString.append(" and (s.description like :description or :description is null)");
            query = queryString.toString();
        }

        return super.getPersistenceManager().createQuery(query);
    }
}
