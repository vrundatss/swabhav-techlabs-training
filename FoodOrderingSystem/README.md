Food Ordering System (Java Mini-Project)

Key Features :
Administrative Module ==>
Menu Management: Create, Read, Update, and Delete (CRUD) food items.

Stock Control: Monitor and update inventory levels.

Order Management: Assign pending orders to available delivery agents.

Revenue Tracking: Calculate platform commission and total earnings.

Category Controls: Bulk remove items or filter menu by category.

Customer Module ==>
Dynamic Customization: Uses the Decorator Pattern to add Extra Cheese, Toppings, or Spices to any item.

Shopping Cart: Persistent session-based cart with the ability to merge quantities.

Discount Engine: Automatic calculation of savings using Strategy Pattern (Day-based, Flat, and Festival discounts).

Real-time Tracking: Receive notifications as order status changes from Placed to Delivered.

Delivery Agent Module ==>
Availability Toggle: Agents can set themselves as Available or Busy.

Earning Wallet: Automated credit of delivery fees upon successful delivery completion.

Task Inbox: Separate notification stream for assigned tasks.

| Concept                        | Where Used                                                                            |
| ------------------------------ | ------------------------------------------------------------------------------------- |
| **OOP Principles**             | Encapsulation (in models), Inheritance (statuses), Polymorphism (discount strategies) |
| **Design Patterns**            | Factory (for payment), Strategy (for discounts), Facade (AppFacade)                   |
| **Collections Framework**      | `Map`, `List`, `Stream API` for filtering and grouping                                |
| **Exception Handling**         | Custom `ResourceNotFoundException` for robust flow                                    |
| **Enums & Inner Classes**      | Category types, order states                                                          |
| **Java 8 Streams & Lambdas**   | Filtering menu, searching orders                                                      |
| **Serialization-ready Models** | Easy to persist in the future                                                         |
| **Database Connection**        | Easy to connect with SQL/NoSQL Database in the future
