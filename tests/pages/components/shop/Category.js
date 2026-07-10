export class Category {
  constructor(page, name) {
    this.page = page;

    this.header = page.getByRole('heading', {
      name,
      level: 2
    });
  }
}