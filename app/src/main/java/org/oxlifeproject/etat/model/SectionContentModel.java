package org.oxlifeproject.etat.model;

import java.io.Serializable;

/**
 * Created by Tuti on 31/12/2017.
 */

public class SectionContentModel implements Serializable {

    private String title, content, videoURL;
    private boolean quiz, assessment, hasChildren, video;
    private int id, section;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public boolean isQuiz() {
        return quiz;
    }

    public void setQuiz(boolean quiz) {
        this.quiz = quiz;
    }

    public boolean isAssessment() {
        return assessment;
    }

    public void setAssessment(boolean assessment) {
        this.assessment = assessment;
    }

    public boolean isHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }



}
