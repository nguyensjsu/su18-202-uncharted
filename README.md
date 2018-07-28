# su18-202-uncharted

# Design Decisions
1. We wanted to keep the application completely stateless.
2. We wanted to authenticate all requests to the API's so we used HTTP Basic authentication.
3. In our design the major components are : Customer, Card, Orders, Menu
4. Each customer can have only 1 card at a time he/she can update its balance or replace with a whole new card.
5. Each customer can have only 1 open order at a time and must payh for a new order.
6. We are using factory pattern and so if a new implementaion of DAO objects is present then minimal modification is required.


# Feature Set
1. Checking if card present before allowing to order.
2. Checking balance in card before payment.
3. Only 1 open order at a time.
4. Can get order description.
5. Authentication with every request.
6. Authorization check for every request.
7. Automatic total generation of order.

![deployment](https://user-images.githubusercontent.com/31361726/43352273-f6b20cf8-923e-11e8-86ec-5f67e4403ea3.PNG)
