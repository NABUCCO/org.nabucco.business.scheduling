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
package org.nabucco.business.scheduling.impl.service.produce;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import org.nabucco.business.scheduling.facade.message.SchedulingMsg;
import org.nabucco.business.scheduling.facade.message.StaffingListMsg;
import org.nabucco.business.scheduling.facade.message.produce.SchedulingPrototypeRq;
import org.nabucco.business.scheduling.facade.message.produce.StaffingPrototypeRq;
import org.nabucco.business.scheduling.facade.service.produce.ProduceScheduling;
import org.nabucco.framework.base.facade.exception.service.ProduceException;
import org.nabucco.framework.base.facade.message.ServiceRequest;
import org.nabucco.framework.base.facade.message.ServiceResponse;
import org.nabucco.framework.base.facade.service.injection.InjectionException;
import org.nabucco.framework.base.facade.service.injection.InjectionProvider;
import org.nabucco.framework.base.impl.service.ServiceSupport;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManager;
import org.nabucco.framework.base.impl.service.maintain.PersistenceManagerFactory;

/**
 * ProduceSchedulingImpl<p/>Service for producing schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class ProduceSchedulingImpl extends ServiceSupport implements ProduceScheduling {

    private static final long serialVersionUID = 1L;

    private static final String ID = "ProduceScheduling";

    private static Map<String, String[]> ASPECTS;

    private ProduceSchedulingServiceHandler produceSchedulingServiceHandler;

    private ProduceStaffingsServiceHandler produceStaffingsServiceHandler;

    private EntityManager entityManager;

    /** Constructs a new ProduceSchedulingImpl instance. */
    public ProduceSchedulingImpl() {
        super();
    }

    @Override
    public void postConstruct() {
        super.postConstruct();
        InjectionProvider injector = InjectionProvider.getInstance(ID);
        PersistenceManager persistenceManager = PersistenceManagerFactory.getInstance().createPersistenceManager(
                this.entityManager, super.getLogger());
        this.produceSchedulingServiceHandler = injector.inject(ProduceSchedulingServiceHandler.getId());
        if ((this.produceSchedulingServiceHandler != null)) {
            this.produceSchedulingServiceHandler.setPersistenceManager(persistenceManager);
            this.produceSchedulingServiceHandler.setLogger(super.getLogger());
        }
        this.produceStaffingsServiceHandler = injector.inject(ProduceStaffingsServiceHandler.getId());
        if ((this.produceStaffingsServiceHandler != null)) {
            this.produceStaffingsServiceHandler.setPersistenceManager(persistenceManager);
            this.produceStaffingsServiceHandler.setLogger(super.getLogger());
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
            ASPECTS.put("produceScheduling", new String[] { "org.nabucco.aspect.initializing",
                    "org.nabucco.aspect.constraining" });
            ASPECTS.put("produceStaffings", new String[] { "org.nabucco.aspect.initializing",
                    "org.nabucco.aspect.constraining" });
        }
        String[] aspects = ASPECTS.get(operationName);
        if ((aspects == null)) {
            return ServiceSupport.NO_ASPECTS;
        }
        return Arrays.copyOf(aspects, aspects.length);
    }

    @Override
    public ServiceResponse<SchedulingMsg> produceScheduling(ServiceRequest<SchedulingPrototypeRq> rq)
            throws ProduceException {
        if ((this.produceSchedulingServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceScheduling().");
            throw new InjectionException("No service implementation configured for produceScheduling().");
        }
        ServiceResponse<SchedulingMsg> rs;
        this.produceSchedulingServiceHandler.init();
        rs = this.produceSchedulingServiceHandler.invoke(rq);
        this.produceSchedulingServiceHandler.finish();
        return rs;
    }

    @Override
    public ServiceResponse<StaffingListMsg> produceStaffings(ServiceRequest<StaffingPrototypeRq> rq)
            throws ProduceException {
        if ((this.produceStaffingsServiceHandler == null)) {
            super.getLogger().error("No service implementation configured for produceStaffings().");
            throw new InjectionException("No service implementation configured for produceStaffings().");
        }
        ServiceResponse<StaffingListMsg> rs;
        this.produceStaffingsServiceHandler.init();
        rs = this.produceStaffingsServiceHandler.invoke(rq);
        this.produceStaffingsServiceHandler.finish();
        return rs;
    }
}
