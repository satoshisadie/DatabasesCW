package dao;

import beans.ForumRule;
import beans.Tag;
import beans.ThreadTag;

import java.util.List;

public interface CommonDao {
    public List<Tag> getAllTags();
    public List<ThreadTag> getThreadTagForThreads(List<Integer> threadsIds);
    public void attachTags(int threadId, List<Integer> tagsIds);
    public List<ForumRule> getAllRules();
    public void attachViolation(int postId, int ruleId);
}
