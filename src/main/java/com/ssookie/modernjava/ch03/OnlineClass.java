package com.ssookie.modernjava.ch03;

import com.ssookie.modernjava.ch04.Progress;

import java.util.Optional;

public class OnlineClass {
    private Integer id;
    private String title;
    private boolean closed;
    public Progress progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed= closed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    // Optional - 리턴 타입으로 쓰는 것이 권장사항임.
    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress);
    }

    public OnlineClass setProgress(Progress progress) {
        this.progress = progress;
        return this;
    }
}
