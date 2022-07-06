package com.nexai.project.controller;

public class Router {
    private String page;
    private Type type = Type.FORWARD;
    public enum Type{
        FORWARD,
        REDIRECT
    }

    public Router() {
    }
    public Router(String page) {
        this.page = page;
    }

    public Router(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Type getType(){
        return type;
    }

    public void setType() {
        this.type = Type.REDIRECT;
    }
}
