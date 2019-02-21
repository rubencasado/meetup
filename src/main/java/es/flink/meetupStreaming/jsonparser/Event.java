package es.flink.meetupStreaming.jsonparser;

/**
 * Created by bigdata on 10/05/16.
 */
public class Event
{
    private String time;

    private String event_url;

    private String event_id;

    private String event_name;

    public String getTime ()
    {
        return time;
    }

    public void setTime (String time)
    {
        this.time = time;
    }

    public String getEvent_url ()
    {
        return event_url;
    }

    public void setEvent_url (String event_url)
    {
        this.event_url = event_url;
    }

    public String getEvent_id ()
    {
        return event_id;
    }

    public void setEvent_id (String event_id)
    {
        this.event_id = event_id;
    }

    public String getEvent_name ()
    {
        return event_name;
    }

    public void setEvent_name (String event_name)
    {
        this.event_name = event_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [time = "+time+", event_url = "+event_url+", event_id = "+event_id+", event_name = "+event_name+"]";
    }
}

