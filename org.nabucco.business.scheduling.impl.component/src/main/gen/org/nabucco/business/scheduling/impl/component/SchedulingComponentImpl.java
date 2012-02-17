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
package org.nabucco.business.scheduling.impl.component;

import org.nabucco.business.scheduling.facade.component.SchedulingComponentLocal;
import org.nabucco.business.scheduling.facade.component.SchedulingComponentRemote;
import org.nabucco.business.scheduling.facade.service.maintain.MaintainScheduling;
import org.nabucco.business.scheduling.facade.service.produce.ProduceScheduling;
import org.nabucco.business.scheduling.facade.service.resolve.ResolveScheduling;
import org.nabucco.business.scheduling.facade.service.search.SearchScheduling;
import org.nabucco.framework.base.facade.component.handler.PostConstructHandler;
import org.nabucco.framework.base.facade.component.handler.PreDestroyHandler;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.facade.service.componentrelation.ComponentRelationService;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.facade.service.queryfilter.QueryFilterService;
import org.nabucco.framework.base.impl.component.ComponentSupport;

/**
 * SchedulingComponentImpl<p/>Scheduling component for business schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class SchedulingComponentImpl extends ComponentSupport implements SchedulingComponentLocal,
        SchedulingComponentRemote {

    private static final long serialVersionUID = 1L;

    private static final String ID = "SchedulingComponent";

    /** Constructs a new SchedulingComponentImpl instance. */
    public SchedulingComponentImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PostConstructHandler handler = injector.inject(PostConstructHandler.getId());
        if ((handler == null)) {
            if (super.getLogger().isDebugEnabled()) {
                super.getLogger().debug("No post construct handler configured for \'", ID, "\'.");
            }
            return;
        }
        handler.setLocatable(this);
        handler.setLogger(super.getLogger());
        handler.invoke();
    }

    @Override
    public void preDestroy() {
        super.preDestroy();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PreDestroyHandler handler = injector.inject(PreDestroyHandler.getId());
        if ((handler == null)) {
            if (super.getLogger().isDebugEnabled()) {
                super.getLogger().debug("No pre destroy handler configured for \'", ID, "\'.");
            }
            return;
        }
        handler.setLocatable(this);
        handler.setLogger(super.getLogger());
        handler.invoke();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return COMPONENT_NAME;
    }

    @Override
    public String getJndiName() {
        return JNDI_NAME;
    }

    @Override
    public ComponentRelationService getComponentRelationService() throws ServiceException {
        return super.lookup(SchedulingComponentJndiNames.COMPONENT_RELATION_SERVICE_REMOTE,
                ComponentRelationService.class);
    }

    @Override
    public ComponentRelationService getComponentRelationServiceLocal() throws ServiceException {
        return super.lookup(SchedulingComponentJndiNames.COMPONENT_RELATION_SERVICE_LOCAL,
                ComponentRelationService.class);
    }

    @Override
    public QueryFilterService getQueryFilterService() throws ServiceException {
        return super.lookup(SchedulingComponentJndiNames.QUERY_FILTER_SERVICE_REMOTE, QueryFilterService.class);
    }

    @Override
    public QueryFilterService getQueryFilterServiceLocal() throws ServiceException {
        return super.lookup(SchedulingComponentJndiNames.QUERY_FILTER_SERVICE_LOCAL, QueryFilterService.class);
    }

    @Override
    public MaintainScheduling getMaintainSchedulingLocal() throws ServiceException {
        return super.lookup(SchedulingComponentJndiNames.MAINTAIN_SCHEDULING_LOCAL, MaintainScheduling.class);
    }

    @Override
    public MaintainScheduling getMaintainScheduling() throws ServiceException {
        return super.lookup(SchedulingComponentJndiNames.MAINTAIN_SCHEDULING_REMOTE, MaintainScheduling.class);
    }

    @Override
    public ProduceScheduling getProduceSchedulingLocal() throws ServiceException {
        return super.lookup(SchedulingComponentJndiNames.PRODUCE_SCHEDULING_LOCAL, ProduceScheduling.class);
    }

    @Override
    public ProduceScheduling getProduceScheduling() throws ServiceException {
        return super.lookup(SchedulingComponentJndiNames.PRODUCE_SCHEDULING_REMOTE, ProduceScheduling.class);
    }

    @Override
    public ResolveScheduling getResolveSchedulingLocal() throws ServiceException {
        return super.lookup(SchedulingComponentJndiNames.RESOLVE_SCHEDULING_LOCAL, ResolveScheduling.class);
    }

    @Override
    public ResolveScheduling getResolveScheduling() throws ServiceException {
        return super.lookup(SchedulingComponentJndiNames.RESOLVE_SCHEDULING_REMOTE, ResolveScheduling.class);
    }

    @Override
    public SearchScheduling getSearchSchedulingLocal() throws ServiceException {
        return super.lookup(SchedulingComponentJndiNames.SEARCH_SCHEDULING_LOCAL, SearchScheduling.class);
    }

    @Override
    public SearchScheduling getSearchScheduling() throws ServiceException {
        return super.lookup(SchedulingComponentJndiNames.SEARCH_SCHEDULING_REMOTE, SearchScheduling.class);
    }
}
