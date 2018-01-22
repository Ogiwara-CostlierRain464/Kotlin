package jp.ogiwara.aileen3;

public class Users {
    public static class UserMinimal{
        public String uid;
        public String name;
        public String description;
        public String icon;
        public String cover;
        public Integer premium;
        public String token;
    }

    public static class UserSmall extends UserMinimal{
        public Integer unread_count;
        public Long last_chat_at;
        public Float lat;
        public Float lng;
        public Long located_date;
    }

    public static class UserMidium extends UserSmall{
        public UserSmall[] users;
        public String users_next_cursor;
    }

    public static class User extends UserMidium{
        public Long followed_date;
        public Long following_date;
        public Integer followers_count;
        public Long contacted_date;
        public Integer contacts_count;

        public Integer is_blocked;
        public Integer my_groups_count;

        @Override
        public String toString() {
            return String.format("name: %s,desc: %s,premium: %s,token: %s",name,description,premium,token);
        }
    }
}
