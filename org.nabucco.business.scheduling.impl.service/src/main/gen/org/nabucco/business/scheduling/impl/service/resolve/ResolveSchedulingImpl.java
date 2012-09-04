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
package org.nabucco.business.scheduling.impl.service.resolve;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.business.scheduling.facade.message.StaffingMsg;
import org.nabucco.business.scheduling.facade.service.resolve.ResolveScheduling;
import org.nabucco.framework.base.facade.exception.service.ResolveException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;

/**
 * ResolveSchedulingImpl<p/>Service for resolving schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class ResolveSchedulingImpl extends ServiceSupport implements ResolveScheduling {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ResolveScheduling";

    private static Map<String, String[]> ASPECTS;

    private ResolveSchedulingServiceHandler resolveSchedulingServiceHandler;

    private ResolveStaffingServiceHandler resolveStaffingServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ResolveSchedulingImpl instance. */
    public ResolveSchedulingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.resolveSchedulingServiceHandler = injector.inject(ResolveSchedulingServiceHandler.getId());
        if ((this.resolveSchedulingServiceHandler != null)) {
            this.resolveSchedulingServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveSchedulingServiceHandler.setLogger(super.getLogger());
        }
        this.resolveStaffingServiceHandler = injector.inject(ResolveStaffingServiceHandler.getId());
        if ((this.resolveStaffingServiceHandler != null)) {
            this.resolveStaffingServiceHandler.setPersistenceManager(persistenceManager);
            this.resolveStaffingServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("resolveScheduling", new String[] { "org.nabucco.aspect.resolving",
                    "org.nabucco.aspect.transitioning", "org.nabucco.aspect.constraining",
                    "org.nabucco.aspect.historization" });
            ASPECTS.put("resolveStaffing", new String[] { "org.nabucco.aspect.resolving",
                    "org.nabucco.aspect.transitioning", "org.nabucco.aspect.constraining",
                    "org.nabucco.aspect.historization" });
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<SchedulingMsg> resolveScheduling(ServiceRequest<SchedulingMsg> rq) throws ResolveException {
        if ((this.resolveSchedulingServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveScheduling().");
            throw new InjectionException("No service implementation configured for resolveScheduling().");
        }
        ServiceResponse<SchedulingMsg> rs;
        this.resolveSchedulingServiceHandler.init();
        rs = this.resolveSchedulingServiceHandler.invoke(rq);
        this.resolveSchedulingServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<StaffingMsg> resolveStaffing(ServiceRequest<StaffingMsg> rq) throws ResolveException {
        if ((this.resolveStaffingServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for resolveStaffing().");
            throw new InjectionException("No service implementation configured for resolveStaffing().");
        }
        ServiceResponse<StaffingMsg> rs;
        this.resolveStaffingServiceHandler.init();
        rs = this.resolveStaffingServiceHandler.invoke(rq);
        this.resolveStaffingServiceHandler.finish();
        return rs;
    }
}
