package es.flink.meetupStreaming.jsonparser;

/**
 * Created by bigdata on 10/05/16.
 */
public class Venue
{
    private String lon;

    private String venue_name;

    private String venue_id;

    private String lat;

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public String getVenue_name ()
    {
        return venue_name;
    }

    public void setVenue_name (String venue_name)
    {
        this.venue_name = venue_name;
    }

    public String getVenue_id ()
    {
        return venue_id;
    }

    public void setVenue_id (String venue_id)
    {
        this.venue_id = venue_id;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString()
    {
        return "Venune_ClassPojo [lon = "+lon+", venue_name = "+venue_name+", venue_id = "+venue_id+", lat = "+lat+"]";
    }

}

