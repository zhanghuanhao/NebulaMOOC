/*
 * @author Zhanghh
 * @date 2019/4/13
 */
package com.nebula.mooc.core.entity;

import java.util.List;

public class CourseChapter {

    private long id;
    private long courseId;
    private String title;

    private List<CourseSection> sectionList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CourseSection> getSectionList() {
        return sectionList;
    }

    public void setSectionList(List<CourseSection> sectionList) {
        this.sectionList = sectionList;
    }

}
