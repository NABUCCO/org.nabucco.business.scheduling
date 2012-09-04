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
package org.nabucco.business.scheduling.facade.message.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.business.scheduling.facade.datatype.Scheduling;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.message.ServiceMessage;
import org.nabucco.framework.base.facade.message.ServiceMessageSupport;

/**
 * StaffingSearchRq<p/>Search Request for a scheduling.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class StaffingSearchRq extends ServiceMessageSupport implements ServiceMessage {

    private static final long serialVersionUID = 1L;

    private static final String[] PROPERTY_CONSTRAINTS = { "m0,1;", "l0,255;u0,n;m0,1;" };

    public static final String SCHEDULING = "scheduling";

    public static final String SUMMARY = "summary";

    /** The transfered scheduling. */
    private Scheduling scheduling;

    /** The staffing summary. */
    private Name summary;

    /** Constructs a new StaffingSearchRq instance. */
    public StaffingSearchRq() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.put(SCHEDULING, PropertyDescriptorSupport.createDatatype(SCHEDULING, Scheduling.class, 0,
                PROPERTY_CONSTRAINTS[0], false, PropertyAssociationType.COMPOSITION));
        propertyMap.put(SUMMARY,
                PropertyDescriptorSupport.createBasetype(SUMMARY, Name.class, 1, PROPERTY_CONSTRAINTS[1], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    /** Init. */
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(StaffingSearchRq.getPropertyDescriptor(SCHEDULING), this.getScheduling()));
        properties.add(super.createProperty(StaffingSearchRq.getPropertyDescriptor(SUMMARY), this.summary));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(SCHEDULING) && (property.getType() == Scheduling.class))) {
            this.setScheduling(((Scheduling) property.getInstance()));
            return true;
        } else if ((property.getName().equals(SUMMARY) && (property.getType() == Name.class))) {
            this.setSummary(((Name) property.getInstance()));
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if ((this == obj)) {
            return true;
        }
        if ((obj == null)) {
            return false;
        }
        if ((this.getClass() != obj.getClass())) {
            return false;
        }
        if ((!super.equals(obj))) {
            return false;
        }
        final StaffingSearchRq other = ((StaffingSearchRq) obj);
        if ((this.scheduling == null)) {
            if ((other.scheduling != null))
                return false;
        } else if ((!this.scheduling.equals(other.scheduling)))
            return false;
        if ((this.summary == null)) {
            if ((other.summary != null))
                return false;
        } else if ((!this.summary.equals(other.summary)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.scheduling == null) ? 0 : this.scheduling.hashCode()));
        result = ((PRIME * result) + ((this.summary == null) ? 0 : this.summary.hashCode()));
        return result;
    }

    @Override
    public ServiceMessage cloneObject() {
        return this;
    }

    /**
     * The transfered scheduling.
     *
     * @return the Scheduling.
     */
    public Scheduling getScheduling() {
        return this.scheduling;
    }

    /**
     * The transfered scheduling.
     *
     * @param scheduling the Scheduling.
     */
    public void setScheduling(Scheduling scheduling) {
        this.scheduling = scheduling;
    }

    /**
     * The staffing summary.
     *
     * @return the Name.
     */
    public Name getSummary() {
        return this.summary;
    }

    /**
     * The staffing summary.
     *
     * @param summary the Name.
     */
    public void setSummary(Name summary) {
        this.summary = summary;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(StaffingSearchRq.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(StaffingSearchRq.class).getAllProperties();
    }
}
