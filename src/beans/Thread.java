package beans;

import helpers.DateUtils;
import org.joda.time.DateTime;

public class Thread {
    private Integer id;
    private String subject;
    private Integer viewCount;
    private DateTime dateCreated;
    private DateTime dateLastPost;
    private Boolean active;
    private Integer userId;
    private Integer forumId;

    public String getDateCreatedFormatted() {
        return DateUtils.formatDate(dateCreated);
    }

    public String getDateLastPostFormatted() {
        return DateUtils.formatDate(dateLastPost);
    }

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

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public DateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(DateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public DateTime getDateLastPost() {
        return dateLastPost;
    }

    public void setDateLastPost(DateTime dateLastPost) {
        this.dateLastPost = dateLastPost;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getForumId() {
        return forumId;
    }

    public void setForumId(Integer forumId) {
        this.forumId = forumId;
    }
}
