/*
 * Copyright 2012 PRODYNA AG
 * 
 * Licensed under the Eclipse Public License (EPL), Version 1.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/eclipse-1.0.php or
 * http://www.nabucco.org/License.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package org.nabucco.business.scheduling.facade.component;

import org.nabucco.business.scheduling.facade.service.maintain.MaintainScheduling;
import org.nabucco.business.scheduling.facade.service.produce.ProduceScheduling;
import org.nabucco.business.scheduling.facade.service.resolve.ResolveScheduling;
import org.nabucco.business.scheduling.facade.service.search.SearchScheduling;
import org.nabucco.framework.base.facade.component.Component;
import org.nabucco.framework.base.facade.exception.service.ServiceException;

/**
 * SchedulingComponent<p/>Scheduling component for business schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public interface SchedulingComponent extends Component {

    final String COMPONENT_NAME = "org.nabucco.business.scheduling";

    final String COMPONENT_PREFIX = "schd";

    final String JNDI_NAME = ((((JNDI_PREFIX + "/") + COMPONENT_NAME) + "/") + "org.nabucco.business.scheduling.facade.component.SchedulingComponent");

    /**
     * Getter for the MaintainScheduling.
     *
     * @return the MaintainScheduling.
     * @throws ServiceException
     */
    MaintainScheduling getMaintainScheduling() throws ServiceException;

    /**
     * Getter for the ProduceScheduling.
     *
     * @return the ProduceScheduling.
     * @throws ServiceException
     */
    ProduceScheduling getProduceScheduling() throws ServiceException;

    /**
     * Getter for the ResolveScheduling.
     *
     * @return the ResolveScheduling.
     * @throws ServiceException
     */
    ResolveScheduling getResolveScheduling() throws ServiceException;

    /**
     * Getter for the SearchScheduling.
     *
     * @return the SearchScheduling.
     * @throws ServiceException
     */
    SearchScheduling getSearchScheduling() throws ServiceException;
}
