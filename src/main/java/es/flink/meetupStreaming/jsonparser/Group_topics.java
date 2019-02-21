package es.flink.meetupStreaming.jsonparser;

/**
 * Created by bigdata on 10/05/16.
 */
public class Group_topics implements Comparable
{
    private String urlkey;

    private String topic_name;

    public String getUrlkey ()
    {
        return urlkey;
    }

    public void setUrlkey (String urlkey)
    {
        this.urlkey = urlkey;
    }

    public String getTopic_name ()
    {
        return topic_name;
    }

    public void setTopic_name (String topic_name)
    {
        this.topic_name = topic_name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [urlkey = "+urlkey+", topic_name = "+topic_name+"]";
    }

    @Override
    public boolean equals (Object o) {
        Group_topics g = (Group_topics) o;
        return this.getTopic_name() == g.getTopic_name() && this.getUrlkey() == g.getUrlkey();
    }

    @Override
    public int compareTo(Object o) {
        Group_topics g = (Group_topics) o;
        if (this.getTopic_name() == g.getTopic_name() && this.getUrlkey() == g.getUrlkey())
            return 0;
        else return 1;
    }
}