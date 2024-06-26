# HeyMart 👋

## Advanced Programming Group Project - Pejuang Adpro

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=PejuangAdpro_HeyMart&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=PejuangAdpro_HeyMart)

HeyMart has been successfully deployed using Koyeb. You can access the website [here](https://heymart-brmntyprytm.koyeb.app/).

### Team Members:
1. `andikaprmdyaa` - Andika Pramudya Wardana / 2206046645

   Responsible for: Manage products (🍎)
   - [x] Supermarket Managers can add products
   - [x] Supermarket Managers can change and delete products
   - [x] Users can view products in a supermarket

2. `brmntyprytm` - Bramantyo Priyo Utomo / 2206821563

   Responsible for:
   - Database
      - [x] Established connection to Railway PostgreSQL
   - Continuous Integration 
      - [x] Set Up CI with SonarCloud
      - [x] Set Up CI with GitHub Actions
   - Continuous Deployment
      - [x] Set Up CD with Railway 
   - Styling
     -  [x] Set Up Tailwind CSS
     -  [x] Styled Landing, Login and Register Page
   - Authentication (🧑‍/💻/🍎)
      - [x] Users can register
      - [x] Users can login
      - [x] Users can logout

   - Manage Shopping Cart (🧑‍)
      - [x] Buyers can view the Shopping Cart
      - [x] Buyers can add products to the Shopping Cart. The Shopping Cart can only contain products in one Supermarket. If the buyer adds products from a different Supermarket, display a notification to delete the products in the previous Shopping Cart.
      - [x] Buyers can check-out the Shopping Cart and make payment.

3. `PascalPahlevi` - Pascal Pahlevi / 2206046752

   Responsible for: Manage Supermarkets (💻)
   - [x] Admin can register supermarkets
   - [x] Users can view supermarkets
   - [x] Admin can change and delete supermarkets

4. `EzarY7` - Ezar Yudha / 2206046746

   Responsible for: Manage Balances (🍎/🧑‍)
   - [ ] Update Buyer and Supermarket balances when checking out. The Buyer's balance will decrease and the Supermarket's balance will increase
   - [x] Buyers can top-up balance
   - [x] Supermarket managers can withdraw balances

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

**Git Workflow**:
1. Clone the repository.
2. Create a new branch for the feature you are working on.
3. Make commits to the feature branch using the commit message format.
4. Push the feature branch to the remote repository.
5. Create a pull request to merge the feature branch into the develop branch.
6. Make sure that all checks are passed and the code is reviewed before merging the pull request.
7. Merge develop into main when the project is ready for release.