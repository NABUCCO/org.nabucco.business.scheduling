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
import org.nabucco.business.scheduling.facade.datatype.StaffingStatus;
import org.nabucco.framework.base.facade.datatype.Datatype;
import org.nabucco.framework.base.facade.datatype.MultiTenantDatatype;
import org.nabucco.framework.base.facade.datatype.Name;
import org.nabucco.framework.base.facade.datatype.date.Date;
import org.nabucco.framework.base.facade.datatype.documentation.Comment;
import org.nabucco.framework.base.facade.datatype.property.NabuccoProperty;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyContainer;
import org.nabucco.framework.base.facade.datatype.property.NabuccoPropertyDescriptor;
import org.nabucco.framework.base.facade.datatype.property.PropertyCache;
import org.nabucco.framework.base.facade.datatype.property.PropertyDescriptorSupport;

/**
 * Staffing<p/>Holds personal staffing of a scheduling.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public class Staffing extends MultiTenantDatatype implements Datatype {

    private static final long serialVersionUID = 1L;

    private static final StaffingStatus STATUS_DEFAULT = StaffingStatus.CREATED;

    private static final String[] PROPERTY_CONSTRAINTS = { "l0,255;u0,n;m0,1;", "l0,100000;u0,n;m0,1;", "m1,1;",
            "l0,n;u0,n;m0,1;", "l0,n;u0,n;m0,1;", "l0,n;u0,n;m0,1;" };

    public static final String SUMMARY = "summary";

    public static final String COMMENT = "comment";

    public static final String STATUS = "status";

    public static final String INTERVIEWDATE = "interviewDate";

    public static final String POSSIBLESTARTDATE = "possibleStartDate";

    public static final String PROJECTENDDATE = "projectEndDate";

    /** Name of the staffing. */
    private Name summary;

    /** Description of the staffing. */
    private Comment comment;

    /** Status of the staffing. */
    private StaffingStatus status;

    /** Date of the customer interview. */
    private Date interviewDate;

    /** The earliest possible start date */
    private Date possibleStartDate;

    /** The project end date */
    private Date projectEndDate;

    /** Constructs a new Staffing instance. */
    public Staffing() {
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
     * @param clone the Staffing.
     */
    protected void cloneObject(Staffing clone) {
        super.cloneObject(clone);
        if ((this.getSummary() != null)) {
            clone.setSummary(this.getSummary().cloneObject());
        }
        if ((this.getComment() != null)) {
            clone.setComment(this.getComment().cloneObject());
        }
        clone.setStatus(this.getStatus());
        if ((this.getInterviewDate() != null)) {
            clone.setInterviewDate(this.getInterviewDate().cloneObject());
        }
        if ((this.getPossibleStartDate() != null)) {
            clone.setPossibleStartDate(this.getPossibleStartDate().cloneObject());
        }
        if ((this.getProjectEndDate() != null)) {
            clone.setProjectEndDate(this.getProjectEndDate().cloneObject());
        }
    }

    /**
     * CreatePropertyContainer.
     *
     * @return the NabuccoPropertyContainer.
     */
    protected static NabuccoPropertyContainer createPropertyContainer() {
        Map<String, NabuccoPropertyDescriptor> propertyMap = new HashMap<String, NabuccoPropertyDescriptor>();
        propertyMap.putAll(PropertyCache.getInstance().retrieve(MultiTenantDatatype.class).getPropertyMap());
        propertyMap.put(SUMMARY,
                PropertyDescriptorSupport.createBasetype(SUMMARY, Name.class, 4, PROPERTY_CONSTRAINTS[0], false));
        propertyMap.put(COMMENT,
                PropertyDescriptorSupport.createBasetype(COMMENT, Comment.class, 5, PROPERTY_CONSTRAINTS[1], false));
        propertyMap.put(STATUS, PropertyDescriptorSupport.createEnumeration(STATUS, StaffingStatus.class, 6,
                PROPERTY_CONSTRAINTS[2], false));
        propertyMap.put(INTERVIEWDATE,
                PropertyDescriptorSupport.createBasetype(INTERVIEWDATE, Date.class, 7, PROPERTY_CONSTRAINTS[3], false));
        propertyMap.put(POSSIBLESTARTDATE, PropertyDescriptorSupport.createBasetype(POSSIBLESTARTDATE, Date.class, 8,
                PROPERTY_CONSTRAINTS[4], false));
        propertyMap
                .put(PROJECTENDDATE, PropertyDescriptorSupport.createBasetype(PROJECTENDDATE, Date.class, 9,
                        PROPERTY_CONSTRAINTS[5], false));
        return new NabuccoPropertyContainer(propertyMap);
    }

    @Override
    public void init() {
        this.initDefaults();
    }

    @Override
    public Set<NabuccoProperty> getProperties() {
        Set<NabuccoProperty> properties = super.getProperties();
        properties.add(super.createProperty(Staffing.getPropertyDescriptor(SUMMARY), this.summary, null));
        properties.add(super.createProperty(Staffing.getPropertyDescriptor(COMMENT), this.comment, null));
        properties.add(super.createProperty(Staffing.getPropertyDescriptor(STATUS), this.getStatus(), null));
        properties.add(super.createProperty(Staffing.getPropertyDescriptor(INTERVIEWDATE), this.interviewDate, null));
        properties.add(super.createProperty(Staffing.getPropertyDescriptor(POSSIBLESTARTDATE), this.possibleStartDate,
                null));
        properties.add(super.createProperty(Staffing.getPropertyDescriptor(PROJECTENDDATE), this.projectEndDate, null));
        return properties;
    }

    @Override
    public boolean setProperty(NabuccoProperty property) {
        if (super.setProperty(property)) {
            return true;
        }
        if ((property.getName().equals(SUMMARY) && (property.getType() == Name.class))) {
            this.setSummary(((Name) property.getInstance()));
            return true;
        } else if ((property.getName().equals(COMMENT) && (property.getType() == Comment.class))) {
            this.setComment(((Comment) property.getInstance()));
            return true;
        } else if ((property.getName().equals(STATUS) && (property.getType() == StaffingStatus.class))) {
            this.setStatus(((StaffingStatus) property.getInstance()));
            return true;
        } else if ((property.getName().equals(INTERVIEWDATE) && (property.getType() == Date.class))) {
            this.setInterviewDate(((Date) property.getInstance()));
            return true;
        } else if ((property.getName().equals(POSSIBLESTARTDATE) && (property.getType() == Date.class))) {
            this.setPossibleStartDate(((Date) property.getInstance()));
            return true;
        } else if ((property.getName().equals(PROJECTENDDATE) && (property.getType() == Date.class))) {
            this.setProjectEndDate(((Date) property.getInstance()));
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
        final Staffing other = ((Staffing) obj);
        if ((this.summary == null)) {
            if ((other.summary != null))
                return false;
        } else if ((!this.summary.equals(other.summary)))
            return false;
        if ((this.comment == null)) {
            if ((other.comment != null))
                return false;
        } else if ((!this.comment.equals(other.comment)))
            return false;
        if ((this.status == null)) {
            if ((other.status != null))
                return false;
        } else if ((!this.status.equals(other.status)))
            return false;
        if ((this.interviewDate == null)) {
            if ((other.interviewDate != null))
                return false;
        } else if ((!this.interviewDate.equals(other.interviewDate)))
            return false;
        if ((this.possibleStartDate == null)) {
            if ((other.possibleStartDate != null))
                return false;
        } else if ((!this.possibleStartDate.equals(other.possibleStartDate)))
            return false;
        if ((this.projectEndDate == null)) {
            if ((other.projectEndDate != null))
                return false;
        } else if ((!this.projectEndDate.equals(other.projectEndDate)))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = super.hashCode();
        result = ((PRIME * result) + ((this.summary == null) ? 0 : this.summary.hashCode()));
        result = ((PRIME * result) + ((this.comment == null) ? 0 : this.comment.hashCode()));
        result = ((PRIME * result) + ((this.status == null) ? 0 : this.status.hashCode()));
        result = ((PRIME * result) + ((this.interviewDate == null) ? 0 : this.interviewDate.hashCode()));
        result = ((PRIME * result) + ((this.possibleStartDate == null) ? 0 : this.possibleStartDate.hashCode()));
        result = ((PRIME * result) + ((this.projectEndDate == null) ? 0 : this.projectEndDate.hashCode()));
        return result;
    }

    @Override
    public Staffing cloneObject() {
        Staffing clone = new Staffing();
        this.cloneObject(clone);
        return clone;
    }

    /**
     * Name of the staffing.
     *
     * @return the Name.
     */
    public Name getSummary() {
        return this.summary;
    }

    /**
     * Name of the staffing.
     *
     * @param summary the Name.
     */
    public void setSummary(Name summary) {
        this.summary = summary;
    }

    /**
     * Name of the staffing.
     *
     * @param summary the String.
     */
    public void setSummary(String summary) {
        if ((this.summary == null)) {
            if ((summary == null)) {
                return;
            }
            this.summary = new Name();
        }
        this.summary.setValue(summary);
    }

    /**
     * Description of the staffing.
     *
     * @return the Comment.
     */
    public Comment getComment() {
        return this.comment;
    }

    /**
     * Description of the staffing.
     *
     * @param comment the Comment.
     */
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    /**
     * Description of the staffing.
     *
     * @param comment the String.
     */
    public void setComment(String comment) {
        if ((this.comment == null)) {
            if ((comment == null)) {
                return;
            }
            this.comment = new Comment();
        }
        this.comment.setValue(comment);
    }

    /**
     * Status of the staffing.
     *
     * @return the StaffingStatus.
     */
    public StaffingStatus getStatus() {
        return this.status;
    }

    /**
     * Status of the staffing.
     *
     * @param status the StaffingStatus.
     */
    public void setStatus(StaffingStatus status) {
        this.status = status;
    }

    /**
     * Status of the staffing.
     *
     * @param status the String.
     */
    public void setStatus(String status) {
        if ((status == null)) {
            this.status = null;
        } else {
            this.status = StaffingStatus.valueOf(status);
        }
    }

    /**
     * Date of the customer interview.
     *
     * @return the Date.
     */
    public Date getInterviewDate() {
        return this.interviewDate;
    }

    /**
     * Date of the customer interview.
     *
     * @param interviewDate the Date.
     */
    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    /**
     * Date of the customer interview.
     *
     * @param interviewDate the java.util.Date.
     */
    public void setInterviewDate(java.util.Date interviewDate) {
        if ((this.interviewDate == null)) {
            if ((interviewDate == null)) {
                return;
            }
            this.interviewDate = new Date();
        }
        this.interviewDate.setValue(interviewDate);
    }

    /**
     * The earliest possible start date
     *
     * @return the Date.
     */
    public Date getPossibleStartDate() {
        return this.possibleStartDate;
    }

    /**
     * The earliest possible start date
     *
     * @param possibleStartDate the Date.
     */
    public void setPossibleStartDate(Date possibleStartDate) {
        this.possibleStartDate = possibleStartDate;
    }

    /**
     * The earliest possible start date
     *
     * @param possibleStartDate the java.util.Date.
     */
    public void setPossibleStartDate(java.util.Date possibleStartDate) {
        if ((this.possibleStartDate == null)) {
            if ((possibleStartDate == null)) {
                return;
            }
            this.possibleStartDate = new Date();
        }
        this.possibleStartDate.setValue(possibleStartDate);
    }

    /**
     * The project end date
     *
     * @return the Date.
     */
    public Date getProjectEndDate() {
        return this.projectEndDate;
    }

    /**
     * The project end date
     *
     * @param projectEndDate the Date.
     */
    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    /**
     * The project end date
     *
     * @param projectEndDate the java.util.Date.
     */
    public void setProjectEndDate(java.util.Date projectEndDate) {
        if ((this.projectEndDate == null)) {
            if ((projectEndDate == null)) {
                return;
            }
            this.projectEndDate = new Date();
        }
        this.projectEndDate.setValue(projectEndDate);
    }

    /**
     * Getter for the PropertyDescriptor.
     *
     * @param propertyName the String.
     * @return the NabuccoPropertyDescriptor.
     */
    public static NabuccoPropertyDescriptor getPropertyDescriptor(String propertyName) {
        return PropertyCache.getInstance().retrieve(Staffing.class).getProperty(propertyName);
    }

    /**
     * Getter for the PropertyDescriptorList.
     *
     * @return the List<NabuccoPropertyDescriptor>.
     */
    public static List<NabuccoPropertyDescriptor> getPropertyDescriptorList() {
        return PropertyCache.getInstance().retrieve(Staffing.class).getAllProperties();
    }
}
