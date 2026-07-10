import { CustomerForm } from "./components/checkout-details/CustomerForm";
import { OrderItems } from "./components/checkout-details/OrderItems";
import { OrderSummary } from "./components/checkout-details/OrderSummary";

export class CheckoutDetailsPage {
    constructor(page) {
        this.page = page;
    }

    orderItems(id) {
        return new OrderItems(this.page, id);
    }

    customerForm() {
        return new CustomerForm(this.page);
    }

    orderSummary() {
        return new OrderSummary(this.page);
    }
}