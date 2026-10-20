import java.util.*;
import java.time.LocalDate;

class Solution {
    public String matchPayment(String payment, String[] invoices, int forgiveness) {
        String[] paymentSplit = payment.split(",");
        String paymentId = paymentSplit[0];
        int paymentAmount = Integer.parseInt(paymentSplit[1]);
        String memo = paymentSplit[2];
        
        int containsNote = memo.indexOf("paying off:");
        String targetMemoId = containsNote != -1 ? memo.substring(containsNote + 11).trim().split(" ")[0] : null;

        String exactInvoiceId = "";
        LocalDate exactDueDate = null;

        String forgivenInvoiceId = "";
        LocalDate forgivenDueDate = null;
        int bestForgivenAmount = 0;

        for (String invoiceString : invoices) {
            String[] invoice = invoiceString.split(",");
            String id = invoice[0];
            int invoiceAmount = Integer.parseInt(invoice[1]);
            LocalDate dueDate = LocalDate.parse(invoice[2]);

            // 1. Memo Match: Return immediately
            if (targetMemoId != null && id.equals(targetMemoId)) {
                return paymentId + " paid " + paymentAmount + " for invoice " + id + " on date " + dueDate;
            }

            // 2. Exact Amount Match
            int diff = Math.abs(invoiceAmount - paymentAmount);
            if (diff == 0) {
                if (exactInvoiceId.isEmpty() || dueDate.isBefore(exactDueDate)) {
                    exactInvoiceId = id;
                    exactDueDate = dueDate;
                }
            } 
            // 3. Forgiven Match (Only considered if diff > 0 and <= forgiveness)
            else if (containsNote == -1 && diff <= forgiveness) {
                if (forgivenInvoiceId.isEmpty() || dueDate.isBefore(forgivenDueDate)) {
                    forgivenInvoiceId = id;
                    forgivenDueDate = dueDate;
                    bestForgivenAmount = diff;
                }
            }
        }

        // Output formatting based on priority
        if (!exactInvoiceId.isEmpty()) {
            return paymentId + " paid " + paymentAmount + " for invoice " + exactInvoiceId + " on date " + exactDueDate;
        }
        
        if (!forgivenInvoiceId.isEmpty()) {
            return paymentId + " paid " + paymentAmount + " for invoice " + forgivenInvoiceId + " on date " + forgivenDueDate + " (forgiven " + bestForgivenAmount + ")";
        }

        return "";
    }
}
