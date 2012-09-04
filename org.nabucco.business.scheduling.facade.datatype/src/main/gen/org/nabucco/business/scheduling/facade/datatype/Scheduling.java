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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.nabucco.business.scheduling.facade.datatype.SchedulingStatus;
import org.nabucco.business.scheduling.facade.datatype.Staffing;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.MultiTenantDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.Owner;
import org.nabucco.framework.base.facade.datatype.Percentage;
import org.nabucco.framework.base.facade.datatype.business.OfferNumber;
import org.nabucco.framework.base.facade.datatype.business.PersonDays;
import org.nabucco.framework.base.facade.datatype.code.Code;
import org.nabucco.framework.base.facade.datatype.code.CodePath;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoCollectionState;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoList;
import org.nabucco.framework.base.facade.datatype.collection.NabuccoListImpl;
import org.nabucco.framework.base.facade.datatype.date.Date;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyAssociationType;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;
import org.nabucco.framework.base.facade.datatype.text.LongDescription;

/**
 * Scheduling<p/>Holds schedules of specific customer requests.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class Scheduling extends MultiTenantDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final SchedulingStatus STATUS_DEFAULT = SchedulingStatus.CREATED;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m1,1;", "l0,4000;u0,n;m1,1;", "m1,1;",
            "l3,12;u0,n;m1,1;", "l0,n;u0,n;m1,1;", "l0,n;u0,n;m0,1;", "l0,n;u0,n;m1,1;", "l1,n;u0,n;m0,1;", "m1,1;",
            "m1,1;", "m1,1;", "l0,n;u0,n;m1,1;", "m0,1;", "l0,n;u0,100;m1,1;", "m0,1;", "m1,1;", "m1,1;",
            "l0,255;u0,n;m0,1;", "m0,n;" };

    public static final String NAME = "name";

    public static final String DESCRIPTION = "description";

    public static final String STATUS = "status";

    public static final String OWNER = "owner";

    public static final String STARTDATE = "startDate";

    public static final String ENDDATE = "endDate";

    public static final String DUEDATE = "dueDate";

    public static final String OFFERNUMBER = "offerNumber";

    public static final String WORKLOAD = "workLoad";

    public static final String SCHEDULINGTYPE = "schedulingType";

    public static final String FEASABILITY = "feasability";

    public static final String VOLUME = "volume";

    public static final String CORPORATEDIVISION = "corporateDivision";

    public static final String ONSITE = "onsite";

    public static final String OFFERSTATE = "offerState";

    public static final String PRIORITY = "priority";

    public static final String PROJECTROLE = "projectRole";

    public static final String CREATOR = "creator";

    public static final String STAFFLIST = "staffList";

    /** The name of the scheduling. */
    private Name name;

    /** The description of the scheduling. */
    private LongDescription description;

    /** The scheduling status. */
    private SchedulingStatus status;

    /** The owner of the scheduling. */
    private Owner owner;

    /** The starting date of this scheduling. */
    private Date startDate;

    /** The end date of this scheduling. */
    private Date endDate;

    /** The due date of this scheduling. */
    private Date dueDate;

    /** Number of the related offer. */
    private OfferNumber offerNumber;

    /** The load of work planned for this scheduling. */
    private Code workLoad;

    private Long workLoadRefId;

    protected static final String WORKLOAD_CODEPATH = "nabucco.business.scheduling.workload";

    /** The type this scheduling. */
    private Code schedulingType;

    private Long schedulingTypeRefId;

    protected static final String SCHEDULINGTYPE_CODEPATH = "nabucco.business.scheduling.type";

    /** The feasability of success. */
    private Code feasability;

    private Long feasabilityRefId;

    protected static final String FEASABILITY_CODEPATH = "nabucco.business.scheduling.feasability";

    /** The amount of person days for of this scheduling. */
    private PersonDays volume;

    /** The corporate division. */
    private Code corporateDivision;

    private Long corporateDivisionRefId;

    protected static final String CORPORATEDIVISION_CODEPATH = "nabucco.business.organization.sector";

    /** The percent project that take place by the customer */
    private Percentage onsite;

    /** The state of the offer */
    private Code offerState;

    private Long offerStateRefId;

    protected static final String OFFERSTATE_CODEPATH = "nabucco.business.scheduling.offerstate";

    /** The priority of this scheduling. */
    private Code priority;

    private Long priorityRefId;

    protected static final String PRIORITY_CODEPATH = "nabucco.business.scheduling.priority";

    /** Role in the selected project. */
    private Code projectRole;

    private Long projectRoleRefId;

    protected static final String PROJECTROLE_CODEPATH = "nabucco.business.project.projectposition.role";

    /** Name of the creator of the scheduling. */
    private Name creator;

    /** The personal staffing of a scheduling. */
    private NabuccoList<Staffing> staffList;

    /** Constructs a new Scheduling instance. */
    public Scheduling() {
        super();
        this.initDefaults();
    }

    /** InitDefaults. */
    private void initDefaults() {
        status = STATUS_DEFAULT;
    }

    /**
     * CloneObject.
     *
     * @param clone the Scheduling.
     */
    protected void cloneObject(Scheduling clone) {
        super.cloneObject(clone);
        if ((this.getName() != null)) {
            clone.setName(this.getName().cloneObject());
        }
        if ((this.getDescription() != null)) {
            clone.setDescription(this.getDescription().cloneObject());
        }
        clone.setStatus(this.getStatus());
        if ((this.getOwner() != null)) {
            clone.setOwner(this.getOwner().cloneObject());
        }
        if ((this.getStartDate() != null)) {
            clone.setStartDate(this.getStartDate().cloneObject());
        }
        if ((this.getEndDate() != null)) {
            clone.setEndDate(this.getEndDate().cloneObject());
        }
        if ((this.getDueDate() != null)) {
            clone.setDueDate(this.getDueDate().cloneObject());
        }
        if ((this.getOfferNumber() != null)) {
            clone.setOfferNumber(this.getOfferNumber().cloneObject());
        }
        if ((this.getWorkLoad() != null)) {
            clone.setWorkLoad(this.getWorkLoad().cloneObject());
        }
        if ((this.getWorkLoadRefId() != null)) {
            clone.setWorkLoadRefId(this.getWorkLoadRefId());
        }
        if ((this.getSchedulingType() != null)) {
            clone.setSchedulingType(this.getSchedulingType().cloneObject());
        }
        if ((this.getSchedulingTypeRefId() != null)) {
            clone.setSchedulingTypeRefId(this.getSchedulingTypeRefId());
        }
        if ((this.getFeasability() != null)) {
            clone.setFeasability(this.getFeasability().cloneObject());
        }
        if ((this.getFeasabilityRefId() != null)) {
            clone.setFeasabilityRefId(this.getFeasabilityRefId());
        }
        if ((this.getVolume() != null)) {
            clone.setVolume(this.getVolume().cloneObject());
        }
        if ((this.getCorporateDivision() != null)) {
            clone.setCorporateDivision(this.getCorporateDivision().cloneObject());
        }
        if ((this.getCorporateDivisionRefId() != null)) {
            clone.setCorporateDivisionRefId(this.getCorporateDivisionRefId());
        }
        if ((this.getOnsite() != null)) {
            clone.setOnsite(this.getOnsite().cloneObject());
        }
        if ((this.getOfferState() != null)) {
            clone.setOfferState(this.getOfferState().cloneObject());
        }
        if ((this.getOfferStateRefId() != null)) {
            clone.setOfferStateRefId(this.getOfferStateRefId());
        }
        if ((this.getPriority() != null)) {
            clone.setPriority(this.getPriority().cloneObject());
        }
        if ((this.getPriorityRefId() != null)) {
            clone.setPriorityRefId(this.getPriorityRefId());
        }
        if ((this.getProjectRole() != null)) {
            clone.setProjectRole(this.getProjectRole().cloneObject());
        }
        if ((this.getProjectRoleRefId() != null)) {
            clone.setProjectRoleRefId(this.getProjectRoleRefId());
        }
        if ((this.getCreator() != null)) {
            clone.setCreator(this.getCreator().cloneObject());
        }
        if ((this.staffList != null)) {
            clone.staffList = this.staffList.cloneCollection();
        }
    }

    /**
     * Getter for the StaffListJPA.
     *
     * @return the List<Staffing>.
     */
    List<Staffing> getStaffListJPA() {
        if ((this.staffList == null)) {
            this.staffList = new NabuccoListImpl<Staffing>(NabuccoCollectionState.LAZY);
        }
        return ((NabuccoListImpl<Staffing>) this.staffList).getDelegate();
    }

    /**
     * Setter for the StaffListJPA.
     *
     * @param staffList the List<Staffing>.
     */
    void setStaffListJPA(List<Staffing> staffList) {
        if ((this.staffList == null)) {
            this.staffList = new NabuccoListImpl<Staffing>(NabuccoCollectionState.LAZY);
        }
        ((NabuccoListImpl<Staffing>) this.staffList).setDelegate(staffList);
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(MultiTenantDatatype.class).getPropertyMap());
        propertyMap.put(NAME,
                PropertyDescriptorSupport.createBasetype(NAME, Name.class, 4, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(DESCRIPTION, PropertyDescriptorSupport.createBasetype(DESCRIPTION, LongDescription.class, 5,
                PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(STATUS, PropertyDescriptorSupport.createEnumeration(STATUS, SchedulingStatus.class, 6,
                PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(OWNER,
                PropertyDescriptorSupport.createBasetype(OWNER, Owner.class, 7, PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(STARTDATE,
                PropertyDescriptorSupport.createBasetype(STARTDATE, Date.class, 8, PROPERTY_CONSTRAINTS[4], false));
        propertyMap.put(ENDDATE,
                PropertyDescriptorSupport.createBasetype(ENDDATE, Date.class, 9, PROPERTY_CONSTRAINTS[5], false));
        propertyMap.put(DUEDATE,
                PropertyDescriptorSupport.createBasetype(DUEDATE, Date.class, 10, PROPERTY_CONSTRAINTS[6], false));
        propertyMap.put(OFFERNUMBER, PropertyDescriptorSupport.createBasetype(OFFERNUMBER, OfferNumber.class, 11,
                PROPERTY_CONSTRAINTS[7], false));
        propertyMap.put(WORKLOAD, PropertyDescriptorSupport.createDatatype(WORKLOAD, Code.class, 12,
                PROPERTY_CONSTRAINTS[8], false, PropertyAssociationType.COMPONENT, WORKLOAD_CODEPATH));
        propertyMap.put(SCHEDULINGTYPE, PropertyDescriptorSupport.createDatatype(SCHEDULINGTYPE, Code.class, 13,
                PROPERTY_CONSTRAINTS[9], false, PropertyAssociationType.COMPONENT, SCHEDULINGTYPE_CODEPATH));
        propertyMap.put(FEASABILITY, PropertyDescriptorSupport.createDatatype(FEASABILITY, Code.class, 14,
                PROPERTY_CONSTRAINTS[10], false, PropertyAssociationType.COMPONENT, FEASABILITY_CODEPATH));
        propertyMap
                .put(VOLUME, PropertyDescriptorSupport.createBasetype(VOLUME, PersonDays.class, 15,
                        PROPERTY_CONSTRAINTS[11], false));
        propertyMap.put(CORPORATEDIVISION, PropertyDescriptorSupport.createDatatype(CORPORATEDIVISION, Code.class, 16,
                PROPERTY_CONSTRAINTS[12], false, PropertyAssociationType.COMPONENT, CORPORATEDIVISION_CODEPATH));
        propertyMap
                .put(ONSITE, PropertyDescriptorSupport.createBasetype(ONSITE, Percentage.class, 17,
                        PROPERTY_CONSTRAINTS[13], false));
        propertyMap.put(OFFERSTATE, PropertyDescriptorSupport.createDatatype(OFFERSTATE, Code.class, 18,
                PROPERTY_CONSTRAINTS[14], false, PropertyAssociationType.COMPONENT, OFFERSTATE_CODEPATH));
        propertyMap.put(PRIORITY, PropertyDescriptorSupport.createDatatype(PRIORITY, Code.class, 19,
                PROPERTY_CONSTRAINTS[15], false, PropertyAssociationType.COMPONENT, PRIORITY_CODEPATH));
        propertyMap.put(PROJECTROLE, PropertyDescriptorSupport.createDatatype(PROJECTROLE, Code.class, 20,
                PROPERTY_CONSTRAINTS[16], false, PropertyAssociationType.COMPONENT, PROJECTROLE_CODEPATH));
        propertyMap.put(CREATOR,
                PropertyDescriptorSupport.createBasetype(CREATOR, Name.class, 21, PROPERTY_CONSTRAINTS[17], false));
        propertyMap.put(STAFFLIST, PropertyDescriptorSupport.createCollection(STAFFLIST, Staffing.class, 22,
                PROPERTY_CONSTRAINTS[18], false, PropertyAssociationType.COMPOSITION));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(NAME), this.name, null));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(DESCRIPTION), this.description, null));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(STATUS), this.getStatus(), null));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(OWNER), this.owner, null));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(STARTDATE), this.startDate, null));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(ENDDATE), this.endDate, null));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(DUEDATE), this.dueDate, null));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(OFFERNUMBER), this.offerNumber, null));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(WORKLOAD), this.getWorkLoad(),
                this.workLoadRefId));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(SCHEDULINGTYPE), this.getSchedulingType(),
                this.schedulingTypeRefId));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(FEASABILITY), this.getFeasability(),
                this.feasabilityRefId));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(VOLUME), this.volume, null));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(CORPORATEDIVISION),
                this.getCorporateDivision(), this.corporateDivisionRefId));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(ONSITE), this.onsite, null));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(OFFERSTATE), this.getOfferState(),
                this.offerStateRefId));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(PRIORITY), this.getPriority(),
                this.priorityRefId));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(PROJECTROLE), this.getProjectRole(),
                this.projectRoleRefId));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(CREATOR), this.creator, null));
        properties.add(super.createProperty(Scheduling.getPropertyDescriptor(STAFFLIST), this.staffList, null));
        return properties;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(NAME) && (property.getType() == Name.class))) {
            this.setName(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DESCRIPTION) && (property.getType() == LongDescription.class))) {
            this.setDescription(((LongDescription) property.getInstance()));
            return true;
        } else if ((property.getName().equals(STATUS) && (property.getType() == SchedulingStatus.class))) {
            this.setStatus(((SchedulingStatus) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OWNER) && (property.getType() == Owner.class))) {
            this.setOwner(((Owner) property.getInstance()));
            return true;
        } else if ((property.getName().equals(STARTDATE) && (property.getType() == Date.class))) {
            this.setStartDate(((Date) property.getInstance()));
            return true;
        } else if ((property.getName().equals(ENDDATE) && (property.getType() == Date.class))) {
            this.setEndDate(((Date) property.getInstance()));
            return true;
        } else if ((property.getName().equals(DUEDATE) && (property.getType() == Date.class))) {
            this.setDueDate(((Date) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OFFERNUMBER) && (property.getType() == OfferNumber.class))) {
            this.setOfferNumber(((OfferNumber) property.getInstance()));
            return true;
        } else if ((property.getName().equals(WORKLOAD) && (property.getType() == Code.class))) {
            this.setWorkLoad(((Code) property.getInstance()));
            return true;
        } else if ((property.getName().equals(SCHEDULINGTYPE) && (property.getType() == Code.class))) {
            this.setSchedulingType(((Code) property.getInstance()));
            return true;
        } else if ((property.getName().equals(FEASABILITY) && (property.getType() == Code.class))) {
            this.setFeasability(((Code) property.getInstance()));
            return true;
        } else if ((property.getName().equals(VOLUME) && (property.getType() == PersonDays.class))) {
            this.setVolume(((PersonDays) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CORPORATEDIVISION) && (property.getType() == Code.class))) {
            this.setCorporateDivision(((Code) property.getInstance()));
            return true;
        } else if ((property.getName().equals(ONSITE) && (property.getType() == Percentage.class))) {
            this.setOnsite(((Percentage) property.getInstance()));
            return true;
        } else if ((property.getName().equals(OFFERSTATE) && (property.getType() == Code.class))) {
            this.setOfferState(((Code) property.getInstance()));
            return true;
        } else if ((property.getName().equals(PRIORITY) && (property.getType() == Code.class))) {
            this.setPriority(((Code) property.getInstance()));
            return true;
        } else if ((property.getName().equals(PROJECTROLE) && (property.getType() == Code.class))) {
            this.setProjectRole(((Code) property.getInstance()));
            return true;
        } else if ((property.getName().equals(CREATOR) && (property.getType() == Name.class))) {
            this.setCreator(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(STAFFLIST) && (property.getType() == Staffing.class))) {
            this.staffList = ((NabuccoList<Staffing>) property.getInstance());
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
        final Scheduling other = ((Scheduling) obj);
        if ((this.name == null)) {
            if ((other.name != null))
                return false;
        } else if ((!this.name.equals(other.name)))
            return false;
        if ((this.description == null)) {
            if ((other.description != null))
                return false;
        } else if ((!this.description.equals(other.description)))
            return false;
        if ((this.status == null)) {
            if ((other.status != null))
                return false;
        } else if ((!this.status.equals(other.status)))
            return false;
        if ((this.owner == null)) {
            if ((other.owner != null))
                return false;
        } else if ((!this.owner.equals(other.owner)))
            return false;
        if ((this.startDate == null)) {
            if ((other.startDate != null))
                return false;
        } else if ((!this.startDate.equals(other.startDate)))
            return false;
        if ((this.endDate == null)) {
            if ((other.endDate != null))
                return false;
        } else if ((!this.endDate.equals(other.endDate)))
            return false;
        if ((this.dueDate == null)) {
            if ((other.dueDate != null))
                return false;
        } else if ((!this.dueDate.equals(other.dueDate)))
            return false;
        if ((this.offerNumber == null)) {
            if ((other.offerNumber != null))
                return false;
        } else if ((!this.offerNumber.equals(other.offerNumber)))
            return false;
        if ((this.workLoad == null)) {
            if ((other.workLoad != null))
                return false;
        } else if ((!this.workLoad.equals(other.workLoad)))
            return false;
        if ((this.workLoadRefId == null)) {
            if ((other.workLoadRefId != null))
                return false;
        } else if ((!this.workLoadRefId.equals(other.workLoadRefId)))
            return false;
        if ((this.schedulingType == null)) {
            if ((other.schedulingType != null))
                return false;
        } else if ((!this.schedulingType.equals(other.schedulingType)))
            return false;
        if ((this.schedulingTypeRefId == null)) {
            if ((other.schedulingTypeRefId != null))
                return false;
        } else if ((!this.schedulingTypeRefId.equals(other.schedulingTypeRefId)))
            return false;
        if ((this.feasability == null)) {
            if ((other.feasability != null))
                return false;
        } else if ((!this.feasability.equals(other.feasability)))
            return false;
        if ((this.feasabilityRefId == null)) {
            if ((other.feasabilityRefId != null))
                return false;
        } else if ((!this.feasabilityRefId.equals(other.feasabilityRefId)))
            return false;
        if ((this.volume == null)) {
            if ((other.volume != null))
                return false;
        } else if ((!this.volume.equals(other.volume)))
            return false;
        if ((this.corporateDivision == null)) {
            if ((other.corporateDivision != null))
                return false;
        } else if ((!this.corporateDivision.equals(other.corporateDivision)))
            return false;
        if ((this.corporateDivisionRefId == null)) {
            if ((other.corporateDivisionRefId != null))
                return false;
        } else if ((!this.corporateDivisionRefId.equals(other.corporateDivisionRefId)))
            return false;
        if ((this.onsite == null)) {
            if ((other.onsite != null))
                return false;
        } else if ((!this.onsite.equals(other.onsite)))
            return false;
        if ((this.offerState == null)) {
            if ((other.offerState != null))
                return false;
        } else if ((!this.offerState.equals(other.offerState)))
            return false;
        if ((this.offerStateRefId == null)) {
            if ((other.offerStateRefId != null))
                return false;
        } else if ((!this.offerStateRefId.equals(other.offerStateRefId)))
            return false;
        if ((this.priority == null)) {
            if ((other.priority != null))
                return false;
        } else if ((!this.priority.equals(other.priority)))
            return false;
        if ((this.priorityRefId == null)) {
            if ((other.priorityRefId != null))
                return false;
        } else if ((!this.priorityRefId.equals(other.priorityRefId)))
            return false;
        if ((this.projectRole == null)) {
            if ((other.projectRole != null))
                return false;
        } else if ((!this.projectRole.equals(other.projectRole)))
            return false;
        if ((this.projectRoleRefId == null)) {
            if ((other.projectRoleRefId != null))
                return false;
        } else if ((!this.projectRoleRefId.equals(other.projectRoleRefId)))
            return false;
        if ((this.creator == null)) {
            if ((other.creator != null))
                return false;
        } else if ((!this.creator.equals(other.creator)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.name == null) ? 0 : this.name.hashCode()));
        result = ((PRIME * result) + ((this.description == null) ? 0 : this.description.hashCode()));
        result = ((PRIME * result) + ((this.status == null) ? 0 : this.status.hashCode()));
        result = ((PRIME * result) + ((this.owner == null) ? 0 : this.owner.hashCode()));
        result = ((PRIME * result) + ((this.startDate == null) ? 0 : this.startDate.hashCode()));
        result = ((PRIME * result) + ((this.endDate == null) ? 0 : this.endDate.hashCode()));
        result = ((PRIME * result) + ((this.dueDate == null) ? 0 : this.dueDate.hashCode()));
        result = ((PRIME * result) + ((this.offerNumber == null) ? 0 : this.offerNumber.hashCode()));
        result = ((PRIME * result) + ((this.workLoad == null) ? 0 : this.workLoad.hashCode()));
        result = ((PRIME * result) + ((this.workLoadRefId == null) ? 0 : this.workLoadRefId.hashCode()));
        result = ((PRIME * result) + ((this.schedulingType == null) ? 0 : this.schedulingType.hashCode()));
        result = ((PRIME * result) + ((this.schedulingTypeRefId == null) ? 0 : this.schedulingTypeRefId.hashCode()));
        result = ((PRIME * result) + ((this.feasability == null) ? 0 : this.feasability.hashCode()));
        result = ((PRIME * result) + ((this.feasabilityRefId == null) ? 0 : this.feasabilityRefId.hashCode()));
        result = ((PRIME * result) + ((this.volume == null) ? 0 : this.volume.hashCode()));
        result = ((PRIME * result) + ((this.corporateDivision == null) ? 0 : this.corporateDivision.hashCode()));
        result = ((PRIME * result) + ((this.corporateDivisionRefId == null) ? 0 : this.corporateDivisionRefId
                .hashCode()));
        result = ((PRIME * result) + ((this.onsite == null) ? 0 : this.onsite.hashCode()));
        result = ((PRIME * result) + ((this.offerState == null) ? 0 : this.offerState.hashCode()));
        result = ((PRIME * result) + ((this.offerStateRefId == null) ? 0 : this.offerStateRefId.hashCode()));
        result = ((PRIME * result) + ((this.priority == null) ? 0 : this.priority.hashCode()));
        result = ((PRIME * result) + ((this.priorityRefId == null) ? 0 : this.priorityRefId.hashCode()));
        result = ((PRIME * result) + ((this.projectRole == null) ? 0 : this.projectRole.hashCode()));
        result = ((PRIME * result) + ((this.projectRoleRefId == null) ? 0 : this.projectRoleRefId.hashCode()));
        result = ((PRIME * result) + ((this.creator == null) ? 0 : this.creator.hashCode()));
        return result;
    }

    @Override
    public Scheduling cloneObject() {
        Scheduling clone = new Scheduling();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * The name of the scheduling.
     *
     * @return the Name.
     */
    public Name getName() {
        return this.name;
    }

    /**
     * The name of the scheduling.
     *
     * @param name the Name.
     */
    public void setName(Name name) {
        this.name = name;
    }

    /**
     * The name of the scheduling.
     *
     * @param name the String.
     */
    public void setName(String name) {
        if ((this.name == null)) {
            if ((name == null)) {
                return;
            }
            this.name = new Name();
        }
        this.name.setValue(name);
    }

    /**
     * The description of the scheduling.
     *
     * @return the LongDescription.
     */
    public LongDescription getDescription() {
        return this.description;
    }

    /**
     * The description of the scheduling.
     *
     * @param description the LongDescription.
     */
    public void setDescription(LongDescription description) {
        this.description = description;
    }

    /**
     * The description of the scheduling.
     *
     * @param description the String.
     */
    public void setDescription(String description) {
        if ((this.description == null)) {
            if ((description == null)) {
                return;
            }
            this.description = new LongDescription();
        }
        this.description.setValue(description);
    }

    /**
     * The scheduling status.
     *
     * @return the SchedulingStatus.
     */
    public SchedulingStatus getStatus() {
        return this.status;
    }

    /**
     * The scheduling status.
     *
     * @param status the SchedulingStatus.
     */
    public void setStatus(SchedulingStatus status) {
        this.status = status;
    }

    /**
     * The scheduling status.
     *
     * @param status the String.
     */
    public void setStatus(String status) {
        if ((status == null)) {
            this.status = null;
        } else {
            this.status = SchedulingStatus.valueOf(status);
        }
    }

    /**
     * The owner of the scheduling.
     *
     * @return the Owner.
     */
    public Owner getOwner() {
        return this.owner;
    }

    /**
     * The owner of the scheduling.
     *
     * @param owner the Owner.
     */
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    /**
     * The owner of the scheduling.
     *
     * @param owner the String.
     */
    public void setOwner(String owner) {
        if ((this.owner == null)) {
            if ((owner == null)) {
                return;
            }
            this.owner = new Owner();
        }
        this.owner.setValue(owner);
    }

    /**
     * The starting date of this scheduling.
     *
     * @return the Date.
     */
    public Date getStartDate() {
        return this.startDate;
    }

    /**
     * The starting date of this scheduling.
     *
     * @param startDate the Date.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * The starting date of this scheduling.
     *
     * @param startDate the java.util.Date.
     */
    public void setStartDate(java.util.Date startDate) {
        if ((this.startDate == null)) {
            if ((startDate == null)) {
                return;
            }
            this.startDate = new Date();
        }
        this.startDate.setValue(startDate);
    }

    /**
     * The end date of this scheduling.
     *
     * @return the Date.
     */
    public Date getEndDate() {
        return this.endDate;
    }

    /**
     * The end date of this scheduling.
     *
     * @param endDate the Date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * The end date of this scheduling.
     *
     * @param endDate the java.util.Date.
     */
    public void setEndDate(java.util.Date endDate) {
        if ((this.endDate == null)) {
            if ((endDate == null)) {
                return;
            }
            this.endDate = new Date();
        }
        this.endDate.setValue(endDate);
    }

    /**
     * The due date of this scheduling.
     *
     * @return the Date.
     */
    public Date getDueDate() {
        return this.dueDate;
    }

    /**
     * The due date of this scheduling.
     *
     * @param dueDate the Date.
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * The due date of this scheduling.
     *
     * @param dueDate the java.util.Date.
     */
    public void setDueDate(java.util.Date dueDate) {
        if ((this.dueDate == null)) {
            if ((dueDate == null)) {
                return;
            }
            this.dueDate = new Date();
        }
        this.dueDate.setValue(dueDate);
    }

    /**
     * Number of the related offer.
     *
     * @return the OfferNumber.
     */
    public OfferNumber getOfferNumber() {
        return this.offerNumber;
    }

    /**
     * Number of the related offer.
     *
     * @param offerNumber the OfferNumber.
     */
    public void setOfferNumber(OfferNumber offerNumber) {
        this.offerNumber = offerNumber;
    }

    /**
     * Number of the related offer.
     *
     * @param offerNumber the String.
     */
    public void setOfferNumber(String offerNumber) {
        if ((this.offerNumber == null)) {
            if ((offerNumber == null)) {
                return;
            }
            this.offerNumber = new OfferNumber();
        }
        this.offerNumber.setValue(offerNumber);
    }

    /**
     * The load of work planned for this scheduling.
     *
     * @param workLoad the Code.
     */
    public void setWorkLoad(Code workLoad) {
        this.workLoad = workLoad;
        if ((workLoad != null)) {
            this.setWorkLoadRefId(workLoad.getId());
        } else {
            this.setWorkLoadRefId(null);
        }
    }

    /**
     * The load of work planned for this scheduling.
     *
     * @return the Code.
     */
    public Code getWorkLoad() {
        return this.workLoad;
    }

    /**
     * Getter for the WorkLoadRefId.
     *
     * @return the Long.
     */
    public Long getWorkLoadRefId() {
        return this.workLoadRefId;
    }

    /**
     * Setter for the WorkLoadRefId.
     *
     * @param workLoadRefId the Long.
     */
    public void setWorkLoadRefId(Long workLoadRefId) {
        this.workLoadRefId = workLoadRefId;
    }

    /**
     * The type this scheduling.
     *
     * @param schedulingType the Code.
     */
    public void setSchedulingType(Code schedulingType) {
        this.schedulingType = schedulingType;
        if ((schedulingType != null)) {
            this.setSchedulingTypeRefId(schedulingType.getId());
        } else {
            this.setSchedulingTypeRefId(null);
        }
    }

    /**
     * The type this scheduling.
     *
     * @return the Code.
     */
    public Code getSchedulingType() {
        return this.schedulingType;
    }

    /**
     * Getter for the SchedulingTypeRefId.
     *
     * @return the Long.
     */
    public Long getSchedulingTypeRefId() {
        return this.schedulingTypeRefId;
    }

    /**
     * Setter for the SchedulingTypeRefId.
     *
     * @param schedulingTypeRefId the Long.
     */
    public void setSchedulingTypeRefId(Long schedulingTypeRefId) {
        this.schedulingTypeRefId = schedulingTypeRefId;
    }

    /**
     * The feasability of success.
     *
     * @param feasability the Code.
     */
    public void setFeasability(Code feasability) {
        this.feasability = feasability;
        if ((feasability != null)) {
            this.setFeasabilityRefId(feasability.getId());
        } else {
            this.setFeasabilityRefId(null);
        }
    }

    /**
     * The feasability of success.
     *
     * @return the Code.
     */
    public Code getFeasability() {
        return this.feasability;
    }

    /**
     * Getter for the FeasabilityRefId.
     *
     * @return the Long.
     */
    public Long getFeasabilityRefId() {
        return this.feasabilityRefId;
    }

    /**
     * Setter for the FeasabilityRefId.
     *
     * @param feasabilityRefId the Long.
     */
    public void setFeasabilityRefId(Long feasabilityRefId) {
        this.feasabilityRefId = feasabilityRefId;
    }

    /**
     * The amount of person days for of this scheduling.
     *
     * @return the PersonDays.
     */
    public PersonDays getVolume() {
        return this.volume;
    }

    /**
     * The amount of person days for of this scheduling.
     *
     * @param volume the PersonDays.
     */
    public void setVolume(PersonDays volume) {
        this.volume = volume;
    }

    /**
     * The amount of person days for of this scheduling.
     *
     * @param volume the Integer.
     */
    public void setVolume(Integer volume) {
        if ((this.volume == null)) {
            if ((volume == null)) {
                return;
            }
            this.volume = new PersonDays();
        }
        this.volume.setValue(volume);
    }

    /**
     * The corporate division.
     *
     * @param corporateDivision the Code.
     */
    public void setCorporateDivision(Code corporateDivision) {
        this.corporateDivision = corporateDivision;
        if ((corporateDivision != null)) {
            this.setCorporateDivisionRefId(corporateDivision.getId());
        } else {
            this.setCorporateDivisionRefId(null);
        }
    }

    /**
     * The corporate division.
     *
     * @return the Code.
     */
    public Code getCorporateDivision() {
        return this.corporateDivision;
    }

    /**
     * Getter for the CorporateDivisionRefId.
     *
     * @return the Long.
     */
    public Long getCorporateDivisionRefId() {
        return this.corporateDivisionRefId;
    }

    /**
     * Setter for the CorporateDivisionRefId.
     *
     * @param corporateDivisionRefId the Long.
     */
    public void setCorporateDivisionRefId(Long corporateDivisionRefId) {
        this.corporateDivisionRefId = corporateDivisionRefId;
    }

    /**
     * The percent project that take place by the customer
     *
     * @return the Percentage.
     */
    public Percentage getOnsite() {
        return this.onsite;
    }

    /**
     * The percent project that take place by the customer
     *
     * @param onsite the Percentage.
     */
    public void setOnsite(Percentage onsite) {
        this.onsite = onsite;
    }

    /**
     * The percent project that take place by the customer
     *
     * @param onsite the java.math.BigDecimal.
     */
    public void setOnsite(java.math.BigDecimal onsite) {
        if ((this.onsite == null)) {
            if ((onsite == null)) {
                return;
            }
            this.onsite = new Percentage();
        }
        this.onsite.setValue(onsite);
    }

    /**
     * The state of the offer
     *
     * @param offerState the Code.
     */
    public void setOfferState(Code offerState) {
        this.offerState = offerState;
        if ((offerState != null)) {
            this.setOfferStateRefId(offerState.getId());
        } else {
            this.setOfferStateRefId(null);
        }
    }

    /**
     * The state of the offer
     *
     * @return the Code.
     */
    public Code getOfferState() {
        return this.offerState;
    }

    /**
     * Getter for the OfferStateRefId.
     *
     * @return the Long.
     */
    public Long getOfferStateRefId() {
        return this.offerStateRefId;
    }

    /**
     * Setter for the OfferStateRefId.
     *
     * @param offerStateRefId the Long.
     */
    public void setOfferStateRefId(Long offerStateRefId) {
        this.offerStateRefId = offerStateRefId;
    }

    /**
     * The priority of this scheduling.
     *
     * @param priority the Code.
     */
    public void setPriority(Code priority) {
        this.priority = priority;
        if ((priority != null)) {
            this.setPriorityRefId(priority.getId());
        } else {
            this.setPriorityRefId(null);
        }
    }

    /**
     * The priority of this scheduling.
     *
     * @return the Code.
     */
    public Code getPriority() {
        return this.priority;
    }

    /**
     * Getter for the PriorityRefId.
     *
     * @return the Long.
     */
    public Long getPriorityRefId() {
        return this.priorityRefId;
    }

    /**
     * Setter for the PriorityRefId.
     *
     * @param priorityRefId the Long.
     */
    public void setPriorityRefId(Long priorityRefId) {
        this.priorityRefId = priorityRefId;
    }

    /**
     * Role in the selected project.
     *
     * @param projectRole the Code.
     */
    public void setProjectRole(Code projectRole) {
        this.projectRole = projectRole;
        if ((projectRole != null)) {
            this.setProjectRoleRefId(projectRole.getId());
        } else {
            this.setProjectRoleRefId(null);
        }
    }

    /**
     * Role in the selected project.
     *
     * @return the Code.
     */
    public Code getProjectRole() {
        return this.projectRole;
    }

    /**
     * Getter for the ProjectRoleRefId.
     *
     * @return the Long.
     */
    public Long getProjectRoleRefId() {
        return this.projectRoleRefId;
    }

    /**
     * Setter for the ProjectRoleRefId.
     *
     * @param projectRoleRefId the Long.
     */
    public void setProjectRoleRefId(Long projectRoleRefId) {
        this.projectRoleRefId = projectRoleRefId;
    }

    /**
     * Name of the creator of the scheduling.
     *
     * @return the Name.
     */
    public Name getCreator() {
        return this.creator;
    }

    /**
     * Name of the creator of the scheduling.
     *
     * @param creator the Name.
     */
    public void setCreator(Name creator) {
        this.creator = creator;
    }

    /**
     * Name of the creator of the scheduling.
     *
     * @param creator the String.
     */
    public void setCreator(String creator) {
        if ((this.creator == null)) {
            if ((creator == null)) {
                return;
            }
            this.creator = new Name();
        }
        this.creator.setValue(creator);
    }

    /**
     * The personal staffing of a scheduling.
     *
     * @return the NabuccoList<Staffing>.
     */
    public NabuccoList<Staffing> getStaffList() {
        if ((this.staffList == null)) {
            this.staffList = new NabuccoListImpl<Staffing>(NabuccoCollectionState.INITIALIZED);
        }
        return this.staffList;
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Scheduling.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Scheduling.class).getAllProperties();
    }

    /**
     * Getter for the WorkLoadCodePath.
     *
     * @return the CodePath.
     */
    public static CodePath getWorkLoadCodePath() {
        return new CodePath(WORKLOAD_CODEPATH);
    }

    /**
     * Getter for the SchedulingTypeCodePath.
     *
     * @return the CodePath.
     */
    public static CodePath getSchedulingTypeCodePath() {
        return new CodePath(SCHEDULINGTYPE_CODEPATH);
    }

    /**
     * Getter for the FeasabilityCodePath.
     *
     * @return the CodePath.
     */
    public static CodePath getFeasabilityCodePath() {
        return new CodePath(FEASABILITY_CODEPATH);
    }

    /**
     * Getter for the CorporateDivisionCodePath.
     *
     * @return the CodePath.
     */
    public static CodePath getCorporateDivisionCodePath() {
        return new CodePath(CORPORATEDIVISION_CODEPATH);
    }

    /**
     * Getter for the OfferStateCodePath.
     *
     * @return the CodePath.
     */
    public static CodePath getOfferStateCodePath() {
        return new CodePath(OFFERSTATE_CODEPATH);
    }

    /**
     * Getter for the PriorityCodePath.
     *
     * @return the CodePath.
     */
    public static CodePath getPriorityCodePath() {
        return new CodePath(PRIORITY_CODEPATH);
    }

    /**
     * Getter for the ProjectRoleCodePath.
     *
     * @return the CodePath.
     */
    public static CodePath getProjectRoleCodePath() {
        return new CodePath(PROJECTROLE_CODEPATH);
    }
}
