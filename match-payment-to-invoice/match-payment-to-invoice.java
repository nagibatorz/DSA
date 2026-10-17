import java.util.*;
import java.math.*;
import java.time.LocalDate;
class Solution {
    public String matchPaymentToInvoice(String payment, String[] invoices) {
        // TODO: Implement matchPaymentToInvoice logic
        List<String> potentialInvoices = new ArrayList<>();
        String[] paymentSplit = payment.split(",");
        String paymentId = paymentSplit[0];
        int paymentAmount = Integer.parseInt(paymentSplit[1]);
        String memo = paymentSplit[2];
        
        int containsNote = memo.indexOf("paying off:");
        String resInvoiceId = "";
        LocalDate resDueDate = null;
        for(String invoiceString : invoices){
            String[] invoice = invoiceString.split(",");
            String id = invoice[0];
            int amount = Integer.parseInt(invoice[1]);
            LocalDate dueDate = LocalDate.parse(invoice[2]);
            if(containsNote != -1 && id.equals(memo.substring(containsNote + 11).trim().split(" ")[0])){
                return paymentId + " paid " + paymentAmount + " for invoice " + id + " on date " + dueDate;
            }
            if(containsNote == -1 && amount == paymentAmount){
                if(resInvoiceId.isEmpty() || resDueDate.isAfter(dueDate)){
                    resInvoiceId = id;
                    resDueDate = dueDate;
                }
            }
        }


        if(resInvoiceId.length() == 0) return resInvoiceId;
        return paymentId + " paid " + paymentAmount + " for invoice " + resInvoiceId + " on date " + resDueDate;
    }
}
