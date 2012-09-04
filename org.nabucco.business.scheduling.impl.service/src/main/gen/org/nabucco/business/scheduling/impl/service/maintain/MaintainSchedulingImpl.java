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
package org.nabucco.business.scheduling.impl.service.maintain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.business.scheduling.facade.message.SchedulingListMsg;
import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.business.scheduling.facade.message.StaffingMsg;
import org.nabucco.business.scheduling.facade.service.maintain.MaintainScheduling;
import org.nabucco.framework.base.facade.exception.service.MaintainException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;

/**
 * MaintainSchedulingImpl<p/>Service for maintaining scheduling datatypes.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-10-04
 */
public class MaintainSchedulingImpl extends ServiceSupport implements MaintainScheduling {

    private static final long serialVersionUID = 1L;

    private static final String ID = "MaintainScheduling";

    private static Map<String, String[]> ASPECTS;

    private MaintainSchedulingServiceHandler maintainSchedulingServiceHandler;

    private MaintainSchedulingListServiceHandler maintainSchedulingListServiceHandler;

    private MaintainStaffingServiceHandler maintainStaffingServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new MaintainSchedulingImpl instance. */
    public MaintainSchedulingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.maintainSchedulingServiceHandler = injector.inject(MaintainSchedulingServiceHandler.getId());
        if ((this.maintainSchedulingServiceHandler != null)) {
            this.maintainSchedulingServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainSchedulingServiceHandler.setLogger(super.getLogger());
        }
        this.maintainSchedulingListServiceHandler = injector.inject(MaintainSchedulingListServiceHandler.getId());
        if ((this.maintainSchedulingListServiceHandler != null)) {
            this.maintainSchedulingListServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainSchedulingListServiceHandler.setLogger(super.getLogger());
        }
        this.maintainStaffingServiceHandler = injector.inject(MaintainStaffingServiceHandler.getId());
        if ((this.maintainStaffingServiceHandler != null)) {
            this.maintainStaffingServiceHandler.setPersistenceManager(persistenceManager);
            this.maintainStaffingServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("maintainScheduling", new String[] { "org.nabucco.aspect.validating",
                    "org.nabucco.aspect.transitioning", "org.nabucco.aspect.relating", "org.nabucco.aspect.resolving",
                    "org.nabucco.aspect.indexing", "org.nabucco.aspect.constraining",
                    "org.nabucco.aspect.historization" });
            ASPECTS.put("maintainSchedulingList", new String[] { "org.nabucco.aspect.validating",
                    "org.nabucco.aspect.transitioning", "org.nabucco.aspect.relating", "org.nabucco.aspect.resolving",
                    "org.nabucco.aspect.indexing", "org.nabucco.aspect.constraining",
                    "org.nabucco.aspect.historization" });
            ASPECTS.put("maintainStaffing", new String[] { "org.nabucco.aspect.validating",
                    "org.nabucco.aspect.transitioning", "org.nabucco.aspect.relating", "org.nabucco.aspect.resolving",
                    "org.nabucco.aspect.constraining", "org.nabucco.aspect.historization" });
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<SchedulingMsg> maintainScheduling(ServiceRequest<SchedulingMsg> rq) throws MaintainException {
        if ((this.maintainSchedulingServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainScheduling().");
            throw new InjectionException("No service implementation configured for maintainScheduling().");
        }
        ServiceResponse<SchedulingMsg> rs;
        this.maintainSchedulingServiceHandler.init();
        rs = this.maintainSchedulingServiceHandler.invoke(rq);
        this.maintainSchedulingServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<SchedulingListMsg> maintainSchedulingList(ServiceRequest<SchedulingListMsg> rq)
            throws MaintainException {
        if ((this.maintainSchedulingListServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainSchedulingList().");
            throw new InjectionException("No service implementation configured for maintainSchedulingList().");
        }
        ServiceResponse<SchedulingListMsg> rs;
        this.maintainSchedulingListServiceHandler.init();
        rs = this.maintainSchedulingListServiceHandler.invoke(rq);
        this.maintainSchedulingListServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<StaffingMsg> maintainStaffing(ServiceRequest<StaffingMsg> rq) throws MaintainException {
        if ((this.maintainStaffingServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for maintainStaffing().");
            throw new InjectionException("No service implementation configured for maintainStaffing().");
        }
        ServiceResponse<StaffingMsg> rs;
        this.maintainStaffingServiceHandler.init();
        rs = this.maintainStaffingServiceHandler.invoke(rq);
        this.maintainStaffingServiceHandler.finish();
        return rs;
    }
}
