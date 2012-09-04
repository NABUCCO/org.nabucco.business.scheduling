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
package org.nabucco.business.scheduling.impl.component;

/**
 * SchedulingComponentJndiNames<p/>Scheduling component for business schedulings.<p/>
 *
 * @version 1.0
 * @author Nicolas Moser, PRODYNA AG, 2011-08-23
 */
public interface SchedulingComponentJndiNames {

    final String COMPONENT_RELATION_SERVICE_LOCAL = "nabucco/org.nabucco.business.scheduling/org.nabucco.business.scheduling.facade.component.ComponentRelationService/local";

    final String COMPONENT_RELATION_SERVICE_REMOTE = "nabucco/org.nabucco.business.scheduling/org.nabucco.business.scheduling.facade.component.ComponentRelationService/remote";

    final String QUERY_FILTER_SERVICE_LOCAL = "nabucco/org.nabucco.business.scheduling/org.nabucco.business.scheduling.facade.component.QueryFilterService/local";

    final String QUERY_FILTER_SERVICE_REMOTE = "nabucco/org.nabucco.business.scheduling/org.nabucco.business.scheduling.facade.component.QueryFilterService/remote";

    final String MAINTAIN_SCHEDULING_LOCAL = "nabucco/org.nabucco.business.scheduling/org.nabucco.business.scheduling.facade.service.maintain.MaintainScheduling/local";

    final String MAINTAIN_SCHEDULING_REMOTE = "nabucco/org.nabucco.business.scheduling/org.nabucco.business.scheduling.facade.service.maintain.MaintainScheduling/remote";

    final String PRODUCE_SCHEDULING_LOCAL = "nabucco/org.nabucco.business.scheduling/org.nabucco.business.scheduling.facade.service.produce.ProduceScheduling/local";

    final String PRODUCE_SCHEDULING_REMOTE = "nabucco/org.nabucco.business.scheduling/org.nabucco.business.scheduling.facade.service.produce.ProduceScheduling/remote";

    final String RESOLVE_SCHEDULING_LOCAL = "nabucco/org.nabucco.business.scheduling/org.nabucco.business.scheduling.facade.service.resolve.ResolveScheduling/local";

    final String RESOLVE_SCHEDULING_REMOTE = "nabucco/org.nabucco.business.scheduling/org.nabucco.business.scheduling.facade.service.resolve.ResolveScheduling/remote";

    final String SEARCH_SCHEDULING_LOCAL = "nabucco/org.nabucco.business.scheduling/org.nabucco.business.scheduling.facade.service.search.SearchScheduling/local";

    final String SEARCH_SCHEDULING_REMOTE = "nabucco/org.nabucco.business.scheduling/org.nabucco.business.scheduling.facade.service.search.SearchScheduling/remote";
}
