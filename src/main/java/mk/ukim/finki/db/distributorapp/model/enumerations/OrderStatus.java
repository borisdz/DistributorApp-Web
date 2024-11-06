package mk.ukim.finki.db.distributorapp.model.enumerations;

public enum OrderStatus {
    CREATED,
    CONFIRMED,
    PENDING_PAYMENT,
    PAYMENT_RECEIVED,
    PENDING_DELIVERY,
    OUT_OF_STOCK,
    DECLINED
}
