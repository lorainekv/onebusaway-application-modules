/**
 * Copyright (C) 2011 Brian Ferris <bdferris@onebusaway.org>
 * Copyright (C) 2011 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onebusaway.transit_data_federation.impl.service_alerts;

import java.util.HashSet;
import java.util.Set;

import org.onebusaway.gtfs.model.AgencyAndId;
import org.onebusaway.transit_data_federation.services.service_alerts.ServiceAlerts.Affects;
import org.onebusaway.transit_data_federation.services.service_alerts.ServiceAlerts.ServiceAlert;

class AffectsTripKeyFactory implements AffectsKeyFactory<AgencyAndId> {

  public static final AffectsTripKeyFactory INSTANCE = new AffectsTripKeyFactory();

  @Override
  public Set<AgencyAndId> getKeysForAffects(ServiceAlert serviceAlert) {

    Set<AgencyAndId> tripIds = new HashSet<AgencyAndId>();

    for (Affects affects : serviceAlert.getAffectsList()) {
      if (affects.hasTripId()
          && !(affects.hasAgencyId() || affects.hasDirectionId()
              || affects.hasRouteId() || affects.hasStopId())) {
        AgencyAndId tripId = ServiceAlertLibrary.agencyAndId(affects.getTripId());
        tripIds.add(tripId);
      }
    }

    return tripIds;
  }
}