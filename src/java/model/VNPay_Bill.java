package model;

/**
 *
 * @author phuct
 */
public class VNPay_Bill {

    private String vnpTxnRef;            // vnp_TxnRef
    private float vnpAmount;              // vnp_Amount
    private String vnpPayDate;            // vnp_PayDate
    private String vnpTransactionStatus;  // vnp_TransactionStatus
    private int orderId;                  // OrderId liên kết với bảng Order

    public VNPay_Bill() {
    }

    public VNPay_Bill(String vnpTxnRef, float vnpAmount, String vnpPayDate, String vnpTransactionStatus, int orderId) {
        this.vnpTxnRef = vnpTxnRef;
        this.vnpAmount = vnpAmount;
        this.vnpPayDate = vnpPayDate;
        this.vnpTransactionStatus = vnpTransactionStatus;
        this.orderId = orderId;  // Khởi tạo orderId
    }

    public String getVnpTxnRef() {
        return vnpTxnRef;
    }

    public void setVnpTxnRef(String vnpTxnRef) {
        this.vnpTxnRef = vnpTxnRef;
    }

    public float getVnpAmount() {
        return vnpAmount;
    }

    public void setVnpAmount(float vnpAmount) {
        this.vnpAmount = vnpAmount;
    }

    public String getVnpPayDate() {
        return vnpPayDate;
    }

    public void setVnpPayDate(String vnpPayDate) {
        this.vnpPayDate = vnpPayDate;
    }

    public String getVnpTransactionStatus() {
        return vnpTransactionStatus;
    }

    public void setVnpTransactionStatus(String vnpTransactionStatus) {
        this.vnpTransactionStatus = vnpTransactionStatus;
    }

    public int getOrderId() {
        return orderId;  
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;  
    }
}
