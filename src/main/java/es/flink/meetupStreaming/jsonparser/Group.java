package es.flink.meetupStreaming.jsonparser;

import java.util.ArrayList;

/**
 * Created by bigdata on 10/05/16.
 */
public class Group implements Comparable
{
    private String group_name;

    private String group_city;

    private String group_lat;

    private String group_urlname;

    private String group_id;

    private String group_country;

    private String group_lon;

    private ArrayList<Group_topics> group_topics;

    public String getGroup_name ()
    {
        return group_name;
    }

    public void setGroup_name (String group_name)
    {
        this.group_name = group_name;
    }

    public String getGroup_city ()
    {
        return group_city;
    }

    public void setGroup_city (String group_city)
    {
        this.group_city = group_city;
    }

    public String getGroup_lat ()
    {
        return group_lat;
    }

    public void setGroup_lat (String group_lat)
    {
        this.group_lat = group_lat;
    }

    public String getGroup_urlname ()
    {
        return group_urlname;
    }

    public void setGroup_urlname (String group_urlname)
    {
        this.group_urlname = group_urlname;
    }

    public String getGroup_id ()
    {
        return group_id;
    }

    public void setGroup_id (String group_id)
    {
        this.group_id = group_id;
    }

    public String getGroup_country ()
    {
        return group_country;
    }

    public void setGroup_country (String group_country)
    {
        this.group_country = group_country;
    }

    public String getGroup_lon ()
    {
        return group_lon;
    }

    public void setGroup_lon (String group_lon)
    {
        this.group_lon = group_lon;
    }

    public ArrayList<Group_topics> getGroup_topics ()
    {
        return group_topics;
    }

    public void setGroup_topics (ArrayList<Group_topics> group_topics)
    {
        this.group_topics = group_topics;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [group_name = "+group_name+", group_city = "+group_city+", group_lat = "+group_lat+", group_urlname = "+group_urlname+", group_id = "+group_id+", group_country = "+group_country+", group_lon = "+group_lon+", group_topics = "+group_topics+"]";
    }


    @Override
    public boolean equals (Object o){
        Group g = (Group) o;
        return this.getGroup_id()== g.getGroup_id();
    }

    @Override
    public int compareTo(Object o) {
        Group g = (Group) o;
        if (this.getGroup_id()==g.getGroup_id())
            return 0;
        else return 1;
    }

}

