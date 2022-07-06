package com.nexai.project.model.entity;

import com.nexai.project.model.entity.type.PaymentStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Payment implements Identifiable, Serializable {

    private static final long serialVersionUID = 5139007526727524142L;

    private int id;
    private Integer orderId;
    private PaymentStatus status;
    private BigDecimal totalPrice;
    private LocalDate paymentDate;

    public Payment() {}

    public Payment(int id, Integer orderId, PaymentStatus status, BigDecimal totalPrice, LocalDate paymentDate) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.totalPrice = totalPrice;
        this.paymentDate = paymentDate;
    }

    public Payment(Integer orderId, PaymentStatus status, BigDecimal totalPrice, LocalDate paymentDate) {
        this(-1, orderId, status, totalPrice, paymentDate);
    }

    public int getId() {
        return id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (id != payment.id) return false;
        if (!orderId.equals(payment.orderId)) return false;
        if (status != payment.status) return false;
        if (!totalPrice.equals(payment.totalPrice)) return false;
        return paymentDate.equals(payment.paymentDate);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + orderId.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + totalPrice.hashCode();
        result = 31 * result + paymentDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payment{");
        sb.append("id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", status=").append(status);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", paymentDate=").append(paymentDate);
        sb.append('}');
        return sb.toString();
    }
}
