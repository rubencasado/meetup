package es.flink.meetupStreaming.jsonparser;

/**
 * Created by bigdata on 10/05/16.
 */
public class MeetupRSVGevent
{
    private Member member;

    private String response;

    private String visibility;

    private Event event;

    private String mtime;

    private String guests;

    private String rsvp_id;

    private Group group;

    private Venue venue;


    public Member getMember ()
    {
        return member;
    }

    public void setMember (Member member)
    {
        this.member = member;
    }

    public String getResponse ()
    {
        return response;
    }

    public void setResponse (String response)
    {
        this.response = response;
    }

    public String getVisibility ()
    {
        return visibility;
    }

    public void setVisibility (String visibility)
    {
        this.visibility = visibility;
    }

    public Event getEvent ()
    {
        return event;
    }

    public void setEvent (Event event)
    {
        this.event = event;
    }

    public String getMtime ()
    {
        return mtime;
    }

    public void setMtime (String mtime)
    {
        this.mtime = mtime;
    }

    public String getGuests ()
    {
        return guests;
    }

    public void setGuests (String guests)
    {
        this.guests = guests;
    }

    public String getRsvp_id ()
    {
        return rsvp_id;
    }

    public void setRsvp_id (String rsvp_id)
    {
        this.rsvp_id = rsvp_id;
    }

    public Group getGroup ()
    {
        return group;
    }

    public void setGroup (Group group)
    {
        this.group = group;
    }

    public Venue getVenue ()
    {
        return venue;
    }

    public void setVenue (Venue venue)
    {
        this.venue = venue;
    }

    @Override
    public String toString()
    {
        return "MeetupRSVGevent_lassPojo [member = "+member+", response = "+response+", visibility = "+visibility+", event = "+event+", mtime = "+mtime+", guests = "+guests+", rsvp_id = "+rsvp_id+", group = "+group+", venue = "+venue+"]";
    }
}