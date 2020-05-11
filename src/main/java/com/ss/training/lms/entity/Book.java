package com.ss.training.lms.entity;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Book implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = -3859753496101899553L;
    private Integer bookId;
    private String title;
    private Integer publisherId;

    @Override
    public String toString() {
        return bookId + ", " + title + ", " + publisherId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Book(Integer bookId, String title, Integer publisherId) {
        this.bookId = bookId;
        this.title = title;
        this.publisherId = publisherId;
    }

    public Book() {
	}

    public Book(String title, Integer publisherId) {
        this.title = title;
        this.publisherId = publisherId;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + bookId;
        result = prime * result + publisherId;
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (bookId != other.bookId)
            return false;
        if (publisherId != other.publisherId)
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        return true;
    }

    
}