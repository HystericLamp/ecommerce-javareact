import { ProductCard } from './components/shop/ProductCard'
import { Category } from './components/shop/Category';

export class ShopPage {
  constructor(page) {
    this.page = page;

    this.products = page.getByTestId('product-card');
  }

  async goto() {
    await this.page.goto('/shop');
  }

  product(index) {
    return new ProductCard(
      this.products.nth(index)
    );
  }

  productByName(name) {
    const card = this.products.filter({
      has: this.page.getByTestId('product-name').filter({
        hasText: name
      })
    });

    return new ProductCard(card);
  }

  async productCount() {
    return await this.products.count();
  }

  category(name) {
    return new Category(this.page, name);
  }
}