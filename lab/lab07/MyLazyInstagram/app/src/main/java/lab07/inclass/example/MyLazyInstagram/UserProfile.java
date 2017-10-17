package lab07.inclass.example.MyLazyInstagram;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 10/6/2017 AD.
 */

public class UserProfile {
    private String user;
    private String urlProfile;
    private String post;
    private String following;
    private String follower;
    private String bio;
    private List<Posts> posts;

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    public String getUrlProfile() {
        return urlProfile;
    }

    public void setUrlProfile(String urlProfile) {
        this.urlProfile = urlProfile;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String followe) {
        this.follower = followe;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
