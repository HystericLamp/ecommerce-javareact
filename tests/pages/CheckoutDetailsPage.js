import { CustomerForm } from "./components/checkout-details/CustomerForm";
import { OrderItems } from "./components/checkout-details/OrderItems";
import { OrderSummary } from "./components/checkout-details/OrderSummary";

export class CheckoutDetailsPage {
    constructor(page) {
        this.page = page;

        this.items = page.getByTestId('checkout-item');
    }

    orderItems(index) {
        return new OrderItems(
            this.items.nth(index)
        );
    }

    customerForm() {
        return new CustomerForm(this.page);
    }

    orderSummary() {
        return new OrderSummary(this.page);
    }
}