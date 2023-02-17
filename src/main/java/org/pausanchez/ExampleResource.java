package org.pausanchez;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExampleResource {
    private final Set<Quark> quarks = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @POST
    public Set<Quark> add(Quark quark){
        quarks.add(quark);
        return quarks;
    }

    @GET
    public Set<Quark> list(){return quarks;}

    @DELETE
    public Set<Quark> delete(Quark quark){
        quarks.removeIf(existingQuark-> existingQuark.name.contains(quark.name));
        return quarks;
    }

    public static class Quark{
        private String name;
        private String description;

        public Quark(String name, String description){
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}