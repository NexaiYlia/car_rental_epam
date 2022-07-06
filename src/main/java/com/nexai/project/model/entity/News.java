package com.nexai.project.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class News implements Identifiable, Serializable {
    private static final long serialVersionUID = -4657136872925977530L;

    private int id;
    private int userId;
    private String header;
    private String content;
    private LocalDate publicationDate;
    private String imagePath;

    public News() {
    }


    public News(int id, int userId, String header, String content, LocalDate publicationDate, String imagePath) {
        this.id = id;
        this.userId = userId;
        this.header = header;
        this.content = content;
        this.publicationDate = publicationDate;
        this.imagePath = imagePath;
    }

    public News(int userId, String header, String content, String imagePath) {
        this(-1, userId, header, content, null, imagePath);
    }

    public News(int id, int userId, String header, String content, String imagePath) {
        this(id, userId, header, content, null, imagePath);
    }

    @Override
    public int getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (id != news.id) return false;
        if (userId != news.userId) return false;
        if (!header.equals(news.header)) return false;
        if (!content.equals(news.content)) return false;
        if (publicationDate != null ? !publicationDate.equals(news.publicationDate) : news.publicationDate != null)
            return false;
        return imagePath != null ? imagePath.equals(news.imagePath) : news.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (header != null ? header.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("News{");
        sb.append("id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", header='").append(header).append('\'');
        sb.append(", content='").append(content).append('\'');
        sb.append(", publicationDate=").append(publicationDate);
        sb.append(", imagePath='").append(imagePath).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
