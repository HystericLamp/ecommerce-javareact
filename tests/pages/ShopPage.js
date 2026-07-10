import { ProductCard } from './components/shop/ProductCard'
import { Category } from './components/shop/Category';

export class ShopPage {
  constructor(page) {
    this.page = page;

    this.heading = page.getByRole('heading', {
      name: 'Coffee & Brew Gear'
    });
  }

  async goto() {
    await this.page.goto('/shop');
  }

  product(id, name) {
    return new ProductCard(this.page, id, name);
  }

  category(name) {
    return new Category(this.page, name);
  }
}