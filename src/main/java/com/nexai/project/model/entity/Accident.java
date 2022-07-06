package com.nexai.project.model.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Accident implements Identifiable, Serializable {
    private static final long serialVersionUID = -7344903344424645993L;

    private Integer id;
    private LocalDate date;
    private String description;
    private BigDecimal cost;
    private Order order;

    public Accident() {}

    public Accident(Integer id, LocalDate date, String description, BigDecimal cost, Order order) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.cost = cost;
        this.order = order;
    }

    @Override
    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public com.nexai.project.model.entity.Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Accident accident = (Accident) o;

        if (id != null ? !id.equals(accident.id) : accident.id != null) return false;
        if (date != null ? !date.equals(accident.date) : accident.date != null) return false;
        if (description != null ? !description.equals(accident.description) : accident.description != null)
            return false;
        if (cost != null ? !cost.equals(accident.cost) : accident.cost != null) return false;
        return order != null ? order.equals(accident.order) : accident.order == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Accident{");
        sb.append("id=").append(id);
        sb.append(", date=").append(date);
        sb.append(", description='").append(description).append('\'');
        sb.append(", cost=").append(cost);
        sb.append(", order=").append(order);
        sb.append('}');
        return sb.toString();
    }
}
