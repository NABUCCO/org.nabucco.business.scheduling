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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.business.scheduling.facade.message.SchedulingListMsg;
import org.nabucco.business.scheduling.facade.message.StaffingListMsg;
import org.nabucco.business.scheduling.facade.message.search.SchedulingSearchRq;
import org.nabucco.business.scheduling.facade.message.search.StaffingSearchRq;
import org.nabucco.business.scheduling.facade.service.search.SearchScheduling;
import org.nabucco.framework.base.facade.exception.service.SearchException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;

/**
 * SearchSchedulingImpl<p/>Service for searching schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class SearchSchedulingImpl extends ServiceSupport implements SearchScheduling {

    private static final long serialVersionUID = 1L;

    private static final String ID = "SearchScheduling";

    private static Map<String, String[]> ASPECTS;

    private SearchSchedulingServiceHandler searchSchedulingServiceHandler;

    private SearchStaffingServiceHandler searchStaffingServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new SearchSchedulingImpl instance. */
    public SearchSchedulingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.searchSchedulingServiceHandler = injector.inject(SearchSchedulingServiceHandler.getId());
        if ((this.searchSchedulingServiceHandler != null)) {
            this.searchSchedulingServiceHandler.setPersistenceManager(persistenceManager);
            this.searchSchedulingServiceHandler.setLogger(super.getLogger());
        }
        this.searchStaffingServiceHandler = injector.inject(SearchStaffingServiceHandler.getId());
        if ((this.searchStaffingServiceHandler != null)) {
            this.searchStaffingServiceHandler.setPersistenceManager(persistenceManager);
            this.searchStaffingServiceHandler.setLogger(super.getLogger());
        }
    }

    @Override
    public void preDestroy() {
        super.preDestroy();
    }

    @Override
    public String[] getAspects(String operationName) {
        if ((ASPECTS == null)) {
            ASPECTS = new HashMap<String, String[]>();
            ASPECTS.put("searchScheduling", new String[] { "org.nabucco.aspect.resolving" });
            ASPECTS.put("searchStaffing", new String[] { "org.nabucco.aspect.resolving" });
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<SchedulingListMsg> searchScheduling(ServiceRequest<SchedulingSearchRq> rq)
            throws SearchException {
        if ((this.searchSchedulingServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchScheduling().");
            throw new InjectionException("No service implementation configured for searchScheduling().");
        }
        ServiceResponse<SchedulingListMsg> rs;
        this.searchSchedulingServiceHandler.init();
        rs = this.searchSchedulingServiceHandler.invoke(rq);
        this.searchSchedulingServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<StaffingListMsg> searchStaffing(ServiceRequest<StaffingSearchRq> rq) throws SearchException {
        if ((this.searchStaffingServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for searchStaffing().");
            throw new InjectionException("No service implementation configured for searchStaffing().");
        }
        ServiceResponse<StaffingListMsg> rs;
        this.searchStaffingServiceHandler.init();
        rs = this.searchStaffingServiceHandler.invoke(rq);
        this.searchStaffingServiceHandler.finish();
        return rs;
    }
}
