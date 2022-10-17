package BLL;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private int orderId;
    private int clientId;
    private Date orderDate;

    public Order(int orderId, int clientId, Date orderDate) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.orderDate = orderDate;
    }

    public Order() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj==null) return false;
        if(!(obj instanceof Order)) return false;
        Order mi = (Order)obj;
        return this.orderId == mi.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.orderId);
    }

    @Override
    public String toString() {
        return "orderId=" + orderId +
                ", clientId=" + clientId +
                ", orderDate=" + orderDate;
    }
}
