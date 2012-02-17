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
package org.nabucco.business.scheduling.facade.component;

import org.nabucco.business.scheduling.facade.service.maintain.MaintainScheduling;
import org.nabucco.business.scheduling.facade.service.produce.ProduceScheduling;
import org.nabucco.business.scheduling.facade.service.resolve.ResolveScheduling;
import org.nabucco.business.scheduling.facade.service.search.SearchScheduling;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;

/**
 * SchedulingComponentLocal<p/>Scheduling component for business schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public interface SchedulingComponentLocal extends SchedulingComponent {

    /**
     * Getter for the ComponentRelationServiceLocal.
     *
     * @return the ComponentRelationService.
     * @throws ServiceException
     */
    ComponentRelationService getComponentRelationServiceLocal() throws ServiceException;

    /**
     * Getter for the QueryFilterServiceLocal.
     *
     * @return the QueryFilterService.
     * @throws ServiceException
     */
    QueryFilterService getQueryFilterServiceLocal() throws ServiceException;

    /**
     * Getter for the MaintainSchedulingLocal.
     *
     * @return the MaintainScheduling.
     * @throws ServiceException
     */
    MaintainScheduling getMaintainSchedulingLocal() throws ServiceException;

    /**
     * Getter for the ProduceSchedulingLocal.
     *
     * @return the ProduceScheduling.
     * @throws ServiceException
     */
    ProduceScheduling getProduceSchedulingLocal() throws ServiceException;

    /**
     * Getter for the ResolveSchedulingLocal.
     *
     * @return the ResolveScheduling.
     * @throws ServiceException
     */
    ResolveScheduling getResolveSchedulingLocal() throws ServiceException;

    /**
     * Getter for the SearchSchedulingLocal.
     *
     * @return the SearchScheduling.
     * @throws ServiceException
     */
    SearchScheduling getSearchSchedulingLocal() throws ServiceException;
}
