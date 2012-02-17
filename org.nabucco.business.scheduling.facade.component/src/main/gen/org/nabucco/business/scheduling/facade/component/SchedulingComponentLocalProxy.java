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

import org.nabucco.business.scheduling.facade.component.SchedulingComponent;
import org.nabucco.business.scheduling.facade.service.maintain.MaintainScheduling;
import org.nabucco.business.scheduling.facade.service.produce.ProduceScheduling;
import org.nabucco.business.scheduling.facade.service.resolve.ResolveScheduling;
import org.nabucco.business.scheduling.facade.service.search.SearchScheduling;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;

/**
 * SchedulingComponentLocalProxy<p/>Scheduling component for business schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class SchedulingComponentLocalProxy implements SchedulingComponent {

    private static final long serialVersionUID = 1L;

    private final SchedulingComponentLocal delegate;

    /**
     * Constructs a new SchedulingComponentLocalProxy instance.
     *
     * @param delegate the SchedulingComponentLocal.
     */
    public SchedulingComponentLocalProxy(SchedulingComponentLocal delegate) {
        super();
        if ((delegate == null)) {
            throw new IllegalArgumentException("Cannot create local proxy for component [null].");
        }
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return this.delegate.getId();
    }

    @Override
    public String getName() {
        return this.delegate.getName();
    }

    @Override
    public String getJndiName() {
        return this.delegate.getJndiName();
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return this.delegate.getComponentRelationServiceLocal();
    }

    @Override
    public QueryFilterService getQueryFilterService() throws ServiceException {
        return this.delegate.getQueryFilterServiceLocal();
    }

    @Override
    public String toString() {
        return this.delegate.toString();
    }

    @Override
    public MaintainScheduling getMaintainScheduling() throws ServiceException {
        return this.delegate.getMaintainSchedulingLocal();
    }

    @Override
    public ProduceScheduling getProduceScheduling() throws ServiceException {
        return this.delegate.getProduceSchedulingLocal();
    }

    @Override
    public ResolveScheduling getResolveScheduling() throws ServiceException {
        return this.delegate.getResolveSchedulingLocal();
    }

    @Override
    public SearchScheduling getSearchScheduling() throws ServiceException {
        return this.delegate.getSearchSchedulingLocal();
    }
}
