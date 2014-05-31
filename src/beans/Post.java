package beans;

import helpers.DateUtils;
import org.joda.time.DateTime;

public class Post {
    private Integer id;
    private String message;
    private Integer rating;
    private DateTime dateCreated;
    private Integer userId;
    private Integer topicId;
    private Integer repliedTo;

    public String getDateCreatedFormatted() {
        return DateUtils.formatDate(dateCreated);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getRepliedTo() {
        return repliedTo;
    }

    public void setRepliedTo(Integer repliedTo) {
        this.repliedTo = repliedTo;
    }
}
