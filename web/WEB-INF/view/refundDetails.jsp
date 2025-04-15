<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Refund Details</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"/>
        <link rel="stylesheet" href="./assets/font/themify-icons/themify-icons.css" />
        <link rel="stylesheet" href="./assets/css/style.css">
        <link rel="stylesheet" href="./assets/css/refundDetails.css">
        <link rel="stylesheet" href="./assets/css/order-detail.css">
        <link rel="stylesheet" href="./assets/css/header-footer.css">

    </head>
    <body>
        <%@ include file="/include/shop-header.jsp" %>

        <div class="container refund-container">
            <div class="refund-header">
                <h4 class="text-primary">Refund Status: ${refundInfo.refundStatus}</h4>
            </div>

            <div class="refund-details">
                <div class="refund-item">
                    <div><strong>Refund ID:</strong></div>
                    <div>${refundInfo.refundId}</div>
                </div>
                <div class="refund-item">
                    <div><strong>Order ID:</strong></div>
                    <div>${refundInfo.orderId}</div>
                </div>
                <div class="refund-item">
                    <div><strong>Refund Reason:</strong></div>
                    <div>${refundInfo.refundReason}</div>
                </div>
                <div class="refund-item">
                    <div><strong>Refund Amount:</strong></div>
                    <div>${refundInfo.refundAmount}</div>
                </div>
                <div class="refund-item">
                    <div><strong>Created At:</strong></div>
                    <div><fmt:formatDate value="${refundInfo.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
                </div>
                <div class="refund-item">
                    <div><strong>Reason Image:</strong></div>
                    <img class="refund-img" src="${refundInfo.refundReasonImg}" alt="alt"/>
                </div>
            </div>

            <div class="refund-actions">
                <c:choose>
                    <c:when test="${refundInfo.refundStatus == 'PENDING'}">
                        <!-- Button to trigger modal -->
                        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#refundModal">Approve Refund</button>
                    </c:when>
                    <c:otherwise>
                        <p class="text-secondary">This refund has been processed.</p>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${refundInfo.refundStatus != 'APPROVED' && refundInfo.refundStatus != 'REJECTED'}">
                        <form method="post" action="refundDetails">
                            <input type="hidden" name="refundId" value="${refundInfo.refundId}" />
                            <input type="hidden" name="action" value="reject" />
                            <button type="submit" class="btn btn-danger" onclick="return confirm('Are you sure you want to reject this refund?');">Reject Refund</button>
                        </form>
                    </c:when>
                </c:choose>
            </div>
        </div>

        <!-- Modal for refund approval -->
        <div class="modal fade" id="refundModal" tabindex="-1" aria-labelledby="refundModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="refundModalLabel">Refund Information</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="refund" id="frmrefund" method="post">
                            <div class="form-group">
                                <label for="order_id">Transaction ID to Refund (vnp_TxnRef)</label>
                                <input class="form-control" id="paymentID" name="paymentID" type="text"
                                       value="${vnpayBill.vnpTxnRef}" readonly required />
                            </div>
                            <div class="form-group">
                                <label for="amount">Total Amount Order</label>
                                <input class="form-control" type="text"
                                       value="${formattedAmount}" readonly required />
                            </div>
                            <div class="form-group">
                                <label for="trantype">Refund Type</label>
                                <select name="trantype" id="trantype" class="form-control" required>
                                    <option value="02">Full Refund</option>
                                    <option value="03">Partial Refund</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="amount">Refund Amount</label>
                                <input class="form-control" id="amount" name="amount" type="number" min="1" max="${vnpayBill.vnpAmount}"
                                       value="" required />
                            </div>
                            <div class="form-group">
                                <label for="trans_date">Transaction Creation Date (vnp_CreateDate)</label>
                                <input class="form-control" id="trans_date" name="trans_date" type="text"
                                       value="${vnpayBill.vnpPayDate}" placeholder="yyyyMMddHHmmss" readonly required />
                            </div>
                            <div class="form-group">
                                <label for="user">User Initiating Refund</label>
                                <input class="form-control" id="user" name="user" type="text"
                                       value="FOODIE" required />
                            </div>
                            <input type="hidden" name="refundId" value="${refundInfo.refundId}" />
                            <input type="hidden" name="action" value="refundHandler">
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">Submit Refund</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/include/footer.jsp" %>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <script src="./assets/js/refund-details.js"></script>
    </body>
</html>
