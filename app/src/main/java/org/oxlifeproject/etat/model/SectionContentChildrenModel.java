package org.oxlifeproject.etat.model;

import java.io.Serializable;

/**
 * Created by Tuti on 03/01/2018.
 */

public class SectionContentChildrenModel implements Serializable {

    private int ID, sectionContent;
    private String title, content;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSectionContent() {
        return sectionContent;
    }

    public void setSectionContent(int sectionContent) {
        this.sectionContent = sectionContent;
    }

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
}
