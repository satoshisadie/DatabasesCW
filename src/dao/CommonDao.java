package dao;

import beans.ForumRule;
import beans.Tag;
import beans.TopicFollower;
import beans.TopicTag;

import java.util.List;

public interface CommonDao {
    public List<Tag> getAllTags();
    public List<Tag> getTagsByTopic(int threadId);
    public List<TopicTag> getTopicTagForTopics(List<Integer> threadsIds);
    public void attachTags(int threadId, List<Integer> tagsIds);
    public List<ForumRule> getAllRules();
    public void attachViolation(int postId, int ruleId, String violationComment);
    public List<TopicFollower> getTopicFollowersByUserId(int userId);
}
