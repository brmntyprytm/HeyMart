# HeyMart 👋

## Advanced Programming Group Project - Pejuang Adpro

### Team Members:
1. `andikaprmdyaa` - Andika Pramudya Wardana / 2206046645

   Responsible for: Manage products (🍎)
    - Supermarket Managers can add products
    - Supermarket Managers can change and delete products
    - Users can view products in a supermarket

2. `brmntyprytm` - Bramantyo Priyo Utomo / 2206821563

   Responsible for:
    - Authentication (🧑‍)
        - Users can register
        - Users can login
        - Users can logout

    - Manage Shopping Cart (🧑‍)
        - Buyers can view the Shopping Cart
        - Buyers can add products to the Shopping Cart. The Shopping Cart can only contain products in one Supermarket. If the buyer adds products from a different Supermarket, display a notification to delete the products in the previous Shopping Cart.
        - Buyers can check-out the Shopping Cart and make payment.

3. `PascalPahlevi` - Pascal Pahlevi / 2206046752

   Responsible for: Manage Supermarkets (💻)
    - Admin can register supermarkets
    - Users can view supermarkets
    - Admin can change and delete supermarkets

4. `EzarY7` - Ezar Yudha / 2206046746

   Responsible for: Manage Balances (🍎/🧑‍)
    - Update Buyer and Supermarket balances when checking out. The Buyer's balance will decrease and the Supermarket's balance will increase
    - Buyers can top-up balance
    - Supermarket managers can withdraw balances

**Branching Strategy**:
* `feature` branches: All new features or changes should be developed on separate feature branches branched off from the develop branch.
* `develop` branch: Integration branch for ongoing development.
* `main` branch: Production ready state of the project.

**Commit Message Format**:
* Use imperative mood for commit message types: "GREEN", "RED", "REFACTOR", "STYLE", "DOCS", "CHORE", "FEAT", "FIX", etc.
* Using this format [Type]: Short description

**Pull Request Process**:
1. Create a pull request from feature branch to the develop branch.
2. Provide a descriptive title for the pull request that summarizes the changes.