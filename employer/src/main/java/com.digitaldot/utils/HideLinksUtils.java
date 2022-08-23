package com.digitaldot.utils;

import org.springframework.stereotype.Component;

@Component
public class HideLinksUtils {
//todo -> refactor for pattern
    private boolean isId;
    private boolean isEdit;
    private boolean isDelete;
    private boolean isCollection;
    //employerDto
    private boolean isDeleteJoinUser;
    private boolean isUser;

    public boolean isId() {
        return isId;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public boolean isDeleteJoinUser() {
        return isDeleteJoinUser;
    }

    public boolean isUser() {
        return isUser;
    }

    public HideLinksUtils hideId() {
        HideLinksUtils hideLinksUtils = new HideLinksUtils();
        hideLinksUtils.isId = true;
        return hideLinksUtils;
    }
    public HideLinksUtils hideEdit() {
        HideLinksUtils hideLinksUtils = new HideLinksUtils();
        hideLinksUtils.isEdit = true;
        return hideLinksUtils;
    }
    public HideLinksUtils hideDelete() {
        HideLinksUtils hideLinksUtils = new HideLinksUtils();
        hideLinksUtils.isDelete = true;
        return hideLinksUtils;
    }
    public HideLinksUtils hideCollection() {
        HideLinksUtils hideLinksUtils = new HideLinksUtils();
        hideLinksUtils.isCollection = true;
        return hideLinksUtils;
    }

    public HideLinksUtils hideDeleteJoinUser() {
        HideLinksUtils hideLinksUtils = new HideLinksUtils();
        hideLinksUtils.isDeleteJoinUser = true;
        return hideLinksUtils;
    }

    public HideLinksUtils hideUser() {
        HideLinksUtils hideLinksUtils = new HideLinksUtils();
        hideLinksUtils.isUser = true;
        return hideLinksUtils;
    }

    //todo -> implements
    public HideLinksUtils hideMany() {
        HideLinksUtils hideLinksUtils = new HideLinksUtils();
        return null;
    }
}
