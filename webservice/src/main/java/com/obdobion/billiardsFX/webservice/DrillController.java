package com.obdobion.billiardsFX.webservice;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.obdobion.billiardsFX.model.DrillLookupReference;
import com.obdobion.billiardsFX.model.DrillMaster;

@Path("/drills")
public class DrillController
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<DrillLookupReference> getAvailableDrills()
    {
        return DrillMaster.availableDrills(null);
    }
}
