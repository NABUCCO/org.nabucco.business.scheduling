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
package org.nabucco.business.scheduling.facade.datatype;

import java.util.Collections;
import java.util.Set;
import org.nabucco.framework.base.facade.datatype.Enumeration;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.visitor.Visitor;
import org.nabucco.framework.base.facade.datatype.visitor.VisitorException;

/**
 * SchedulingStatus<p/>Status of a Schedulng.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public enum SchedulingStatus implements Enumeration {
    /** Created Scheduling */
    CREATED("C"),
    /** Requested Scheduling */
    REQUESTED("R"),
    /** Prioritized Scheduling */
    PRIORITIZED("P"),
    /** Running Scheduling */
    IN_SCHEDULING("I"),
    /** Scheduled Scheduling */
    SCHEDULED("S"),
    /** Approved Scheduling */
    APPROVED("A"),
    /** Rejected Scheduling */
    NOT_APPROVED("N"),
    /** Closed Scheduling */
    CLOSED("X"), ;

    private String id;

    /**
     * Constructs a new SchedulingStatus instance.
     *
     * @param id the String.
     */
    SchedulingStatus(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getOrdinal() {
        return this.ordinal();
    }

    @Override
    public Enumeration cloneObject() {
        return this;
    }

    @Override
    public void accept(Visitor visitor) throws VisitorException {
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        return Collections.emptySet();
    }

    /**
     * ValueOfId.
     *
     * @param literalId the String.
     * @return the SchedulingStatus.
     */
    public static SchedulingStatus valueOfId(String literalId) {
        for (SchedulingStatus enumeration : SchedulingStatus.values()) {
            if (enumeration.getId().equalsIgnoreCase(literalId)) {
                return enumeration;
            }
        }
        return null;
    }
}
