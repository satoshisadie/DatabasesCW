package beans;

import org.joda.time.DateTime;

public class Post {
    private Integer id;
    private String subject;
    private String message;
    private DateTime dateCreated;
    private Integer userId;
    private Integer threadId;
    private Integer repliedTo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public Integer getThreadId() {
        return threadId;
    }

    public void setThreadId(Integer threadId) {
        this.threadId = threadId;
    }

    public Integer getRepliedTo() {
        return repliedTo;
    }

    public void setRepliedTo(Integer repliedTo) {
        this.repliedTo = repliedTo;
    }
}
