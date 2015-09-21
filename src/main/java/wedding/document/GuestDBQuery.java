package wedding.document;

import com.mongodb.BasicDBObject;

/**
 * Created by Shirney on 15/8/21.
 */
public class GuestDBQuery extends BasicDBObject {

    public void setID(String id) {
        this.append(Guest.ID_FIELD, id);
    }

    public void setName(String name) {
        this.append(Guest.NAME_FIELD, name);
    }

    public void setComing(boolean isComing) {
        this.append(Guest.ISCOMING_FIELD, isComing);
    }

    public void setAdults(int adults) {
        this.append(Guest.ADULTS_FIELD, adults);
    }

    public void setVegetarians(int vegetarians) {
        this.append(Guest.VEGETARIANS_FIELD, vegetarians);
    }

    public void setChildren(int children) {
        this.append(Guest.CHILDREN_FIELD, children);
    }

    public void setInvitationType(Guest.InvitationType invitationType) {
        this.append(Guest.INVITATION_TYPE_FIELD, invitationType.toString());
    }

    public void setAddress(String address) {
        this.append(Guest.ADDRESS_FIELD, address);
    }

    public void setEmailAddress(String email) {
        this.append(Guest.EMAIL_ADDRESS_FIELD, email);
    }

    public void setComment(String comment) {
        this.append(Guest.COMMENT_FIELD, comment);
    }
}
