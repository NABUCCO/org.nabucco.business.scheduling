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

import org.nabucco.framework.base.facade.component.connection.ConnectionException;
import org.nabucco.framework.base.facade.component.locator.ComponentLocator;
import org.nabucco.framework.base.facade.component.locator.ComponentLocatorSupport;

/**
 * Locator for SchedulingComponent.
 *
 * @author NABUCCO Generator, PRODYNA AG
 */
public class SchedulingComponentLocator extends ComponentLocatorSupport<SchedulingComponent> implements
        ComponentLocator<SchedulingComponent> {

    private static SchedulingComponentLocator instance;

    /**
     * Constructs a new SchedulingComponentLocator instance.
     *
     * @param component the Class<SchedulingComponent>.
     * @param jndiName the String.
     */
    private SchedulingComponentLocator(String jndiName, Class<SchedulingComponent> component) {
        super(jndiName, component);
    }

    @Override
    public SchedulingComponent getComponent() throws ConnectionException {
        SchedulingComponent component = super.getComponent();
        if ((component instanceof SchedulingComponentLocal)) {
            return new SchedulingComponentLocalProxy(((SchedulingComponentLocal) component));
        }
        return component;
    }

    /**
     * Getter for the Instance.
     *
     * @return the SchedulingComponentLocator.
     */
    public static SchedulingComponentLocator getInstance() {
        if ((instance == null)) {
            instance = new SchedulingComponentLocator(SchedulingComponent.JNDI_NAME, SchedulingComponent.class);
        }
        return instance;
    }
}
