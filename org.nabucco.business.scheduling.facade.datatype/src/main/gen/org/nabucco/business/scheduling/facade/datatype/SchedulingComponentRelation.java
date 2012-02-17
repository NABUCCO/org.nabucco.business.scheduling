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
package org.nabucco.business.scheduling.facade.datatype;

import org.nabucco.framework.base.facade.datatype.componentrelation.ComponentRelation;
import org.nabucco.framework.base.facade.datatype.componentrelation.ComponentRelationType;

/**
 * SchedulingComponentRelation<p/>Holds schedules of specific customer requests.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class SchedulingComponentRelation extends ComponentRelation<Scheduling> {

    private static final long serialVersionUID = 1L;

    /** Constructs a new SchedulingComponentRelation instance. */
    protected SchedulingComponentRelation() {
        super();
    }

    /**
     * Constructs a new SchedulingComponentRelation instance.
     *
     * @param relationType the ComponentRelationType.
     */
    public SchedulingComponentRelation(ComponentRelationType relationType) {
        super(relationType);
    }

    /**
     * Getter for the Tarthe .
     *
     * @return the Scheduling.
     */
    public Scheduling getTarget() {
        return super.getTarget();
    }

    /**
     * Setter for the Target.
     *
     * @param target the Scheduling.
     */
    public void setTarget(Scheduling target) {
        super.setTarget(target);
    }

    @Override
    public SchedulingComponentRelation cloneObject() {
        SchedulingComponentRelation clone = new SchedulingComponentRelation();
        super.cloneObject(clone);
        return clone;
    }
}
