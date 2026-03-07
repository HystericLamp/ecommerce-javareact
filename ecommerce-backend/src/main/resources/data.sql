DELETE FROM order_items;
DELETE FROM orders;
DELETE FROM products;
DELETE FROM categories;
DELETE FROM users;

INSERT INTO users (name, email, password)
VALUES 
('Member1', 'member1@shop.com', '$2a$10$9Z.ib4DQLsPZHD1FT.ruE.RvHSwN0HveoVru9S2hodCgsb75HeeLW'),
('Member2', 'member2@shop.com', '$2a$10$9/qqgkCcALFKrlV/.8UA6e/KmW2LZHK8Y2eVgzQcdjYebkiIcyVyu');

INSERT INTO categories (id, name) VALUES
(1, "Coffee"),
(2, "Brew Gear"),
(3, "Accessories"),
(4, "Snacks");

INSERT INTO products (name, description, price, stock, category_id)
VALUES 
("Ethiopian Sunrise",
 "Single-origin light roast with floral and citrus notes. 12oz bag.",
 1800,
 50,
 1),

("Colombian Supremo",
 "Medium roast with chocolate and caramel undertones. 12oz bag.",
 1600,
 60,
 1),

("Velvet Morning Blend",
 "Signature house blend, smooth and balanced. 12oz bag.",
 1700,
 75,
 1),

("Hazelnut Harmony",
 "Flavored medium roast with smooth hazelnut sweetness. 12oz bag.",
 1900,
 40,
 1),

("Decaf Dream",
 "Swiss Water Process decaf with full flavor and no caffeine. 12oz bag.",
 1800,
 35,
 1),

("Aurora Pour-Over Kit",
 "Ceramic dripper, glass carafe, filters, and scoop included.",
 4500,
 25,
 2),

("Classic French Press",
 "34oz stainless steel French press for rich, bold coffee.",
 3800,
 30,
 2),

("Espresso Mini Machine",
 "Compact home espresso maker with built-in milk frother.",
 12000,
 15,
 2),

("Precision Coffee Grinder",
 "Manual hand grinder for freshly ground coffee at home.",
 3500,
 45,
 2),

("Cold Brew Mason Jar Kit",
 "Glass jar with reusable filter for smooth cold brew.",
 2500,
 50,
 2),

("Stoneware Latte Mug",
 "12oz hand-painted mug with matte artisan finish.",
 2200,
 70,
 3),

("Bamboo Travel Tumbler",
 "16oz eco-friendly tumbler with leak-proof lid.",
 2800,
 55,
 3),

("Golden Coffee Scoop",
 "Stylish brass scoop for perfectly measured coffee.",
 1200,
 100,
 3),

("Coffee Foam Stencil Set",
 "Set of 5 stencils for creating latte art designs.",
 1500,
 65,
 3),

("Mocha Candle",
 "8oz soy wax candle with rich mocha aroma.",
 2000,
 40,
 3),

("Chocolate-Dipped Biscotti",
 "Almond biscotti dipped in dark chocolate. 6-pack.",
 1200,
 80,
 4),

("Cacao Crunch Granola",
 "Coffee-infused granola with chocolate chunks. 10oz bag.",
 1400,
 60,
 4),

("Mini Cinnamon Muffins",
 "Set of 4 soft cinnamon muffins.",
 1000,
 50,
 4);