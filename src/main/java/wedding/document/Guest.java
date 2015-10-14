package wedding.document;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by Shirney on 15/8/21.
 */
public class Guest {
    public static String ID_FIELD = "id";
    public static String NAME_FIELD = "name";
    public static String ISCOMING_FIELD = "isComing";
    public static String ADULTS_FIELD = "adults";
    public static String VEGETARIANS_FIELD = "vegetarians";
    public static String CHILDREN_FIELD = "children";
    public static String INVITATION_TYPE_FIELD = "invitationType";
    public static String ADDRESS_FIELD = "address";
    public static String EMAIL_ADDRESS_FIELD = "emailAddress";
    public static String COMMENT_FIELD = "comment";
    public static String FB_USER_NAME_FIELD = "fbUserName";

    public enum InvitationType {
        none,
        mail,
        email;
    }

    @JsonIgnore
    private String id = "";

    private String name = "";

    private boolean isComing = false;

    private int adults = 0;

    private int children = 0;

    private int vegetarians = 0;

    private InvitationType invitationType = InvitationType.none;

    private String address = "";

    private String emailAddress = "";

    private String comment = "";

    private String fbUserName = "";

    @JsonIgnore
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsComing() {
        return isComing;
    }

    public void setIsComing(boolean isComing) {
        this.isComing = isComing;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public InvitationType getInvitationType() {
        return invitationType;
    }

    public void setInvitationType(InvitationType invitationType) {
        this.invitationType = invitationType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getVegetarians() {
        return vegetarians;
    }

    public void setVegetarians(int vegetarians) {
        this.vegetarians = vegetarians;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFbUserName() {
        return fbUserName;
    }

    public void setFbUserName(String fbUserName) {
        this.fbUserName = fbUserName;
    }

    @JsonIgnore
    public GuestDBQuery buildDBObject() {
        final GuestDBQuery dbObject = new GuestDBQuery();
        dbObject.setID(this.id);
        dbObject.setName(this.name);
        dbObject.setAddress(this.address);
        dbObject.setAdults(this.adults);
        dbObject.setChildren(this.children);
        dbObject.setComing(this.isComing);
        dbObject.setComment(this.comment);
        dbObject.setInvitationType(this.invitationType);
        dbObject.setVegetarians(this.vegetarians);
        dbObject.setEmailAddress(this.emailAddress);
        dbObject.setFBUserName(this.fbUserName);
        return dbObject;
    }
}
