package wedding.document;

import com.mongodb.BasicDBObject;

/**
 * Created by Shirney on 15/9/4.
 */
public class GuestDBFields extends BasicDBObject {

    public GuestDBFields() {
        this.put(Guest.ID_FIELD, 1);
        this.put(Guest.ADDRESS_FIELD, 1);
        this.put(Guest.ADULTS_FIELD, 1);
        this.put(Guest.CHILDREN_FIELD, 1);
        this.put(Guest.COMMENT_FIELD, 1);
        this.put(Guest.INVITATION_TYPE_FIELD, 1);
        this.put(Guest.ISCOMING_FIELD, 1);
        this.put(Guest.NAME_FIELD, 1);
        this.put(Guest.VEGETARIANS_FIELD, 1);
        this.put(Guest.EMAIL_ADDRESS_FIELD, 1);
        this.put("_id", 0);
    }
}
