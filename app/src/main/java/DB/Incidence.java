package DB;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Incidence {
    protected String title;
    protected String priority;
    protected int id;

    protected String status = "UNFIXED";

    protected String description;
    protected long unixDate;



    public Incidence(String title, String priority){
        this.title = title;
        this.priority = priority;
        this.status=status;
        this.unixDate = System.currentTimeMillis() / 1000L;
    }

    public String getTitle(){
        return title;
    }

    public String getPriority(){
        return priority;
    }

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String statusID) {
        this.status = statusID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public long getUnixDate() {
        return this.unixDate;
    }

    public void setUnixDate(long unixDate) {
        this.unixDate = unixDate;
    }

    public String getDate() {
        Date date = new Date(this.unixDate * 1000);
        String date_format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(date);

        return date_format;
    }
}


