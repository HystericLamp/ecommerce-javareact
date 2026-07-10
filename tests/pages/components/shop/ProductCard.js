export class ProductCard {
    constructor(page, id, productName) {
        this.page = page;
        this.id = id;

        this.name = page.getByRole('heading', {
            name: productName,
            level: 3
        });

        this.addBtn = page.getByTestId(`add-${id}`);
        this.increaseBtn = page.getByTestId(`increase-${id}`);
        this.decreaseBtn = page.getByTestId(`decrease-${id}`);
        this.quantity = page.getByTestId(`quantity-${id}`);
    }

    async addToCart() {
        await this.addBtn.click();
    }

    async increase() {
        await this.increaseBtn.click();
    }

    async decrease() {
        await this.decreaseBtn.click();
    }
}