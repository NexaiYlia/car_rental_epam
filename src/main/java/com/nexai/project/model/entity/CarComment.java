package com.nexai.project.model.entity;

import java.io.Serializable;

public class CarComment implements Identifiable, Serializable {

    private static final long serialVersionUID = 1113293598558703521L;

    private int id;
    private User user;
    private Car car;
    private String content;

    public CarComment() {}

    public CarComment(int id, User user, Car car, String content) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.content = content;
    }

    public CarComment(User user, Car car, String content) {
        this(-1, user, car, content);
    }

    @Override
    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarComment comment = (CarComment) o;

        if (id != comment.id) return false;
        if (!user.equals(comment.user)) return false;
        if (!car.equals(comment.car)) return false;
        return content.equals(comment.content);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user.hashCode();
        result = 31 * result + car.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CarComment{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", car=").append(car);
        sb.append(", content='").append(content).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
