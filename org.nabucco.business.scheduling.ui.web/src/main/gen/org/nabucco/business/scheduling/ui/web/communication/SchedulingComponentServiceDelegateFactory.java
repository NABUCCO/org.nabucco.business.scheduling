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
package org.nabucco.business.scheduling.ui.web.communication;

import org.nabucco.business.scheduling.facade.component.SchedulingComponent;
import org.nabucco.business.scheduling.facade.component.SchedulingComponentLocator;
import org.nabucco.business.scheduling.ui.web.communication.maintain.MaintainSchedulingDelegate;
import org.nabucco.business.scheduling.ui.web.communication.produce.ProduceSchedulingDelegate;
import org.nabucco.business.scheduling.ui.web.communication.resolve.ResolveSchedulingDelegate;
import org.nabucco.business.scheduling.ui.web.communication.search.SearchSchedulingDelegate;
import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.exception.client.ClientException;
import org.nabucco.framework.base.facade.exception.service.ServiceException;
import org.nabucco.framework.base.ui.web.communication.ServiceDelegateFactorySupport;

/**
 * ServiceDelegateFactoryTemplate<p/>Scheduling component for business schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class SchedulingComponentServiceDelegateFactory extends ServiceDelegateFactorySupport<SchedulingComponent> {

    private static SchedulingComponentServiceDelegateFactory instance = new SchedulingComponentServiceDelegateFactory();

    private MaintainSchedulingDelegate maintainSchedulingDelegate;

    private ProduceSchedulingDelegate produceSchedulingDelegate;

    private ResolveSchedulingDelegate resolveSchedulingDelegate;

    private SearchSchedulingDelegate searchSchedulingDelegate;

    /** Constructs a new SchedulingComponentServiceDelegateFactory instance. */
    private SchedulingComponentServiceDelegateFactory() {
        super(SchedulingComponentLocator.getInstance());
    }

    /**
     * Getter for the MaintainScheduling.
     *
     * @return the MaintainSchedulingDelegate.
     * @throws ClientException
     */
    public MaintainSchedulingDelegate getMaintainScheduling() throws ClientException {
        try {
            if ((this.maintainSchedulingDelegate == null)) {
                this.maintainSchedulingDelegate = new MaintainSchedulingDelegate(this.getComponent()
                        .getMaintainScheduling());
            }
            return this.maintainSchedulingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: MaintainScheduling", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ProduceScheduling.
     *
     * @return the ProduceSchedulingDelegate.
     * @throws ClientException
     */
    public ProduceSchedulingDelegate getProduceScheduling() throws ClientException {
        try {
            if ((this.produceSchedulingDelegate == null)) {
                this.produceSchedulingDelegate = new ProduceSchedulingDelegate(this.getComponent()
                        .getProduceScheduling());
            }
            return this.produceSchedulingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ProduceScheduling", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the ResolveScheduling.
     *
     * @return the ResolveSchedulingDelegate.
     * @throws ClientException
     */
    public ResolveSchedulingDelegate getResolveScheduling() throws ClientException {
        try {
            if ((this.resolveSchedulingDelegate == null)) {
                this.resolveSchedulingDelegate = new ResolveSchedulingDelegate(this.getComponent()
                        .getResolveScheduling());
            }
            return this.resolveSchedulingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: ResolveScheduling", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the SearchScheduling.
     *
     * @return the SearchSchedulingDelegate.
     * @throws ClientException
     */
    public SearchSchedulingDelegate getSearchScheduling() throws ClientException {
        try {
            if ((this.searchSchedulingDelegate == null)) {
                this.searchSchedulingDelegate = new SearchSchedulingDelegate(this.getComponent().getSearchScheduling());
            }
            return this.searchSchedulingDelegate;
        } catch (ConnectionException e) {
            throw new ClientException("Cannot locate service: SearchScheduling", e);
        } catch (ServiceException e) {
            throw new ClientException("Cannot locate service: ServiceDelegateTemplate", e);
        }
    }

    /**
     * Getter for the Instance.
     *
     * @return the SchedulingComponentServiceDelegateFactory.
     */
    public static SchedulingComponentServiceDelegateFactory getInstance() {
        return instance;
    }
}
