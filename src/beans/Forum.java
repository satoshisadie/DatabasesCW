package beans;

public class Forum {
    Integer id;
    String name;
    String description;
    Integer parentForum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentForum() {
        return parentForum;
    }

    public void setParentForum(Integer parentForum) {
        this.parentForum = parentForum;
    }
}
