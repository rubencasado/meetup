package es.flink.meetupStreaming.jsonparser;

/**
 * Created by bigdata on 10/05/16.
 */
public class Member implements Comparable

{
    private String member_name;

    private String photo;

    private String member_id;

    public String getMember_name ()
    {
        return member_name;
    }

    public void setMember_name (String member_name)
    {
        this.member_name = member_name;
    }

    public String getPhoto ()
    {
        return photo;
    }

    public void setPhoto (String photo)
    {
        this.photo = photo;
    }

    public String getMember_id ()
    {
        return member_id;
    }

    public void setMember_id (String member_id)
    {
        this.member_id = member_id;
    }

    @Override
    public String toString()
    {
        return "Member_ClassPojo [member_name = "+member_name+", photo = "+photo+", member_id = "+member_id+"]";
    }

    @Override
    public int compareTo(Object o) {
        Member m = (Member) o;
        return this.member_id.compareTo(m.member_id);
    }
}

