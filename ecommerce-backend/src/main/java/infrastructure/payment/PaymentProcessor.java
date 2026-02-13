package infrastructure.payment;

public interface PaymentProcessor
{
	String createPayment(long amount, String currencyType);
	boolean confirmPayment(String paymentID);
	boolean refundPayment(String paymentID);
}
