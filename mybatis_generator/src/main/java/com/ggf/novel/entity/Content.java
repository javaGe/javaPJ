package com.ggf.novel.entity;

public class Content {
    private String storyName;

    private String chapter;

    private Integer chapterNum;

    private String storyId;

    private String storyContent;

    public Content(String storyName, String chapter, Integer chapterNum, String storyId, String storyContent) {
        this.storyName = storyName;
        this.chapter = chapter;
        this.chapterNum = chapterNum;
        this.storyId = storyId;
        this.storyContent = storyContent;
    }

    public Content() {
        super();
    }

    public String getStoryName() {
        return storyName;
    }

    public void setStoryName(String storyName) {
        this.storyName = storyName == null ? null : storyName.trim();
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter == null ? null : chapter.trim();
    }

    public Integer getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(Integer chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId == null ? null : storyId.trim();
    }

    public String getStoryContent() {
        return storyContent;
    }

    public void setStoryContent(String storyContent) {
        this.storyContent = storyContent == null ? null : storyContent.trim();
    }
}