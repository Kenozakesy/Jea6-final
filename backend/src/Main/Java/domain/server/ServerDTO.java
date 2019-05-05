package domain.server;

public class ServerDTO {

    private long id;
    private String name;

    private long userID;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }


    @Override
    public String toString() {
        return "ServerDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userID=" + userID +
                '}';
    }
}
